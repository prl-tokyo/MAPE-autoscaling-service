package jp.ac.nii.prl.mape.autoscaling.model.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class DeploymentDTO {
	
	@NotEmpty
	private List<InstanceDTO> instances;
	
	@NotEmpty
	private List<InstanceTypeDTO> instanceTypes;

	public List<InstanceDTO> getInstances() {
		return instances;
	}

	public void setInstances(List<InstanceDTO> instances) {
		this.instances = instances;
	}

	public List<InstanceTypeDTO> getInstanceTypes() {
		return instanceTypes;
	}

	public void setInstanceTypes(List<InstanceTypeDTO> instanceTypes) {
		this.instanceTypes = instanceTypes;
	}


}
