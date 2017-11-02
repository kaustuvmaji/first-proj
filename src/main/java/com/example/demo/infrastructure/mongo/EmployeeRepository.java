package com.example.demo.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Employee findByFirstName(String firstName);

	@Query("{$or :[{firstName:{'$regex' : '^?0', '$options' : 'i'}},{lastName:{'$regex' : '^?1', '$options' : 'i'}}]}")
	Employee findByFirstNameOrLastName(String firstName, String lastName);

}
