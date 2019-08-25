package com.tushar.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.usermanagement.model.User;
import com.tushar.usermanagement.repository.UserManagementRepository;

@Service
public class UserManagementService {

	@Autowired
	private UserManagementRepository userManagementRepository;
	
	public void save(User user) {
		userManagementRepository.save(user);
	}
	
	public List<User> findAll() {
		List<User> user = userManagementRepository.findAll();
		System.out.println(user);
		return user;
	}
}
