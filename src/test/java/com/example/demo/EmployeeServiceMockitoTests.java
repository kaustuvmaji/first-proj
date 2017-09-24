package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

import org.junit.Assert;

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
		Mockito.when(employeeService.getEmployee(1)).thenReturn(new Employee(1, "Josh", "dev"));
		Mockito.when(employeeService.getEmployee(2)).thenReturn(new Employee(2, "Rev", "qa"));
		Assert.assertEquals("Josh", employeeService.getEmployee(1).getName());
		// Mockito.verify(employeeService,
		// Mockito.times(1)).count(Mockito.any(CriteriaDto.class));
	}

	@Test
	public void testAddEmployee() {
		Employee newlyAdded = new Employee(2, "Rev", "qa");
		Mockito.when(employeeService.addEmployee(2, "Rev", "qa")).thenReturn(newlyAdded);
		Employee addEmployees = employeeService.addEmployee(2, "Rev", "qa");
		Assert.assertEquals("Rev", addEmployees.getName());
		Assert.assertEquals("qa", addEmployees.getDepartment());
	}

	@Test
	public void testUpdateEmployee() {
		Employee newlyAdded = new Employee(2, "Rev", "dev");
		Mockito.when(employeeService.updateEmployee(2, "rev", "dev")).thenReturn(newlyAdded);
		Employee updateEmployees = employeeService.updateEmployee(2, "rev", "dev");
		Assert.assertEquals("Rev", updateEmployees.getName());
		Assert.assertNotEquals("qa", updateEmployees.getName());
		Assert.assertEquals("dev", updateEmployees.getDepartment());
	}
}
