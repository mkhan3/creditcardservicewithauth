package com.creditcard.creditcardservicewithauth.dtos;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="User information regarding username and password")
public class UserDto {

	@NotEmpty(message="username cannot be empty")
	@ApiModelProperty(notes="username cannot be empty")
	private String username;
	@NotEmpty(message="password cannot be empty")
	@ApiModelProperty(notes="password cannot be empty")
	private String password;
	
	public UserDto() {
		
	}

	public UserDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

