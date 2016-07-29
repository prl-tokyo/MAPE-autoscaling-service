package jp.ac.nii.prl.mape.autoscaling.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceTypeDTO;

public class DeploymentFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateDeploymentFromDTO() {
		DeploymentDTO ddto = new DeploymentDTO();
		
		InstanceDTO idto = new InstanceDTO();
		idto.setInstID("inst-1");
		idto.setInstLoad(3.0);
		idto.setInstType("t2.micro");
		
		InstanceTypeDTO itdto = new InstanceTypeDTO();
		itdto.setTypeCost(0.01);
		itdto.setTypeCPUs(1);
		itdto.setTypeID("t2.micro");
		itdto.setTypeRAM(1.0);
		
		List<InstanceDTO> idtos = new ArrayList<>();
		idtos.add(idto);
		
		List<InstanceTypeDTO> itdtos = new ArrayList<>();
		itdtos.add(itdto);
		
		ddto.setInstances(idtos);
		ddto.setInstanceTypes(itdtos);
		
		Deployment deployment = DeploymentFactory.createDeployment(ddto);
		Instance instance = deployment.getInstances().get(0);
		InstanceType instanceType = deployment.getInstanceTypes().get(0);
		
		assertEquals(deployment, instance.getDeployment());
		assertEquals(instanceType, instance.getInstanceType());
		assertEquals("inst-1", instance.getInstID());
		assertEquals(3.0, instance.getInstLoad(), 0.0);
		
		assertEquals(deployment, instanceType.getDeployment());
		assertEquals(instance, instanceType.getInstances().get(0));
		assertEquals(0.01, instanceType.getTypeCost(), 0.0);
		assertEquals(1, (int)instanceType.getTypeCPUs());
		assertEquals("t2.micro", instanceType.getTypeID());
		assertEquals(1.0, instanceType.getTypeRAM(), 0.0);
	}

}
