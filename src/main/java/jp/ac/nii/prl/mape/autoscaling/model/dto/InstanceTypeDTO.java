package jp.ac.nii.prl.mape.autoscaling.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class InstanceTypeDTO {

	@NotEmpty
	private String typeID;

	@DecimalMin("1")
	private Integer typeCPUs;
	
	@NotNull
	private Double typeRAM;

	@NotNull
	private Double typeCost;

	public Double getTypeCost() {
		return typeCost;
	}

	public Integer getTypeCPUs() {
		return typeCPUs;
	}

	public String getTypeID() {
		return typeID;
	}

	public Double getTypeRAM() {
		return typeRAM;
	}

	public void setTypeCost(final Double typeCost) {
		this.typeCost = typeCost;
	}

	public void setTypeCPUs(final Integer typeCPUs) {
		this.typeCPUs = typeCPUs;
	}

	public void setTypeID(final String typeID) {
		this.typeID = typeID;
	}

	public void setTypeRAM(final Double typeRAM) {
		this.typeRAM = typeRAM;
	}
}
