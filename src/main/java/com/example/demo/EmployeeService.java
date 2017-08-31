package com.example.demo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface EmployeeService {
	Map<Integer, Employee> employees = new HashMap<>();
	Employee getEmployee(Integer id);
	Employee addEmployees(Employee newEmp);
	Employee deleteEmployee(Integer id);
	Employee updateEmployee(Integer id, String name, String department);
	Collection<Employee> getEmployees() ;
}
