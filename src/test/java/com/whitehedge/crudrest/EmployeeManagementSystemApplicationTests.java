package com.whitehedge.crudrest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;





@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeManagementSystemApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeManagementSystemApplicationTests {
	
	
	@Autowired
    private TestRestTemplate restTemplate;

	
	@LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
	@Test
	void contextLoads() {
	}

	 @Test
	    public void testGetAllUsers() {
	         HttpHeaders headers = new HttpHeaders();
	         HttpEntity<String> entity = new HttpEntity<String>(null, headers);

	         ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
	         HttpMethod.GET, entity, String.class);
	  
	         assertNotNull(response.getBody());
	    }
	
	
	
	
	
	
}
