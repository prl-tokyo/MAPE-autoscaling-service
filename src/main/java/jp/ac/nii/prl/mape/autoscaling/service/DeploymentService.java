package jp.ac.nii.prl.mape.autoscaling.service;

import java.util.Optional;

import jp.ac.nii.prl.mape.autoscaling.model.Adaptation;
import jp.ac.nii.prl.mape.autoscaling.model.Deployment;
import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;

public interface DeploymentService {
	
	Deployment save(final Deployment deployment);
	
	Optional<Deployment> findById(final Long deploymentId);
	
	Adaptation analyse(final Deployment deployment);
	
	DeploymentDTO analyse(final DeploymentDTO deployment);

}
