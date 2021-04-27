package com.creditcard.creditcardservicewithauth.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.creditcard.creditcardservicewithauth.CreditcardservicewithauthApplication;
import com.creditcard.creditcardservicewithauth.dtos.CreditCardDto;
import com.creditcard.creditcardservicewithauth.dtos.UserDto;

/**
 * Integration tests using SpringBootTest. SpringBootTest will load the context for integration testing.
 * mvn verify - maven command to run these tests by build tool.
 * 
 * @author Administrator
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CreditcardservicewithauthApplication.class)
public class IntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	private String token;
	private HttpHeaders headers;

	
	@BeforeEach
	public void setUp() {
		
		UserDto userDto = new UserDto("test", "test123");
		restTemplate.postForEntity("/registration", userDto, Object.class);
		ResponseEntity<String> resEntity2 = restTemplate.postForEntity("/login", userDto, String.class);
		token = (String) resEntity2.getBody();
		headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
	}
	
	@Test
	public void postTestIncorrectCreditcardNumber() {
		CreditCardDto creditCrad = new CreditCardDto("errerr", "10009999999999", 0.0, 5000.0);
		HttpEntity<CreditCardDto> entity = new HttpEntity<CreditCardDto>(creditCrad, headers);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", entity, Object.class);
		assertEquals(HttpStatus.NOT_ACCEPTABLE, resEntity.getStatusCode());

	}
	
	@Test
	public void postTestCreditCardCreated() {
		
		CreditCardDto creditCrad = new CreditCardDto("errerr", "79927398713", 0.0, 5000.0);
		HttpEntity<CreditCardDto> entity = new HttpEntity<CreditCardDto>(creditCrad, headers);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", entity, Object.class);
		assertEquals(HttpStatus.CREATED, resEntity.getStatusCode());
		
	}
	
	
	
	@Test
	public void postTestConflict() {
		CreditCardDto creditCrad = new CreditCardDto("errerr", "1358954993914435", 0.0, 5000.0);
		HttpEntity<CreditCardDto> entity = new HttpEntity<CreditCardDto>(creditCrad, headers);
		restTemplate.postForEntity("/creditcards", entity, Object.class);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", entity, Object.class);
		assertEquals(HttpStatus.CONFLICT, resEntity.getStatusCode());

	}

	@Test
	public void postTestValidationError() {
		CreditCardDto creditCrad = new CreditCardDto("", "1234567890123", 0.0, 5000.0);
		HttpEntity<CreditCardDto> entity = new HttpEntity<CreditCardDto>(creditCrad, headers);
		restTemplate.postForEntity("/creditcards", creditCrad, Object.class);
		ResponseEntity<Object> resEntity = restTemplate.postForEntity("/creditcards", entity, Object.class);
		assertEquals(HttpStatus.BAD_REQUEST, resEntity.getStatusCode());

	}
	 
	  
	@Test
	public void getTestStatusCheck() {
		ResponseEntity<Object> resEntity = restTemplate.exchange("/creditcards", HttpMethod.GET, new HttpEntity<Object>(headers), Object.class);
		assertEquals(HttpStatus.OK, resEntity.getStatusCode());
	}
	 
}
