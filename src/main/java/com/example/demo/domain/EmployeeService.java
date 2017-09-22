package com.example.demo.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Employee domain services.
 * @author KMaji
 *
 */
public interface EmployeeService {
	
	Map<Integer, Employee> employees = new HashMap<>();

	Employee getEmployee(Integer id);

	Employee addEmployees(Employee newEmp);

	void deleteEmployee(Integer id);

	Employee updateEmployee(Integer id, String name, String department);

	Collection<Employee> getEmployees();
	
	default void populate(){
		employees.put(1, new Employee(1, "Josh", "dev"));
		employees.put(2, new Employee(2, "Rev", "qa"));
		employees.put(3, new Employee(3, "Kaustuv", "dev"));
		employees.put(4, new Employee(4, "Sam", "Hr"));
	}
}
