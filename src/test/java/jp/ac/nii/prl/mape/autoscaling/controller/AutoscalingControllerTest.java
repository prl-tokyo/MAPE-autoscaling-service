package jp.ac.nii.prl.mape.autoscaling.controller;

import edu.emory.mathcs.backport.java.util.Collections;
import jp.ac.nii.prl.mape.autoscaling.MapeAutoscalingServiceApplication;
import jp.ac.nii.prl.mape.autoscaling.model.dto.DeploymentDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceDTO;
import jp.ac.nii.prl.mape.autoscaling.model.dto.InstanceTypeDTO;
import org.apache.commons.httpclient.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MapeAutoscalingServiceApplication.class)
@WebAppConfiguration
public class AutoscalingControllerTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCreateDeployment() {
	    final DeploymentDTO deployment = buildEmptyDeploymentDTO();
		given().body(deployment).post("/autoscaling").then().assertThat().statusCode(HttpStatus.SC_CREATED);
	}

	@Test
    public void testCreateGarbageDeployment() {
	    given().body("{hello}")
                .post("/autoscaling")
                .then().assertThat().statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }
	
	protected String json(final Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    private static DeploymentDTO buildEmptyDeploymentDTO() {
	    final InstanceDTO instance = new InstanceDTO();
	    instance.setInstID("1");
	    instance.setInstLoad(0.5);
	    instance.setInstType("t2.micro");

        final InstanceTypeDTO instanceType = new InstanceTypeDTO();
        instanceType.setTypeID("t2.micro");
        instanceType.setTypeCost(0.01);
        instanceType.setTypeCPUs(1);
        instanceType.setTypeRAM(0.5);

	    final DeploymentDTO deployment = new DeploymentDTO();
	    deployment.setInstances(Collections.singletonList(instance));
	    deployment.setInstanceTypes(Collections.singletonList(instanceType));

	    return deployment;
    }

}
