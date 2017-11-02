package com.example.demo.domain;

import java.util.List;

/**
 * Employee domain services.
 * 
 * @author KMaji
 *
 */
public interface EmployeeService {

	Employee getEmployee(String firstName, String lastName);

	Employee addEmployee(Employee employee);

	Employee deleteEmployee(String firstName, String lastName);

	Employee updateEmployee(Employee employee);

	List<Employee> getEmployees();
}
