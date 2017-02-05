package jp.ac.nii.prl.mape.autoscaling.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceTypeDTO;

public class DeploymentFactory {
	
	public static Deployment createDeployment(final DeploymentDTO dto) {
		assert(dto != null);
		
		final Deployment deployment = new Deployment();
		
		final List<Instance> instances = new ArrayList<>();
		final Map<String, List<Instance>> instByType = new HashMap<>();
		for (InstanceDTO iDto:dto.getInstances()) {
			final Instance instance = InstanceFactory.createInstance(iDto, deployment);
			if (instByType.containsKey(iDto.getInstType())) {
				instByType.get(iDto.getInstType()).add(instance);
			} else {
				final List<Instance> list = new ArrayList<>();
				list.add(instance);
				instByType.put(iDto.getInstType(), list);
			}
			instances.add(instance);
		}
		deployment.setInstances(instances);
		
		final List<InstanceType> instanceTypes = new ArrayList<>();
		for(InstanceTypeDTO itDto:dto.getInstanceTypes()) {
			final List<Instance> linkedInstances = instByType.get(itDto.getTypeID());
			final InstanceType instanceType = InstanceTypeFactory.createInstanceType(itDto,
					deployment, linkedInstances);
			instanceTypes.add(instanceType);
			for (Instance linkedInstance:linkedInstances) {
				linkedInstance.setInstanceType(instanceType);
			}
		}
		deployment.setInstanceTypes(instanceTypes);
		
		return deployment;
	}
	
	public static DeploymentDTO createDTO(final Deployment deployment) {
		assert(deployment != null);
		
		final DeploymentDTO dto = new DeploymentDTO();
		
		final List<InstanceDTO> instanceDTOs = new ArrayList<>();
		for (Instance instance:deployment.getInstances()) {
			instanceDTOs.add(InstanceFactory.createDTO(instance));
		}
		dto.setInstances(instanceDTOs);
		
		final List<InstanceTypeDTO> instanceTypeDTOs = new ArrayList<>();
		for (InstanceType instanceType:deployment.getInstanceTypes()) {
            instanceTypeDTOs.add(InstanceTypeFactory.createDTO(instanceType));
        }
		dto.setInstanceTypes(instanceTypeDTOs);
		
		return dto;
	}

}
