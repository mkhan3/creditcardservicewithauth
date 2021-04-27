package com.creditcard.creditcardservicewithauth.utility;

/**
 * The Luhn algorithm, also known as the modulus 10 or mod 10 algorithm, is a simple checksum formula used to validate a 
 * variety of identification numbers, such as credit card numbers.
 * Steps involved in Luhn algorithm:
 * Step 1 – Starting from the rightmost digit double the value of every second digit
 * Step 2 – If doubling of a number results in a two digits number i.e greater than 9(e.g., 6 × 2 = 12), 
 * then add the digits of the product (e.g., 12: 1 + 2 = 3, 15: 1 + 5 = 6), to get a single digit number. 
 * Step 3 – Now take the sum of all the digits.
 * Step 4 – If the total modulo 10 is equal to 0 (if the total ends in zero) then the number is valid 
 * according to the Luhn formula; else it is not valid.
 * 
 * @author Administrator
 *
 */
public class LuhnAlg {

	// Returns true if given card number is valid
	public static boolean checkLuhn(String cardNo)
	{
	    int nDigits = cardNo.length();
	 
	    int nSum = 0;
	    boolean isSecond = false;
	    for (int i = nDigits - 1; i >= 0; i--)
	    {
	 
	        int d = cardNo.charAt(i) - '0';
	 
	        if (isSecond == true)
	            d = d * 2;
	 
	        // Add two digits to handle cases that make two digits
	        // after doubling
	        nSum += d / 10;
	        nSum += d % 10;
	 
	        isSecond = !isSecond;
	    }
	    return (nSum % 10 == 0);
	}
}
