package jp.ac.nii.prl.mape.autoscaling.model;

import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;

public class InstanceFactory {
	
	public static Instance createInstance(final InstanceDTO dto, final Deployment deployment) {
		assert(dto != null && deployment != null);
		
		final Instance instance = new Instance();
		
		instance.setInstID(dto.getInstID());
		instance.setDeployment(deployment);
		instance.setInstLoad(dto.getInstLoad());
		
		return instance;
	}
	
	public static Instance createInstance(final InstanceDTO dto,
			final Deployment deployment,
			final InstanceType instanceType) {
		assert(dto != null && deployment != null && instanceType != null);
		
		final Instance instance = createInstance(dto, deployment);
		instance.setInstanceType(instanceType);
		
		return instance;
	}
	
	public static InstanceDTO createDTO(final Instance instance) {
		assert(instance != null);
		
		final InstanceDTO dto = new InstanceDTO();
		
		dto.setInstID(instance.getInstID());
		dto.setInstLoad(instance.getInstLoad());
		dto.setInstType(instance.getInstanceType().getTypeID());
		
		return dto;
	}

}
