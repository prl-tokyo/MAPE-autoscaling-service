package jp.ac.nii.prl.mape.autoscaling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Adaptation {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private long id;
	
	private boolean adapt;
	
	private boolean up;
	
	@Min(0)
	private int cpuCount;
	
	@JsonIgnore
	@OneToOne
	private Deployment deployment;
	
	public int getCpuCount() {
		return cpuCount;
	}

	public Deployment getDeployment() {
		return deployment;
	}

	public boolean isAdapt() {
		return adapt;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public void setAdapt(final boolean adapt) {
		this.adapt = adapt;
	}
	
	public void setCpuCount(final int cpuCount) {
		this.cpuCount = cpuCount;
	}
	
	public void setDeployment(final Deployment deployment) {
		this.deployment = deployment;
	}
	
	public void setUp(final boolean up) {
		this.up = up;
	}
	

}
