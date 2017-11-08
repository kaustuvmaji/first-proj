package com.example.demo.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.application.io.EmployeeCMD;
import com.example.demo.application.io.EmployeeData;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;
import com.example.demo.domain.util.LogMethodExecution;
import com.example.demo.infrastructure.notification.email.EmailMessage;
import com.example.demo.infrastructure.notification.email.EmailNotificationService;

/**
 * This class is hold the actual implementation that domain services provided to
 * other layer of domain driven design. Some of the methods are cachable.
 * 
 * @author KMaji
 */
@Service
public class EmployeeApplicatonService {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmailNotificationService emailNotificationService;

	private void domainToData(Employee emp, EmployeeData empIO) {
		empIO.setEmployeeId(emp.getDocumentId());
		empIO.setFirstName(emp.getFirstName());
		empIO.setSecondName(emp.getSecondName());
		empIO.setDepartment(emp.getDepartment());
		empIO.setSalary(emp.getSalary());
		empIO.setDateOfBirth(emp.getDateOfBirth());
		empIO.setContactDetails(emp.getContactDetails());
		// empIO.setAssignments(emp.getAssignments());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.demo.EmployeeService#getEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public EmployeeData getEmployee(String firstName, String lastName) {
		Employee emp = employeeService.getEmployee(firstName, lastName);
		EmployeeData empIO = new EmployeeData();
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
	public EmployeeData addEmployee(EmployeeCMD empIO) {
		Employee emp = employeeService.addEmployee(new Employee(empIO.getFirstName(), empIO.getSecondName(),
				empIO.getDateOfBirth(), empIO.getContactDetails(), empIO.getDepartment(), empIO.getSalary()));
		EmployeeData empData = new EmployeeData();
		if (null != empData) {
			domainToData(emp, empData);
		}
		if (!CollectionUtils.isEmpty(emp.getContactDetails())) {
			EmailMessage email = new EmailMessage("Welcome a board !!!", "employeeregistration.vm",
					emp.getContactDetails().get(0).getEmailId());
			Map<String, String> propertyHolder = new HashMap<>();
			propertyHolder.put("firstName", emp.getFirstName());
			propertyHolder.put("lastName", emp.getSecondName());
			propertyHolder.put("department", emp.getDepartment());
			propertyHolder.put("sender", "Admin HR");
			propertyHolder.put("employeeId", emp.getDocumentId());
			email.setPropertyHolder(propertyHolder);
			emailNotificationService.sendSimpleMail(email);
		}

		return empData;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.example.demo.EmployeeService#deleteEmployee(java.lang.Integer)
	 */
	@LogMethodExecution
	public void deleteEmployee(String firstName, String lastName) {
		employeeService.deleteEmployee(firstName, lastName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.example.demo.EmployeeService#updateEmployee(java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@LogMethodExecution
	public EmployeeData updateEmployee(EmployeeCMD empIO) {
		Employee emp = employeeService.updateEmployee(new Employee(empIO.getFirstName(), empIO.getSecondName(),
				empIO.getDateOfBirth(), empIO.getContactDetails(), empIO.getDepartment(), empIO.getSalary()));
		EmployeeData empData = new EmployeeData();
		if (null != empData) {
			domainToData(emp, empData);
		}
		return empData;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.example.demo.EmployeeService#getEmployees()
	 */
	@Cacheable(value = "employeeCache", sync = true)
	@LogMethodExecution
	public Collection<EmployeeData> getEmployees() {
		Collection<EmployeeData> employees = new ArrayList<>();
		for (Employee each : employeeService.getEmployees()) {
			employees.add(new EmployeeData(each.getDocumentId(), each.getFirstName(), each.getSecondName(),
					each.getDateOfBirth(), each.getContactDetails(), each.getDepartment(), each.getSalary(), null));
		}
		return employees;
	}
}
