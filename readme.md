1. To run spring boot application:  mvn spring-boot:run
2. To run integration testing:      mvn verify
3. To run unit testing:             mvn test
4. To create docker image:			mvn spring-boot:build-image
for API documentation, http://localhost:8080/swagger-ui.html and http://localhost:8080/v2/api-docs

Some keypoints:
--------------------
4. CustomizedResponseEntityExceptionHandler (which extends ResponseEntityExceptionHandler) is used to handle exceptions thrown by any controllers. This handler is also a rest controller so it can send customized exception message to client.
5. A service layer is used between rest controller and repository. CCardService and UserService belong to this layer.
6. To receive client request or generate client response, dto objects (CreditCardDto, UserDto) are used. javax.validation.constraints were applied on those objects.
7. CreditCardEntity and User are entity objects. They have been kept separate from the dtos.
8. After successfully creating Credit card resource, the api will send HTTP.OK response with the location of the newly created resource.
9. maven-surefire-plugin is used to run integration testing. Unit testing has also been separated from the integration testing.
10. javax.validation has been used to validate user input 

11. This application used a bit of security. JWT token based security has been applied. 
	a. The user first needs to register their account. 
	b. To register, user needs to send a post request carrying username and password to the /registration endpoint 
		{ "username" : "test", "password" : "test123" }
	c. After registration, if the user wants to use the application, he needs to send the above mentioned post request to /login endpoint
	d. If the user is authenticated successfully, user will be send a JWT token.
	e. To call any credit card APIs, the token needs to be present in the request header.
		key: Authorization
		value: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjE5NTA4NDA1fQ.1S-77Rxn6QYaMn0FWdYaHb9aQyvKkdupdfm7JFm4EUZyztF34sLN6GzWmThUnpO6rWLFByIRYkhohLWys11O_A

12. To add authentication and authorization, the following filter classes of spring framework have been extended.
	UsernamePasswordAuthenticationFilter, BasicAuthenticationFilter
13. WebSecurityConfigurerAdapter class has been extended to enable web security.
14. spring-boot-maven-plugin has been used in the pom.xml to generate docker image.	
15. Swagger2 has been added for API documentation.		