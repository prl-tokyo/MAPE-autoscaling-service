package jp.ac.nii.prl.mape.autoscaling.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.autoscaling.model.Deployment;
import jp.ac.nii.prl.mape.autoscaling.repository.DeploymentRepository;

@Service("deploymentService")
public class DeploymentServiceImpl implements DeploymentService {

	@Autowired
	private DeploymentRepository deploymentRepository;
	
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

}
