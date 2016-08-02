package jp.ac.nii.prl.mape.autoscaling.model;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Deployment {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	@NotEmpty
	@OneToMany(mappedBy="deployment")
	private List<Instance> instances;
	
	@NotEmpty
	@OneToMany(mappedBy="deployment")
	private List<InstanceType> instanceTypes;
	
	@OneToOne
	@JsonIgnore
	private Adaptation adaptation;

	public Adaptation getAdaptation() {
		return adaptation;
	}

	public Long getId() {
		return id;
	}

	public List<Instance> getInstances() {
		return instances;
	}

	public List<InstanceType> getInstanceTypes() {
		return instanceTypes;
	}

	public void setAdaptation(Adaptation adaptation) {
		this.adaptation = adaptation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}

	public void setInstanceTypes(List<InstanceType> instanceTypes) {
		this.instanceTypes = instanceTypes;
	}
}
