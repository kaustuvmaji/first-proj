package com.example.demo.application;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.application.aop.LogMethodExecution;
import com.example.demo.application.io.EmployeeIO;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

/**
 * This class is hold the actual implementation that domain services provided to
 * other layer of domain driven design. Some of the methods are cachable.
 * 
 * @author KMaji
 */
@Service
public class EmployeeApplicatonService /* implements EmployeeService */ {

	@Autowired
	private EmployeeService employeeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public EmployeeIO getEmployee(Integer id) {
		Employee emp = employeeService.getEmployee(id);
		return new EmployeeIO(emp.getId(), emp.getName(), emp.getDepartment());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#addEmployees(com.example.demo.Employee)
	 */
	@LogMethodExecution
	public EmployeeIO addEmployee(Integer id, String name, String department) {
		Employee emp = employeeService.addEmployee(id, name, department);
		return new EmployeeIO(emp.getId(), emp.getName(), emp.getDepartment());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#deleteEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public void deleteEmployee(Integer id) {
		employeeService.deleteEmployee(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#updateEmployee(java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@LogMethodExecution
	public EmployeeIO updateEmployee(Integer id, String name, String department) {
		Employee emp = employeeService.updateEmployee(id, name, department);
		return /* employees.compute(id, (k, v) -> v.updateEmployee(name, department)) */ new EmployeeIO(emp.getId(),
				emp.getName(), emp.getDepartment());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployees()
	 */
	@Cacheable(value = "employeeCache", sync = true)
	@LogMethodExecution
	public Collection<EmployeeIO> getEmployees() {
		Collection<EmployeeIO> employees = new ArrayList<>();
		for (Employee each : employeeService.getEmployees()) {
			employees.add(new EmployeeIO(each.getId(), each.getName(), each.getDepartment()));
		}
		return employees;
	}
}
