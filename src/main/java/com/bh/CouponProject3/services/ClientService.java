package com.bh.CouponProject3.services;

import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Service;

import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.repository.CompanyRepository;
import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class ClientService {

	protected final CompanyRepository companyRepository;
	protected final CustomerRepository customerRepository;
	protected final CouponRepository couponRepository;

	public abstract boolean login(String email, String pass) throws LoginException, CompanyException, CustomerException;

}
