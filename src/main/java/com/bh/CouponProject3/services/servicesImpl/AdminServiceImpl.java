package com.bh.CouponProject3.services.servicesImpl;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Service;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.repository.CompanyRepository;
import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.repository.CustomerRepository;
import com.bh.CouponProject3.services.AdminService;
import com.bh.CouponProject3.services.ClientService;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

	@Override
	public boolean login(String email, String password) throws LoginException, CompanyException, CustomerException {
		if (!(email.equals("admin@admin.com") && password.equals("admin"))) {
			throw new LoginException("Incorrect email or password.");
		}
		return true;
	}

	@Override
	public void addCompany(Company company) throws CompanyException {
		if (companyRepository.existsByNameOrEmail(company.getName(), company.getEmail())) {
			throw new CompanyException(
					"Company " + company.getName() + " Already exists or your email is already in use.");
		}
		companyRepository.save(company);
	}

	@Override
	public void updateCompany(Company company) throws CompanyException {
		if (!companyRepository.existsByIdAndName(company.getId(), company.getName())) {
			throw new CompanyException("Cannot change ID or name of the company.");
		}
		if (companyRepository.existsByEmail(company.getEmail()) && company.getEmail() != getOneCompany(company.getId()).getEmail()) {
			throw new CompanyException("Email already in use.");
		}
		companyRepository.saveAndFlush(company);
	}

	@Override
	public void deleteCompany(int companyId) throws CompanyException {

		if (!companyRepository.existsById(companyId)) {
			throw new CompanyException("Company not Exists.");
		}
		companyRepository.deleteById(companyId);

	}

	@Override
	public Company getOneCompany(int companyId) throws CompanyException {
		return companyRepository.findById(companyId).orElseThrow(() -> new CompanyException("company Not Exists."));

	}

	@Override
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	@Override
	public void addCustomer(Customer customer) throws CustomerException {
		if (customerRepository.existsByEmail(customer.getEmail())) {
			throw new CustomerException("email " + customer.getEmail() + " already exists.");
		}
		customerRepository.save(customer);
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerException {
	
		
		if (!customerRepository.existsById(customer.getId())) {
			throw new CustomerException("customer Not Exists");
		}
		customerRepository.saveAndFlush(customer);

	}

	@Override
	public void deleteCustomer(int customerId) throws CustomerException {
		if (!customerRepository.existsById(customerId)) {
			throw new CustomerException("customer not Exists.");
		}
		customerRepository.deleteById(customerId);

	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getOneCustomer(int id) throws CustomerException {

		return customerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer Not Exists."));
	}

	public AdminServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

}
