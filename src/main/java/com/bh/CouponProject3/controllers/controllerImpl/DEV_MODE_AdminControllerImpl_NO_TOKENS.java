package com.bh.CouponProject3.controllers.controllerImpl;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.clr.A_PrimaryData;
import com.bh.CouponProject3.clr.B_LoginTest;
import com.bh.CouponProject3.clr.C_AdminTest;
import com.bh.CouponProject3.clr.D_CompanyTest;
import com.bh.CouponProject3.clr.E_CustomerTest;
import com.bh.CouponProject3.clr.F_Project3Test;
import com.bh.CouponProject3.clr.G_ExpirationThreadTest;
import com.bh.CouponProject3.controllers.ClientController;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.repository.CompanyRepository;
import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.repository.CustomerRepository;
import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.AdminService;
import com.bh.CouponProject3.utils.StatisticsUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("devModeAdmin") // == http://localhost:8080/admin
@CrossOrigin
public class DEV_MODE_AdminControllerImpl_NO_TOKENS extends ClientController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private StatisticsUtils statistics;
	@Autowired
	private A_PrimaryData a;
	@Autowired
	private B_LoginTest b;
	@Autowired
	private C_AdminTest c;
	@Autowired
	private D_CompanyTest d;
	@Autowired
	private E_CustomerTest e;
	@Autowired
	private F_Project3Test f;
	@Autowired
	private G_ExpirationThreadTest g;

	public DEV_MODE_AdminControllerImpl_NO_TOKENS(LoginManager loginManager, TokenManager tokenManager,
			AdminService adminService) {
		super(loginManager, tokenManager);
		this.adminService = adminService;
	}

	@PostMapping("/company")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws CompanyException, SecurityException {
		System.out.println("before add: " + companyRepository.count());
		adminService.addCompany(company);
		System.out.println("after add: " + companyRepository.count());
		return new ResponseEntity<>(HttpStatus.CREATED); //
	}

	@PutMapping("/company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws CompanyException, SecurityException {
		adminService.updateCompany(company);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //

	}

	@DeleteMapping("/company/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable int companyId) throws CompanyException, SecurityException {
		adminService.deleteCompany(companyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/company/all")
	public ResponseEntity<?> getAllCompanies() throws CompanyException, SecurityException {
		return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK); // 200
	}

	@GetMapping("/company/{companyId}")
	public ResponseEntity<?> getOneCompany(@PathVariable int companyId) throws CompanyException, SecurityException {
		return new ResponseEntity<>(adminService.getOneCompany(companyId), HttpStatus.OK);
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CustomerException {
		adminService.addCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED); //

	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws CustomerException {
		adminService.updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //

	}

	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) throws CustomerException, SecurityException {
		adminService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/customer/all")
	public ResponseEntity<?> getAllCustomers() throws SecurityException {
		return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable int customerId)
			throws CustomerException, CompanyException, SecurityException {
		return new ResponseEntity<>(adminService.getOneCustomer(customerId), HttpStatus.OK);
	}

	@GetMapping("/coupon/all")
	public ResponseEntity<?> getAllCoupons() {
		return new ResponseEntity<>(couponRepository.findAll(), HttpStatus.OK);
	}

	@DeleteMapping("coupon/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int id) throws CouponException {
		System.out.println("No of coupons before:  " + couponRepository.count());
		couponRepository.findById(id)
				.orElseThrow(() -> new CouponException("Sorry, coupon with id " + id + " does not exist"));
		couponRepository.deleteById(id);
		System.out.println("No of coupons after:  " + couponRepository.count());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Q: OK? TODO
	}

	@GetMapping("adminBadges")
	public ResponseEntity<?> getAdminBadges() {
		return new ResponseEntity<>(statistics.statistics(), HttpStatus.OK); // Q: OK? TODO
	}

	@PostMapping("replenishAll")
	ResponseEntity<?> replenishDBData() throws LoginException, CompanyException, CustomerException, SecurityException,
			CouponException, JsonMappingException, JsonProcessingException {
		
		a.run();
		b.run();
		c.run();
		d.run();
		e.run();
		f.run();
		g.run();
		return new ResponseEntity<>("Success:   CLR data replenished", //
				HttpStatus.OK);
	}

	@DeleteMapping("deleteAll")
	ResponseEntity<?> deleteAllDBData()   {

//		couponRepository.deletePurchasesDHexperimentalDevMode();
		couponRepository.deleteAll();
		customerRepository.deleteAll();
		companyRepository.deleteAll();

		return new ResponseEntity<>("Success:   CLR data replenished", //
				HttpStatus.OK);
	}

}
