package jp.ac.nii.prl.mape.autoscaling.controller;

import org.springframework.web.client.RestClientException;

public class DeploymentNotFoundException extends RestClientException {

	private static final long serialVersionUID = 1L;

	public DeploymentNotFoundException(final String msg) {
		super(msg);
	}

}
