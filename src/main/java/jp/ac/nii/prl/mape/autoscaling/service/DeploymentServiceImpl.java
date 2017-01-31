package jp.ac.nii.prl.mape.autoscaling.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.autoscaling.model.Adaptation;
import jp.ac.nii.prl.mape.autoscaling.model.Deployment;
import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceTypeDTO;
import jp.ac.nii.prl.mape.autoscaling.properties.AnalysisProperties;
import jp.ac.nii.prl.mape.autoscaling.repository.DeploymentRepository;

@Service("deploymentService")
public class DeploymentServiceImpl implements DeploymentService {
	
	private static final Logger logger = LoggerFactory.getLogger(DeploymentServiceImpl.class);

	@Autowired
	private DeploymentRepository deploymentRepository;
	
	@Autowired
	private AnalysisProperties analysisProperties;
	
	@Override
	public Deployment save(final Deployment deployment) {
		assert(deployment != null);
		
		return deploymentRepository.save(deployment);
	}
	
	@Override
	public Optional<Deployment> findById(final Long deploymentId) {
		assert(deploymentId != null);
		
		return deploymentRepository.findById(deploymentId);
	}

	@Override
	public Adaptation analyse(final Deployment deployment) {
		logger.debug("Starting analysis");
		
		double load = deployment.getAverageLoad();
		
		logger.debug(String.format("Average Load per CPU is %f", load));
		
		final Adaptation adaptation = new Adaptation();
		if (load >= analysisProperties.getMaxThreshold()) {
			
			logger.debug("Average load per CPU is over the max threshold, scaling up required");
			
			adaptation.setAdapt(true);
			adaptation.setUp(true);
			adaptation.setCpuCount(
					Double.valueOf(deployment.getNumberCPUs()
							* analysisProperties.getScaleUp())
					.intValue());
		} else if ((load <= analysisProperties.getMinThreshold()) && (deployment.getInstances().size() > 2)) {
			
			logger.debug("Average load per CPU is under the min threshold, scaling down required");
			
			adaptation.setAdapt(true);
			adaptation.setUp(false);
			adaptation.setCpuCount(1);
		} else {
			
			logger.debug("No adaptation necessary");
			
			adaptation.setAdapt(false);
		}
		return adaptation;
	}

	@Override
	public DeploymentDTO analyse(final DeploymentDTO deployment) {
		double avg = getWeightedAvgCpuUsage(deployment);
		if (avg > 80) {
			InstanceDTO newInst = new InstanceDTO();
			newInst.setInstID(UUID.randomUUID().toString());
			newInst.setInstLoad(0.0);
			newInst.setInstType(deployment.getInstances().get(0).getInstType());
		} else if (avg < 40) {
			deployment.getInstances().remove(0);
		}
		return deployment;
	}
	
	private double getWeightedAvgCpuUsage(final DeploymentDTO deployment) {
		double total = 0F;
		int cpus = 0;
		for (InstanceDTO instance:deployment.getInstances()) {
			total += instance.getInstLoad();
			cpus += getCpuCount(deployment, instance.getInstType());
		}
		return total / cpus;
	}
	
	private int getCpuCount(final DeploymentDTO deployment, final String type) {
		for (InstanceTypeDTO instType:deployment.getInstanceTypes()) {
			if (instType.getTypeID().equals(type))
				return instType.getTypeCPUs();
		}
		return 0;
	}

}
