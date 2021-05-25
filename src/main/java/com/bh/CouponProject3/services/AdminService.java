package com.bh.CouponProject3.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;



@Service
public interface AdminService {

	public void addCompany(Company company) throws CompanyException;

	void updateCompany(Company company) throws CompanyException;

	public void deleteCompany(int companyId) throws CompanyException;

	public Company getOneCompany(int companyId) throws CompanyException;

	public List<Company> getAllCompanies();

	public void addCustomer(Customer customer) throws CustomerException;

	void updateCustomer(Customer customer) throws CustomerException;

	public void deleteCustomer(int id) throws CustomerException;

	public List<Customer> getAllCustomers();

	public Customer getOneCustomer(int id) throws CustomerException;

}
