package jp.ac.nii.prl.mape.autoscaling.model.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class InstanceDTO {
	
	@NotEmpty
	private String instID;
	
	@NotEmpty
	private String instType;
	
	@NotNull
	private Double instLoad;

	public String getInstID() {
		return instID;
	}

	public Double getInstLoad() {
		return instLoad;
	}

	public String getInstType() {
		return instType;
	}

	public void setInstID(String instID) {
		this.instID = instID;
	}

	public void setInstLoad(Double instLoad) {
		this.instLoad = instLoad;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}

}
