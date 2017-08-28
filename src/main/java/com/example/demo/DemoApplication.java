package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
		return new RestAppSecurity();
	}
	
	@RequestMapping("/")
	@ResponseBody
	String home() {

		StringBuffer welcome = new StringBuffer();
		welcome.append("<center><h1>Welcome to Spring boot example. !!!</h1></center></br>");
		welcome.append(" This poc will cover spring boot app with scrud examples. </br>");
		welcome.append(" Default media type is jason </br>");
		welcome.append("List of services and urls are as following </br>");
		welcome.append("<ul>");
		welcome.append("<li> List of Employees url will be  Example of Read -> /listOfEmployee </li>");
		welcome.append("<li> Employee Detail url will be id match Example of Read -> /employeeDetail </li>");
		welcome.append("<li> Add employee  url will be id match Example of Create-> /addEmployee </li>");
		welcome.append("<li> Add employee  url will be id match Example of Update-> /updateEmployee </li>");
		welcome.append("<li> Add employee  url will be id match Example of delete-> /deleteEmployee </li>");
		welcome.append("</ul>");
		welcome.append("Date -> " + LocalDate.now() + " :: " + LocalTime.now());
		return welcome.toString();
	}


}
