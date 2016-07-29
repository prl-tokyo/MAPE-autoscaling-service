package jp.ac.nii.prl.mape.autoscaling.model;

import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;

public class InstanceFactory {
	
	public static Instance createInstance(InstanceDTO dto, Deployment deployment) {
		
		Instance instance = new Instance();
		
		instance.setInstID(dto.getInstID());
		instance.setDeployment(deployment);
		
		return instance;
	}
	
	public static Instance createInstance(InstanceDTO dto, 
			Deployment deployment,
			InstanceType instanceType) {
		
		Instance instance = createInstance(dto, deployment);
		instance.setInstanceType(instanceType);
		
		return instance;
	}
	
	public static InstanceDTO createDTO(Instance instance) {
		
		InstanceDTO dto = new InstanceDTO();
		
		dto.setInstID(instance.getInstID());
		dto.setInstLoad(instance.getInstLoad());
		dto.setInstType(instance.getInstanceType().getTypeID());
		
		return dto;
	}

}
