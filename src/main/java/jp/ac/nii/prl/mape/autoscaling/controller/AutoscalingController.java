package jp.ac.nii.prl.mape.autoscaling.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jp.ac.nii.prl.mape.autoscaling.model.Deployment;
import jp.ac.nii.prl.mape.autoscaling.model.DeploymentFactory;
import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;
import jp.ac.nii.prl.mape.autoscaling.service.DeploymentService;

@RestController
@Component
@RequestMapping(value="/autoscaling")
public class AutoscalingController {

	private static final Logger logger = LoggerFactory.getLogger(AutoscalingController.class);
	
	@Autowired
	private DeploymentService deploymentService;
	
	@RequestMapping(method= RequestMethod.POST)
	public ResponseEntity<?> createDeployment(@RequestBody DeploymentDTO deploymentDTO) {
		Deployment deployment = DeploymentFactory.createDeployment(deploymentDTO);
		deploymentService.save(deployment);
		
		logger.debug(String.format("Saved deployment with ID %s", deployment.getId()));
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(deployment.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{deploymentId}", method=RequestMethod.GET)
	public DeploymentDTO getDeployment(@PathVariable long deploymentId) {
		
		Optional<Deployment> deployment = deploymentService.findById(deploymentId);
		if (!deployment.isPresent()) {
			logger.error(String.format("No deployment found with id %s", deploymentId));
			throw new DeploymentNotFoundException(String.format("No deployment with ID %s", deploymentId));
		}
			
		return DeploymentFactory.createDTO(deployment.get());
		
	}
}