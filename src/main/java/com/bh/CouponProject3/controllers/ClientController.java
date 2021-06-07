package com.bh.CouponProject3.controllers;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.security.model.LoginBody;
import com.bh.CouponProject3.security.model.LoginResponse;
import com.bh.CouponProject3.services.ClientService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Data
public abstract class ClientController {

	protected final LoginManager loginManager;
	protected final TokenManager tokenManager;

	@PostMapping("/login")
	public ResponseEntity<?> LoginForToken(@RequestBody LoginBody loginBody, @RequestHeader ClientType clientType)
			throws LoginException, CompanyException, CustomerException {
		System.out.println(loginBody);
		ClientService clientService = loginManager.login(loginBody.getEmail(), loginBody.getPassword(), clientType);
		LoginResponse loginResponse = new LoginResponse(tokenManager.createTokenReturnTokenId(clientService));
		return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
	}

	@DeleteMapping("/logOut")
	public ResponseEntity<?> logOut(@RequestHeader String tokenId)
			throws LoginException, CompanyException, CustomerException, SecurityException {
		tokenManager.getMap().remove(tokenId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); 

	}

}
