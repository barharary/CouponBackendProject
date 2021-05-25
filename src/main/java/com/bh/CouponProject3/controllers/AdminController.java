package com.bh.CouponProject3.controllers;

import org.springframework.http.ResponseEntity;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;

public interface AdminController {

	ResponseEntity<?> addCompany(String token, Company company) throws CompanyException, SecurityException;

	ResponseEntity<?> updateCompany(String tokenId, Company company) throws CompanyException, SecurityException;

	ResponseEntity<?> deleteCompany(String tokenId, int companyId) throws CompanyException, SecurityException;

	ResponseEntity<?> getOneCompany(String tokenId, int companyId) throws CompanyException, SecurityException;

	ResponseEntity<?> getAllCompanies(String tokenID) throws CompanyException, SecurityException;

	ResponseEntity<?> addCustomer(String tokenId,Customer customer) throws CustomerException;

	ResponseEntity<?> updateCustomer(String tokenId,Customer customer) throws CustomerException;

	ResponseEntity<?> deleteCustomer(String tokenId,int id) throws CustomerException, SecurityException;

	ResponseEntity<?> getAllCustomers(String tokenId) throws SecurityException;

	ResponseEntity<?> getOneCustomer(String tokenId,int id) throws CustomerException, CompanyException, SecurityException;

}
