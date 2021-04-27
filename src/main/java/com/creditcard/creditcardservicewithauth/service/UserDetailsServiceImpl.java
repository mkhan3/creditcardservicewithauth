package com.creditcard.creditcardservicewithauth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.creditcard.creditcardservicewithauth.repository.UserRepository;
import com.creditcard.creditcardservicewithauth.repository.entity.User;

/**
 * The implementation of org.springframework.security.core.userdetails.UserDetailsService.
 * 
 * @author Administrator
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService

{

	@Autowired
	private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username);
        if(user == null)
        {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());

    }

}