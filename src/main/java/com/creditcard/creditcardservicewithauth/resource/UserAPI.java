package com.creditcard.creditcardservicewithauth.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.creditcardservicewithauth.dtos.UserDto;
import com.creditcard.creditcardservicewithauth.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * User Registration for authentication
 * @author Administrator
 *
 */
@RestController
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "register a user for authentication",response = ResponseEntity.class)
	@PostMapping("/registration")
	public ResponseEntity<Object> signUp(@Valid @RequestBody UserDto userDto)
	    {
			userService.saveDto(userDto);
			return ResponseEntity.created(null).build();
	    }
	
}
