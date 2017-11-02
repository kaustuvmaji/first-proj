package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
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

	private void domainToData(Employee emp, EmployeeIO empIO) {
		empIO.setObjectId(emp.getObjectId());
		empIO.setFirstName(emp.getFirstName());
		empIO.setSecondName(emp.getSecondName());
		empIO.setDepartment(emp.getDepartment());
		empIO.setSalary(emp.getSalary());
		empIO.setDateOfBirth(emp.getDateOfBirth());
		empIO.setContactDetails(emp.getContactDetails());
		empIO.setAssignments(emp.getAssignments());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public EmployeeIO getEmployee(String firstName, String lastName) {
		Employee emp = employeeService.getEmployee(firstName, lastName);
		EmployeeIO empIO = new EmployeeIO();
		if (null != emp) {
			domainToData(emp, empIO);
		}
		return empIO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#addEmployees(com.example.demo.Employee)
	 */
	@LogMethodExecution
	public EmployeeIO addEmployee(EmployeeIO empIO) {
		Employee emp = employeeService.addEmployee(new Employee(empIO.getFirstName(), empIO.getSecondName(),
				empIO.getDateOfBirth(), empIO.getContactDetails(), empIO.getDepartment(), empIO.getSalary(),
				empIO.getAssignments()));
		empIO = new EmployeeIO();
		if (null != emp) {
			domainToData(emp, empIO);
		}
		return empIO;
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see com.example.demo.EmployeeService#deleteEmployee(java.lang.Integer)
	// */
	// @LogMethodExecution
	// public void deleteEmployee(Integer id) {
	// employeeService.deleteEmployee(id);
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see com.example.demo.EmployeeService#updateEmployee(java.lang.Integer,
	// * java.lang.String, java.lang.String)
	// */
	// @LogMethodExecution
	// public EmployeeIO updateEmployee(Integer id, String name, String department)
	// {
	// Employee emp = employeeService.updateEmployee(id, name, department);
	// return /* employees.compute(id, (k, v) -> v.updateEmployee(name, department))
	// */ new EmployeeIO(emp.getId(),
	// emp.getName(), emp.getDepartment());
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see com.example.demo.EmployeeService#getEmployees()
	// */
	// @Cacheable(value = "employeeCache", sync = true)
	// @LogMethodExecution
	// public Collection<EmployeeIO> getEmployees() {
	// Collection<EmployeeIO> employees = new ArrayList<>();
	// for (Employee each : employeeService.getEmployees()) {
	// employees.add(new EmployeeIO(each.getId(), each.getName(),
	// each.getDepartment()));
	// }
	// return employees;
	// }
}
