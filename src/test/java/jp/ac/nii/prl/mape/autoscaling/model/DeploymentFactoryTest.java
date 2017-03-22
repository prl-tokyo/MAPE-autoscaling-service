package jp.ac.nii.prl.mape.autoscaling.model;

import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceTypeDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeploymentFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateDeploymentFromDTO() {
		final DeploymentDTO ddto = new DeploymentDTO();
		
		final InstanceDTO idto = new InstanceDTO();
		idto.setInstID("inst-1");
		idto.setInstLoad(3.0);
		idto.setInstType("t2.micro");
		
		final InstanceTypeDTO itdto = new InstanceTypeDTO();
		itdto.setTypeCost(0.01);
		itdto.setTypeCPUs(1);
		itdto.setTypeID("t2.micro");
		itdto.setTypeRAM(1.0);
		
		final List<InstanceDTO> idtos = new ArrayList<>();
		idtos.add(idto);
		
		final List<InstanceTypeDTO> itdtos = new ArrayList<>();
		itdtos.add(itdto);
		
		ddto.setInstances(idtos);
		ddto.setInstanceTypes(itdtos);
		
		final Deployment deployment = DeploymentFactory.createDeployment(ddto);
		final Instance instance = deployment.getInstances().get(0);
		final InstanceType instanceType = deployment.getInstanceTypes().get(0);
		
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
	
	@Test
	public void testCreateDeploymentFromDTOMultipleInstances() {
		final DeploymentDTO ddto = new DeploymentDTO();
		
		final InstanceDTO idto = new InstanceDTO();
		idto.setInstID("inst-1");
		idto.setInstLoad(3.0);
		idto.setInstType("t2.micro");
		
		final InstanceDTO idto2 = new InstanceDTO();
		idto2.setInstID("inst-2");
		idto2.setInstLoad(4.0);
		idto2.setInstType("t2.large");
		
		final InstanceDTO idto3 = new InstanceDTO();
		idto3.setInstID("inst-3");
		idto3.setInstLoad(4.0);
		idto3.setInstType("t2.large");
		
		final InstanceDTO idto4 = new InstanceDTO();
		idto4.setInstID("inst-4");
		idto4.setInstLoad(4.0);
		idto4.setInstType("t2.large");
		
		final InstanceTypeDTO itdto = new InstanceTypeDTO();
		itdto.setTypeCost(0.01);
		itdto.setTypeCPUs(1);
		itdto.setTypeID("t2.micro");
		itdto.setTypeRAM(1.0);
		
		final InstanceTypeDTO itdto2 = new InstanceTypeDTO();
		itdto2.setTypeCost(0.04);
		itdto2.setTypeCPUs(2);
		itdto2.setTypeID("t2.large");
		itdto2.setTypeRAM(2.0);
		
		final List<InstanceDTO> idtos = new ArrayList<>();
		idtos.add(idto);
		idtos.add(idto2);
		idtos.add(idto3);
		idtos.add(idto4);
		
		final List<InstanceTypeDTO> itdtos = new ArrayList<>();
		itdtos.add(itdto);
		itdtos.add(itdto2);
		
		ddto.setInstances(idtos);
		ddto.setInstanceTypes(itdtos);
		
		final Deployment deployment = DeploymentFactory.createDeployment(ddto);
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
		
		instance = deployment.getInstances().get(1);
		instanceType = deployment.getInstanceTypes().get(1);
		
		assertEquals(deployment, instance.getDeployment());
		assertEquals(instanceType, instance.getInstanceType());
		assertEquals("inst-2", instance.getInstID());
		assertEquals(4.0, instance.getInstLoad(), 0.0);
		
		assertEquals(deployment, instanceType.getDeployment());
		assertTrue(instanceType.getInstances().contains(instance));
		assertTrue(instanceType.getInstances().contains(deployment.getInstances().get(2)));
		assertTrue(instanceType.getInstances().contains(deployment.getInstances().get(3)));
		assertEquals(3, instanceType.getInstances().size());
		assertEquals(0.04, instanceType.getTypeCost(), 0.0);
		assertEquals(2, (int)instanceType.getTypeCPUs());
		assertEquals("t2.large", instanceType.getTypeID());
		assertEquals(2.0, instanceType.getTypeRAM(), 0.0);
		
		instance = deployment.getInstances().get(2);
		
		assertEquals(deployment, instance.getDeployment());
		assertEquals(instanceType, instance.getInstanceType());
		assertEquals("inst-3", instance.getInstID());
		assertEquals(4.0, instance.getInstLoad(), 0.0);
		
		instance = deployment.getInstances().get(3);
		
		assertEquals(deployment, instance.getDeployment());
		assertEquals(instanceType, instance.getInstanceType());
		assertEquals("inst-4", instance.getInstID());
		assertEquals(4.0, instance.getInstLoad(), 0.0);
	}
	
	@Test
	public void testCreateDTOFromDeployment() {
		final Deployment deployment = new Deployment();
		final List<Instance> instances = new ArrayList<>();
		final List<InstanceType> instanceTypes = new ArrayList<>();
		
		final Instance inst1 = new Instance();
		inst1.setDeployment(deployment);
		inst1.setInstID("inst-1");
		inst1.setInstLoad(3.0);
		instances.add(inst1);
		
		final Instance inst2 = new Instance();
		inst2.setDeployment(deployment);
		inst2.setInstID("inst-2");
		inst2.setInstLoad(3.0);
		instances.add(inst2);
		
		final Instance inst3 = new Instance();
		inst3.setDeployment(deployment);
		inst3.setInstID("inst-3");
		inst3.setInstLoad(3.0);
		instances.add(inst3);
		
		final Instance inst4 = new Instance();
		inst4.setDeployment(deployment);
		inst4.setInstID("inst-4");
		inst4.setInstLoad(3.0);
		instances.add(inst4);
		
		final InstanceType instType1 = new InstanceType();
		instType1.setDeployment(deployment);
		instType1.setTypeCost(0.01);
		instType1.setTypeCPUs(1);
		instType1.setTypeID("t2.micro");
		instType1.setTypeRAM(1.0);
		final List<Instance> instType1Instances = new ArrayList<>();
		instType1Instances.add(inst1);
		inst1.setInstanceType(instType1);
		instType1.setInstances(instType1Instances);
		instanceTypes.add(instType1);
		
		final InstanceType instType2 = new InstanceType();
		instType2.setDeployment(deployment);
		instType2.setTypeCost(0.04);
		instType2.setTypeCPUs(2);
		instType2.setTypeID("t2.large");
		instType2.setTypeRAM(2.0);
		final List<Instance> instType2Instances = new ArrayList<>();
		instType2Instances.add(inst2);
		inst2.setInstanceType(instType2);
		instType2Instances.add(inst3);
		inst3.setInstanceType(instType2);
		instType2Instances.add(inst4);
		inst4.setInstanceType(instType2);
		instType2.setInstances(instType2Instances);
		instanceTypes.add(instType2);
		
		deployment.setInstances(instances);
		deployment.setInstanceTypes(instanceTypes);
		
		final DeploymentDTO dto = DeploymentFactory.createDTO(deployment);
		
		assertEquals(4, dto.getInstances().size());
		assertEquals(2, dto.getInstanceTypes().size());
	}

}
