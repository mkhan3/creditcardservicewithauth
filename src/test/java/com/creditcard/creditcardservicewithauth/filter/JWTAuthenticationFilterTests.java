package com.creditcard.creditcardservicewithauth.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.creditcard.creditcardservicewithauth.repository.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JWTAuthenticationFilterTests {

	private JWTAuthenticationFilter jWTAuthenticationFilter;
	@Mock private AuthenticationManager authenticationManager;
	@Mock private Authentication authentication;
	@Mock private HttpServletRequest req;
	@Mock private HttpServletResponse resp;
	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

	private User user;
	@BeforeEach
	public void setUp() throws JsonProcessingException {
		user = new User("test", "test123");
		usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()); 
		Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(usernamePasswordAuthenticationToken);
		
		jWTAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager);
	}
	
	@Test
	public void testAttemptAuthentication() throws IOException {
		
		InputStream targetStream = new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(user));
		ServletInputStream servletInputStream=new ServletInputStream(){
		    public int read() throws IOException {
		      return targetStream.read();
		    }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener listener) {
				// TODO Auto-generated method stub
				
			}
		  };
		  
		Mockito.when(req.getInputStream()).thenReturn(servletInputStream);
		
		Authentication auth = jWTAuthenticationFilter.attemptAuthentication(req, null);
		assertEquals("test", auth.getName());
		
	}
	
	
	@Test
	public void testSuccessfulAuthentication() throws IOException {

		StringBuffer sBuff = new StringBuffer();
		OutputStream out = new OutputStream() {

			@Override
			public void write(int b) throws IOException {
				sBuff.append((char) b);

			}
		};
		PrintWriter printWriter = new PrintWriter(out);

		Mockito.when(resp.getWriter()).thenReturn(printWriter);

		jWTAuthenticationFilter.successfulAuthentication(null, resp, null, usernamePasswordAuthenticationToken);

		assertFalse(sBuff.toString().isEmpty());
	}
	 
}
