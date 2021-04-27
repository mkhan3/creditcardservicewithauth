package com.creditcard.creditcardservicewithauth.filter;

import static com.creditcard.creditcardservicewithauth.constants.SecurityConstants.EXPIRATION_TIME;
import static com.creditcard.creditcardservicewithauth.constants.SecurityConstants.SECRET;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.creditcard.creditcardservicewithauth.repository.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Customisation of UsernamePasswordAuthenticationFilter which is the default class for password authentication in spring security.
 *  
 * @author Administrator
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        
        // spring security will create /login end point automatically
        setFilterProcessesUrl("/login"); 
    }
    
    /**
     * This function will be invoked when the user tries to login to the application. 
     * It reads the user given credentials and then check that to authenticate.
     * An empty list along with username and password are passed where the empty list 
     * represents the authorities (roles). The roles haven't been used yet.  
     * It returns an Authentication object that contains the authorities.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * if the authentication is successful, this method will be invoked.
     * A token is returned to user after authentication is successful.
     * The token is created using username, a secret and expiration date.
     *   
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
    	String token = JWT.create()
                .withSubject(auth.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    	
    	
    	String body = token;
        res.getWriter().write(body);
        res.getWriter().flush();
    }
	
}
