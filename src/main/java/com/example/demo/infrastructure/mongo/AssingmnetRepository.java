package com.example.demo.infrastructure.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Assignment;

@Repository
public interface AssingmnetRepository extends MongoRepository<Assignment, String> {

	public List<Assignment> findByEmployeeId(String employeeId);
}
