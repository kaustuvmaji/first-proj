package com.example.demo.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
	Employee findById(Integer id);
}
