package jp.ac.nii.prl.mape.autoscaling.service;

import java.util.Optional;

import jp.ac.nii.prl.mape.autoscaling.model.Adaptation;
import jp.ac.nii.prl.mape.autoscaling.model.Deployment;
import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;

public interface DeploymentService {
	
	Deployment save(Deployment deployment);
	
	Optional<Deployment> findById(Long deploymentId);
	
	Adaptation analyse(Deployment deployment);
	
	DeploymentDTO analyse(DeploymentDTO deployment);

}
