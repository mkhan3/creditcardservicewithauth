package com.creditcard.creditcardservicewithauth.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import static com.creditcard.creditcardservicewithauth.constants.SecurityConstants.HEADER_STRING;
import static com.creditcard.creditcardservicewithauth.constants.SecurityConstants.TOKEN_PREFIX;
import static com.creditcard.creditcardservicewithauth.constants.SecurityConstants.SECRET;

/**
 * Customize the BasicAuthenticationFilter of the spring framework.
 * @author Administrator
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

	/**
	 * This method intercepts the requests and then checks the authorization header.
	 * If the header is present, the getAuthentication method is invoked. When the token 
	 * is verified, it is saved to the SecurityContext.  
	 */
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();

            if (user != null) {
                // new arraylist means authorities
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }

            return null;
        }

        return null;

    }
}