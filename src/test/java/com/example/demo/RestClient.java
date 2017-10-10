package com.example.demo;

import java.util.Arrays;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	private static final Logger LOG = Logger.getLogger(RestClient.class);

	static {
		LOG.getRootLogger().setLevel(Level.ALL);
		BasicConfigurator.configure();
	}

	public static void main(String args[]) {
		
		final String uri = "http://springbootrestexample.herokuapp.com/employee/services/listOfEmployee";
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		// rest security 
		String plainCreds = "kaustuv:pass@123";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encode(plainCredsBytes);
		
		String base64Creds = new String(base64CredsBytes);
		{
			headers.add("Authorization", "Basic " + base64Creds);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		}
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		LOG.info(result);
		
	}

	
}
