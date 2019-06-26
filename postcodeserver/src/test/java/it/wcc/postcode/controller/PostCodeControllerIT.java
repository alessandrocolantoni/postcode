package it.wcc.postcode.controller;

 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import it.wcc.postcode.config.AppConfigTest;

/**
 * https://developer.okta.com/blog/2019/03/28/test-java-spring-boot-junit5
 * @author alessandro.colantoni
 *
 */
@AutoConfigureMockMvc
@WebMvcTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfigTest.class})
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostCodeControllerIT {

	
	private static final Logger log = LoggerFactory.getLogger(PostCodeControllerIT.class);

	@Autowired
    private MockMvc mockMvc;
	
	//service-account-1:service-account-1-secret@localhost:9090/postcode/oauth/token
	@Test
	public void testHola()  throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hola")
                .with(user("service-account-1"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultHola = result.getResponse().getContentAsString();
        assertNotNull(resultHola, "resultHola is NULL");
        
        assertEquals("hola", resultHola, "hola was expected");
        
        
	        
		 
	}
}
