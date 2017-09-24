package com.example.demo.application;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.application.aop.LogMethodExecution;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

/**
 * This class is hold the actual implementation that domain services provided to
 * other layer of domain driven design. Some of the methods are cachable.
 * 
 * @author KMaji
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeServiceImpl() {
		populate();
	}

	/*
	 * (non-Javadoc)
	 * @see com.example.demo.EmployeeService#getEmployee(java.lang.Integer)
	 */
	@Cacheable(value = "employeeCache", key = "#id", sync = true)
	@Override
	@LogMethodExecution
	public Employee getEmployee(Integer id) {
		return employees.get(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.example.demo.EmployeeService#addEmployees(com.example.demo.Employee)
	 */
	@Override
	@LogMethodExecution
	public Employee addEmployee(Integer id, String name, String department) {
		return employees.put(id, new Employee(id, name, department));
	}

	/*
	 * (non-Javadoc)
	 * @see com.example.demo.EmployeeService#deleteEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public void deleteEmployee(Integer id) {
		employees.remove(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.example.demo.EmployeeService#updateEmployee(java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@LogMethodExecution
	public Employee updateEmployee(Integer id, String name, String department) {
		return employees.compute(id, (k, v) -> v.updateEmployee(name, department));
	}

	/*
	 * (non-Javadoc)
	 * @see com.example.demo.EmployeeService#getEmployees()
	 */
	@Cacheable(value = "employeeCache", sync = true)
	@LogMethodExecution
	public Collection<Employee> getEmployees() {
		return employees.values();
	}
}
