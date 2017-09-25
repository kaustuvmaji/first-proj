package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
		new Employee(2, "Rev", "qa");
		Employee addEmployees = employeeService.addEmployee(2, "Rev", "qa");
		Assert.assertEquals("Rev", addEmployees.getName());
		Assert.assertEquals("qa", addEmployees.getDepartment());
	}

	@Test
	public void testUpdateEmployee() {
		Employee updateEmployees = employeeService.updateEmployee(2, "Rev", "dev");
		Assert.assertEquals("Rev", updateEmployees.getName());
		Assert.assertNotEquals("qa", updateEmployees.getName());
		Assert.assertEquals("dev", updateEmployees.getDepartment());
	}
}
