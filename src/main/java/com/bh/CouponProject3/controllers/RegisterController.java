package com.bh.CouponProject3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.services.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("register")
@CrossOrigin
@RequiredArgsConstructor
public class RegisterController {

	private final AdminService adminService;

	@PostMapping("/customer")
	public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) throws CustomerException {
		adminService.addCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@PostMapping("/company")
	public ResponseEntity<?> registerCompany(@RequestBody Company company) throws CompanyException {
		adminService.addCompany(company);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

}
