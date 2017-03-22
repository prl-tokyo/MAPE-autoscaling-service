package jp.ac.nii.prl.mape.autoscaling.service;

import jp.ac.nii.prl.mape.autoscaling.model.Adaptation;
import jp.ac.nii.prl.mape.autoscaling.model.Deployment;
import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;

import java.util.Optional;

public interface DeploymentService {
	
	Deployment save(Deployment deployment);
	
	Optional<Deployment> findById(Long deploymentId);
	
	Adaptation analyse(Deployment deployment);
	
	DeploymentDTO analyse(DeploymentDTO deployment);

}
