package jp.ac.nii.prl.mape.autoscaling.service;

import jp.ac.nii.prl.mape.autoscaling.model.Adaptation;
import jp.ac.nii.prl.mape.autoscaling.model.Deployment;

public class PlanningServiceImpl implements PlanningService {

	@Override
	public Deployment plan(final Deployment deployment, final Adaptation adaptation) {
		assert(deployment != null && adaptation != null);
		
		if (!adaptation.isAdapt())
			return deployment;
		return null;
	}

}
