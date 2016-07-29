package jp.ac.nii.prl.mape.autoscaling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	public int getCpuCount() {
		return cpuCount;
	}
	
	public boolean isAdapt() {
		return adapt;
	}
	
	public boolean isUp() {
		return up;
	}
	
	public void setAdapt(boolean adapt) {
		this.adapt = adapt;
	}
	
	public void setCpuCount(int cpuCount) {
		this.cpuCount = cpuCount;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	

}
