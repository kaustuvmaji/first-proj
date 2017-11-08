package com.example.demo.infrastructure.mongo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ContactDetail;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

@Service
public class EmployeeMongoServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeMongoRepsitory;

	@Override
	@Cacheable(value = "employeeCache", key = "employee.documentId", sync = true)
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

		List<ContactDetail> existingContactDetails = new CopyOnWriteArrayList<>(updateableEmployee.getContactDetails());

		for (ContactDetail contactDetail : existingContactDetails) {
			for (ContactDetail newContact : employee.getContactDetails()) {
				if (contactDetail.getPhoneNumber().equals(newContact.getPhoneNumber())) {
					contactDetail.updateContactDetail(newContact);
				} else {
					existingContactDetails.add(newContact);
				}
			}
		}
		return employeeMongoRepsitory.save(updateableEmployee);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeMongoRepsitory.findAll();
	}
}
