package com.creditcard.creditcardservicewithauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.creditcard.creditcardservicewithauth.dtos.UserDto;
import com.creditcard.creditcardservicewithauth.repository.UserRepository;
import com.creditcard.creditcardservicewithauth.repository.entity.User;

/**
 * User service for saving or finding user
 * @author Administrator
 *
 */
@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/*
	 * public User findByUser(String name) { return
	 * userRepository.findByUsername(name); }
	 */
	
	public String saveDto(UserDto userDto) { 
		//the saved password will be encrypted
	    userDto.setPassword(bCryptPasswordEncoder
	           .encode(userDto.getPassword())); 
	    
	    return userRepository.save(new User(userDto)).getUsername();
	}
	
}
