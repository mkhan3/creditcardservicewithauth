package com.creditcard.creditcardservicewithauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.creditcardservicewithauth.repository.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);
}
