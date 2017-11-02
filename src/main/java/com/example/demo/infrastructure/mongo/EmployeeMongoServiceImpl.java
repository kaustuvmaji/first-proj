package com.example.demo.infrastructure.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
		List<Assignment> assignments = new ArrayList<>();
	
		List<ContactDetail> contactDetails = new ArrayList<>();
		ContactDetail cd = new ContactDetail();
		cd.setEmailId("kaustuv.maji@gmail.com");
		Assignment asg = new Assignment();
		Date endDate = new Date();
		asg.setEndDate(endDate );
		asg.setPerformanceMark(100l);
		Date startDate = new Date();
		asg.setStartDate(startDate );
		assignments.add(asg);
		contactDetails.add(cd);
		employee.setAssignments(assignments);
		employee.setContactDetails(contactDetails );
		return employeeMongoRepsitory.save(employee);
	}

	@Override
	public Employee deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public Employee addEmployee(Integer id, String name, String department) {
	// return employeeMongoRepsitory.save(new Employee(id, name, department));
	// }
	//
	// @Override
	// public Employee deleteEmployee(Integer id) {
	// Employee emp = employeeMongoRepsitory.findById(id);
	// employeeMongoRepsitory.delete(emp);
	// return emp;
	//
	// }
	//
	// @Override
	// public Employee updateEmployee(Integer id, String name, String department) {
	// return employeeMongoRepsitory.save(new Employee(id, name, department));
	// }
	//
	// @Override
	// // @Cacheable(value = "employeeCache",sync = true)
	// public Collection<Employee> getEmployees() {
	// return employeeMongoRepsitory.findAll();
	// }

}
