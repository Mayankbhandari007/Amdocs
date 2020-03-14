package com.uxpsystems.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.model.User;

@Service
public interface UserService {

	List<User> getAllUser();
	void postUser(User u);
	String putUser(User u, long userId);
	String deleteUser(long userId);
	User getUser(long userId);
	
	
}
