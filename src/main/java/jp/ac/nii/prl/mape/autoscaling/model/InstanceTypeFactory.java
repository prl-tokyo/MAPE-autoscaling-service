package jp.ac.nii.prl.mape.autoscaling.model;

import java.util.List;

import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceTypeDTO;

public class InstanceTypeFactory {

	public static InstanceType createInstanceType(InstanceTypeDTO dto, 
			Deployment deployment, 
			List<Instance> instances) {
		assert(dto != null && deployment != null && instances != null);
		
		InstanceType instanceType = new InstanceType();
		
		instanceType.setTypeCost(dto.getTypeCost());
		instanceType.setTypeCPUs(dto.getTypeCPUs());
		instanceType.setTypeID(dto.getTypeID());
		instanceType.setTypeRAM(dto.getTypeRAM());
		instanceType.setDeployment(deployment);
		instanceType.setInstances(instances);
		
		return instanceType;
	}
	
	public static InstanceTypeDTO createDTO(InstanceType instanceType) {
		assert(instanceType != null);
		
		InstanceTypeDTO dto = new InstanceTypeDTO();
		
		dto.setTypeCost(instanceType.getTypeCost());
		dto.setTypeCPUs(instanceType.getTypeCPUs());
		dto.setTypeID(instanceType.getTypeID());
		dto.setTypeRAM(instanceType.getTypeRAM());
		
		return dto;
	}
}
