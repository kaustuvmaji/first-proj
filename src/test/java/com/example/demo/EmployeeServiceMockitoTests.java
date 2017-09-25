package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceMockitoTests {

	private final Employee rev = new Employee(2, "Rev", "qa");
	@Autowired
	EmployeeService employeeService;

	public EmployeeServiceMockitoTests() {
	}

	@Test
	public void testGetEmployee() {
		Assert.assertEquals("Josh", employeeService.getEmployee(1).getName());
	}

	@Test
	public void testAddEmployee() {
		Employee addEmployees = employeeService.addEmployee(rev.getId(), rev.getName(), rev.getDepartment());
		Assert.assertEquals(rev.getName(), addEmployees.getName());
		Assert.assertEquals(rev.getDepartment(), addEmployees.getDepartment());
	}

	@Test
	public void testUpdateEmployee() {
		Employee updateEmployees = employeeService.updateEmployee(rev.getId(), rev.getName(), "dev");
		Assert.assertNotEquals(rev.getDepartment(), updateEmployees.getName());
		Assert.assertEquals("dev", updateEmployees.getDepartment());
	}
}
