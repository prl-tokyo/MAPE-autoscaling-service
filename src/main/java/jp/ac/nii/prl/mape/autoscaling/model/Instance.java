package jp.ac.nii.prl.mape.autoscaling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Instance {
	
	@GeneratedValue
	@Id
	private long id;
	
	@NotEmpty
	private String instID;
	
	@NotNull
	private Double instLoad;
	
	@ManyToOne
	private InstanceType instanceType;
	
	@ManyToOne
	private Deployment deployment;

	public Deployment getDeployment() {
		return deployment;
	}

	public long getId() {
		return id;
	}

	public InstanceType getInstanceType() {
		return instanceType;
	}

	public String getInstID() {
		return instID;
	}

	public Double getInstLoad() {
		return instLoad;
	}

	public void setDeployment(final Deployment deployment) {
		this.deployment = deployment;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setInstanceType(final InstanceType instanceType) {
		this.instanceType = instanceType;
	}

	public void setInstID(final String instID) {
		this.instID = instID;
	}

	public void setInstLoad(final Double instLoad) {
		this.instLoad = instLoad;
	}

}
