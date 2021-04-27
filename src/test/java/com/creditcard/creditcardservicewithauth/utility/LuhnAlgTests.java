package com.creditcard.creditcardservicewithauth.utility;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit testing for Luhn10 algorithm.
 * The Luhn algorithm, also known as the modulus 10 or mod 10 algorithm, is a simple checksum formula used to validate a 
 * variety of identification numbers, such as credit card numbers.
 * Steps involved in Luhn algorithm:
 * Step 1 – Starting from the rightmost digit double the value of every second digit
 * Step 2 – If doubling of a number results in a two digits number i.e greater than 9(e.g., 6 × 2 = 12), 
 * then add the digits of the product (e.g., 12: 1 + 2 = 3, 15: 1 + 5 = 6), to get a single digit number. 
 * Step 3 – Now take the sum of all the digits.
 * Step 4 – If the total modulo 10 is equal to 0 (if the total ends in zero) then the number is valid 
 * according to the Luhn formula; else it is not valid.
 * @author Administrator
 *
 */
public class LuhnAlgTests {
	
	@BeforeEach
	public void setUp() {
		
	}
	@Test
	public void testValidNumbers() {
		assertTrue(LuhnAlg.checkLuhn("79927398713"));
		assertTrue(LuhnAlg.checkLuhn("1358954993914435"));
		assertTrue(LuhnAlg.checkLuhn("12345678903555"));
		assertTrue(LuhnAlg.checkLuhn("61789372994"));
		assertTrue(LuhnAlg.checkLuhn("379354508162306"));
		
	}
	
	@Test
	public void testInvalidNumbers() {
		
		assertFalse(LuhnAlg.checkLuhn("1234567890"));
		assertFalse(LuhnAlg.checkLuhn("12345678912345678"));
		assertFalse(LuhnAlg.checkLuhn("13579131719"));
		
	}
	
	
}
