package jp.ac.nii.prl.mape.autoscaling.service;

import jp.ac.nii.prl.mape.autoscaling.model.Adaptation;
import jp.ac.nii.prl.mape.autoscaling.model.Deployment;

public interface AnalysisService {

	public Adaptation analyse(Deployment deployment);
}
