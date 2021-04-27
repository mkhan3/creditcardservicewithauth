package com.creditcard.creditcardservicewithauth.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.creditcard.creditcardservicewithauth.dtos.CreditCardDto;
import com.creditcard.creditcardservicewithauth.exception.InvalidCreditCardNumberException;
import com.creditcard.creditcardservicewithauth.service.CCardService;
import com.creditcard.creditcardservicewithauth.utility.LuhnAlg;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Credit card rest controller which exposes one get and one post endpoint
 * @author Administrator
 *
 */

@RestController
public class CreditCardAPI {

	@Autowired
	private CCardService cCardService;
	
	/**
	 * Create credit card with the user given credit card information.
	 * @param creditCard
	 * @return ResponseEntity with the location of the newly created resource
	 */
	
	@ApiOperation(value = "create new credit card",response = ResponseEntity.class)
	@ApiResponses(value = {
            @ApiResponse(code = 409, message = "This card number already exists"),
            @ApiResponse(code = 400, message = "Validation Failed"),
            @ApiResponse(code = 406, message = "Invalid CreditCard Number")
    })
	@PostMapping(path = "/creditcards", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> createCreditCard(@Valid @RequestBody CreditCardDto  creditCardDto) {
		
		// validating creditcard number by Luhn10 algorithm
		if(!LuhnAlg.checkLuhn(creditCardDto.getCardNumber())) {
			throw new InvalidCreditCardNumberException(creditCardDto.getCardNumber() + " - Invalid CreditCard Number");
		}
		
		Long savedCreaditCardId = cCardService.saveCreditCard(creditCardDto);
		//the uri location for the newly created resource
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCreaditCardId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * 
	 * @return list of saved credit cards with the http staus OK
	 */
	@GetMapping(path = "/creditcards", produces = "application/json")
	public ResponseEntity<List<CreditCardDto>> retrieveAllCreditCards() {
		
		return ResponseEntity.ok(cCardService.getAllCreditCards());

	}
}

