package com.tushar.usermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tushar.usermanagement.model.User;

public interface UserManagementRepository extends MongoRepository<User, Integer> {
	
	User findByEmail(String email);

}
