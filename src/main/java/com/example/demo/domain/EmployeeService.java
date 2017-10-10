package com.example.demo.domain;

import java.util.Collection;

/**
 * Employee domain services.
 * @author KMaji
 *
 */
public interface EmployeeService {
	
	Employee getEmployee(Integer id);

	Employee addEmployee(Integer id, String name, String department);

	Employee deleteEmployee(Integer id);

	Employee updateEmployee(Integer id, String name, String department);

	Collection<Employee> getEmployees();
}
