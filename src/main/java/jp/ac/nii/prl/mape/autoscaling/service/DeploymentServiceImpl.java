package jp.ac.nii.prl.mape.autoscaling.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.autoscaling.model.Adaptation;
import jp.ac.nii.prl.mape.autoscaling.model.Deployment;
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
	public Deployment save(Deployment deployment) {
		assert(deployment != null);
		
		return deploymentRepository.save(deployment);
	}
	
	@Override
	public Optional<Deployment> findById(Long deploymentId) {
		assert(deploymentId != null);
		
		return deploymentRepository.findById(deploymentId);
	}

	@Override
	public Adaptation analyse(Deployment deployment) {
		logger.debug("Starting analysis");
		
		double load = deployment.getAverageLoad();
		
		logger.debug(String.format("Average Load per CPU is %f", load));
		
		Adaptation adaptation = new Adaptation();
		if (load >= analysisProperties.getMaxThreshold()) {
			
			logger.debug("Average load per CPU is over the max threshold, scaling up required");
			
			adaptation.setAdapt(true);
			adaptation.setUp(true);
			adaptation.setCpuCount(
					new Double(deployment.getNumberCPUs() 
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

}
