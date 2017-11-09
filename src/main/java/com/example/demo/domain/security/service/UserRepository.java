package com.example.demo.domain.security.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.security.AppUser;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {

	AppUser findByUserName(String userName);
	
}
