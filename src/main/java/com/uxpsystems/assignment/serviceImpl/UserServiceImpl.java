package com.uxpsystems.assignment.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import com.uxpsystems.assignment.dao.UserRepository;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;



@Component
public class UserServiceImpl implements UserService{

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	
	@Autowired
	UserRepository repo;
	
   // @CachePut(value = "UserCache")
	@Override
	public void postUser(User u) {
	
		repo.save(u);
		logger.info("Data Saved");	
	}


	@Override
	public List<User> getAllUser() {
		
		List<User> userList =null;
			userList = repo.findAll();
	        
			
		return userList;
	}

	//@CacheEvict(value = "UserCache", key = "#userId")
	//@CachePut(key = "#userId", value = "UserCache", unless = "#result=null")

	@Override
	public String putUser(User u,long userId) {
		
		try {
		User userFound = repo.findById(userId).orElseThrow(()-> new Exception());
	
		userFound.setUsername(u.getUsername());
		userFound.setPassword(u.getPassword());
		userFound.setStatus(u.getStatus());
        repo.save(userFound);
		return "saved";
		}
		
		catch(Exception e)
		{
			
         logger.info("Data not found for id "+ userId);			
		return "fail";
		}
	
		
		
	}

	//@CacheEvict(value = "UserCache", key = "#userId")
	@Override
	public String deleteUser(long userId) {
		try {
			User userFoundForDelete = repo.findById(userId).orElseThrow(()-> new Exception());
		
			repo.delete(userFoundForDelete);
		return "Y";
		}
		
		catch(Exception e)
		{
			
			 logger.info("Data not found for id in DeleteCase "+ userId);			
				
		return "N";
		}
	}

	 //@Cacheable(value = "UserCache", key="#id")
	 @Override
	public User getUser(long id) {
		try {
			User u = repo.findById(id).orElseThrow(()-> new Exception());
	         return u;
		
		}
		
		catch(Exception e)
		{
			
			logger.info("Data not found in getUser for id "+ id);
		return null;
		}

}}
