package com.example.demo.infrastructure.mongo;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeService;

@Service
public class EmployeeMongoServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeMongoRepsitory;

	@Override
	@Cacheable(value = "employeeCache", key = "#id", sync = true)
	public Employee getEmployee(Integer id) {
		return employeeMongoRepsitory.findById(id);
	}

	@Override
	public Employee addEmployee(Integer id, String name, String department) {
		return employeeMongoRepsitory.save(new Employee(id, name, department));
	}

	@Override
	public Employee deleteEmployee(Integer id) {
		Employee emp = employeeMongoRepsitory.findById(id);
		employeeMongoRepsitory.delete(emp);
		return emp;

	}

	@Override
	public Employee updateEmployee(Integer id, String name, String department) {
		return employeeMongoRepsitory.save(new Employee(id, name, department));
	}

	@Override
//	@Cacheable(value = "employeeCache",sync = true)
	public Collection<Employee> getEmployees() {
		return employeeMongoRepsitory.findAll();
	}

}
