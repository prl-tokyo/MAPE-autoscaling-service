package jp.ac.nii.prl.mape.autoscaling.model;

import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceTypeDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstanceTypeFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInstanceTypeFromDTO() {
		final InstanceTypeDTO dto = new InstanceTypeDTO();
		dto.setTypeCost(0.01);
		dto.setTypeCPUs(1);
		dto.setTypeID("t2.micro");
		dto.setTypeRAM(1.0);
		
		final Deployment deployment = new Deployment();
		final List<Instance> instances = new ArrayList<>();

		final InstanceType insType = InstanceTypeFactory.createInstanceType(dto, deployment, instances);
		
		assertEquals(0.01, insType.getTypeCost(), 0.0);
		assertEquals(1, (int)insType.getTypeCPUs());
		assertEquals("t2.micro", insType.getTypeID());
		assertEquals(1.0, insType.getTypeRAM(), 0.0);
		assertEquals(deployment, insType.getDeployment());
		assertEquals(instances, insType.getInstances());
	}
	
	@Test
	public void testDTOFromInstanceType() {
		final InstanceType instType = new InstanceType();
		instType.setTypeCost(0.01);
		instType.setTypeCPUs(1);
		instType.setTypeID("t2.micro");
		instType.setTypeRAM(1.0);

		final Deployment deployment = new Deployment();
		instType.setDeployment(deployment);
		final List<Instance> instances = new ArrayList<>();
		instType.setInstances(instances);

		final InstanceTypeDTO dto = InstanceTypeFactory.createDTO(instType);
		
		assertEquals(0.01, dto.getTypeCost(), 0.0);
		assertEquals(1, (int)dto.getTypeCPUs());
		assertEquals("t2.micro", dto.getTypeID());
		assertEquals(1.0, dto.getTypeRAM(), 0.0);
	}

}
