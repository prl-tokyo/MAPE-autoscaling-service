package jp.ac.nii.prl.mape.autoscaling.model;

import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstanceFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInstanceFromDTO() {
		final InstanceDTO dto = new InstanceDTO();
		dto.setInstID("inst-1");
		dto.setInstLoad(3.0);
		dto.setInstType("t2.micro");
		
		final Deployment deployment = new Deployment();
		
		final Instance inst = InstanceFactory.createInstance(dto, deployment);
		
		assertEquals("inst-1", inst.getInstID());
		assertEquals(3.0, inst.getInstLoad(), 0);
	}
	
	@Test
	public void testInstanceWithTypeFromDTO() {
		final InstanceDTO dto = new InstanceDTO();
		dto.setInstID("inst-1");
		dto.setInstLoad(3.0);
		dto.setInstType("t2.micro");
		
		final Deployment deployment = new Deployment();
		InstanceType instType = new InstanceType();
		instType.setTypeCost(0.01);
		instType.setTypeCPUs(1);
		instType.setTypeID("t2.micro");
		instType.setTypeRAM(2.0);
		
		final Instance inst = InstanceFactory.createInstance(dto, deployment, instType);
		
		assertEquals("inst-1", inst.getInstID());
		assertEquals(3.0, inst.getInstLoad(), 0);
		assertEquals("t2.micro", inst.getInstanceType().getTypeID());
		assertEquals(instType, inst.getInstanceType());
	}
	
	@Test
	public void testDTOFromInstance() {
		final Deployment deployment = new Deployment();
		
		final Instance inst = new Instance();
		inst.setInstID("inst-1");
		inst.setInstLoad(3.0);
		inst.setDeployment(deployment);
		
		final InstanceType instType = new InstanceType();
		instType.setTypeCost(0.01);
		instType.setTypeCPUs(1);
		instType.setTypeID("t2.micro");
		instType.setTypeRAM(3.0);
		
		inst.setInstanceType(instType);
		final List<Instance> instances = new ArrayList<>();
		instances.add(inst);
		instType.setInstances(instances);
		
		deployment.setInstances(instances);
		
		final InstanceDTO dto = InstanceFactory.createDTO(inst);
		
		assertEquals("inst-1", dto.getInstID());
		assertEquals("t2.micro", dto.getInstType());
		assertEquals(3.0, dto.getInstLoad(), 0.0);
	}

}
