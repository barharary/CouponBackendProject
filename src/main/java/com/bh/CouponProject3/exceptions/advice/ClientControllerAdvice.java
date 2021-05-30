package com.bh.CouponProject3.exceptions.advice;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.login.LoginManager;

@RestController
@ControllerAdvice
public class ClientControllerAdvice {

	// JUST DATA EXCEPTION Nu nu nu...
	@ExceptionHandler(value = { CustomerException.class, CouponException.class, CompanyException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handleErrors(Exception e) {
		return new ErrorDetails("Data problem: ", e.getMessage(), 400);
	} // key,value,code

	// SEC EXCEPTION Oy Vey!!!
	@ExceptionHandler(value = { SecurityException.class, LoginException.class })

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorDetails handleErrors2(Exception e) {
		return new ErrorDetails("Security Problem: ", e.getMessage(), 401);
	}

}
