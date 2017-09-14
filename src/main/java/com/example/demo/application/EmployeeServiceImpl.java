package com.example.demo.application;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.demo.application.aop.LogMethodExecution;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

/**
 * 
 * @author KMaji
 *
 */
@Component
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeServiceImpl() {
		populate();
	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * 
	 * @see com.example.demo.EmployeeService#addEmployees(com.example.demo.Employee)
	 */
	@Override
	@LogMethodExecution
	public Employee addEmployees(Employee newEmp) {
		return employees.put(newEmp.getId(), newEmp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#deleteEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public void deleteEmployee(Integer id) {
		employees.remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#updateEmployee(java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@LogMethodExecution
	public Employee updateEmployee(Integer id, String name, String department) {
		return employees.compute(id, (k, v) -> v.updateEmployee(name, department));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployees()
	 */
	@Cacheable(value = "employeeCache", sync = true)
	@LogMethodExecution
	public Collection<Employee> getEmployees() {
		return employees.values();
	}
}
