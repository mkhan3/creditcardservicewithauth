package com.creditcard.creditcardservicewithauth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.creditcard.creditcardservicewithauth.dtos.UserDto;
import com.creditcard.creditcardservicewithauth.repository.UserRepository;
import com.creditcard.creditcardservicewithauth.repository.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	@Mock
	private UserRepository userRepository;
	@Mock private BCryptPasswordEncoder bCryptPasswordEncoder;

	@InjectMocks private UserService userService;
	private UserDto userDto;
	
	@BeforeEach
	public void setUp() {
		userDto = new UserDto("test", "test123");

	}
	 
	@Test
	public void testSave() {
		
		User user1 = new User("test", "test123");
		Mockito.when(bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("test123");
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user1);
		assertEquals("test", userService.saveDto(userDto));
		
	}
	
}
