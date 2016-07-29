package jp.ac.nii.prl.mape.autoscaling.service;

import java.util.Optional;

import jp.ac.nii.prl.mape.autoscaling.model.Deployment;

public interface DeploymentService {
	
	Deployment save(Deployment deployment);
	
	Optional<Deployment> findById(Long deploymentId);

}
