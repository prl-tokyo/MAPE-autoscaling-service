package jp.ac.nii.prl.mape.autoscaling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.autoscaling.model.Deployment;

public interface DeploymentRepository extends JpaRepository<Deployment, Long> {

	Optional<Deployment> findById(Long id);
}
