package jp.ac.nii.prl.mape.autoscaling.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class InstanceType {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotEmpty
	private String typeID;

	@DecimalMin("1")
	private Integer typeCPUs;
	
	@NotNull
	private Double typeRAM;

	@NotNull
	private Double typeCost;
	
	@OneToMany(mappedBy="instanceType")
	private List<Instance> instances;
	
	@ManyToOne
	private Deployment deployment;

	public Deployment getDeployment() {
		return deployment;
	}

	public long getId() {
		return id;
	}

	public List<Instance> getInstances() {
		return instances;
	}

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

	public void setDeployment(final Deployment deployment) {
		this.deployment = deployment;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public void setInstances(final List<Instance> instances) {
		this.instances = instances;
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
