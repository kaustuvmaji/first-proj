package com.example.demo.infrastructure.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Assignment;
import com.example.demo.domain.ContactDetail;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

@Service
public class EmployeeMongoServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeMongoRepsitory;

	@Override
	// @Cacheable(value = "employeeCache", key = "#{employee.objectId}", sync =
	// true)
	public Employee getEmployee(String firstName, String lastName) {
		return employeeMongoRepsitory.findByFirstNameOrLastName(firstName, lastName);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeMongoRepsitory.save(employee);
	}

	@Override
	public Employee deleteEmployee(String firstName, String lastName) {
		Employee deleteableEmployee = employeeMongoRepsitory.findByFirstNameOrLastName(firstName, lastName);
		// delete
		employeeMongoRepsitory.delete(deleteableEmployee);
		return deleteableEmployee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee updateableEmployee = employeeMongoRepsitory.findByFirstNameOrLastName(employee.getFirstName(),
				employee.getSecondName());
		List<Assignment> existingAssignments = updateableEmployee.getAssignments();
//		for (Assignment newAssign : employee.getAssignments()) {
//			Iterator<Assignment> iterators = existingAssignments.iterator();
//			for (Assignment each : existingAssignments) {
//				if (each.getProjectCode().equalsIgnoreCase(newAssign.getProjectCode())) {
//					each.updateAssignment(newAssign);
//				}else {
//					employee
//				}
//			}
//		}

		List<ContactDetail> contactDetails = updateableEmployee.getContactDetails();
		return null;
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeMongoRepsitory.findAll();
	}
}
