package com.imaginea.autocomplete.inbound;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AutoCompleteControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetCities() throws Exception {
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/suggest_cities?start=Delhi", String.class);
		String expected="Delhi"+System.lineSeparator()+"Delhi North East";
		assertThat(responseEntity.getBody()).isEqualTo(expected);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void testGetCitiesWithLimit() throws Exception {
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/suggest_cities?start=D&atmost=5", String.class);
		String expected="D B Nagar"+System.lineSeparator()+"D B Nagr"+System.lineSeparator()+"D.b.j.singh."+System.lineSeparator()+"D.gannavaram Mandal"+System.lineSeparator()+"D.Hirehal";
		assertThat(responseEntity.getBody()).isEqualTo(expected);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	@Test
	public void testInvalidStartParameter() throws Exception {
		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/suggest_cities?start=D!", String.class);
		System.out.print(responseEntity.getBody());
		assertThat(responseEntity.getBody()).contains("Start should not contains special character");
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

}
