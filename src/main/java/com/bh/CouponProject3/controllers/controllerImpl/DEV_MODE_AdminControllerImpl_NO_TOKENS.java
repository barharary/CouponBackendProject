package com.bh.CouponProject3.controllers.controllerImpl;

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
import com.bh.CouponProject3.controllers.ClientController;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.AdminService;

@RestController
@RequestMapping("DEV_MODE_AdminController_NO_TOKEN") // == http://localhost:8080/admin
@CrossOrigin
public class DEV_MODE_AdminControllerImpl_NO_TOKENS extends ClientController {

	@Autowired
	private AdminService adminService;

	public DEV_MODE_AdminControllerImpl_NO_TOKENS(LoginManager loginManager, TokenManager tokenManager,
			AdminService adminService) {
		super(loginManager, tokenManager);
		this.adminService = adminService;
	}

	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws CompanyException, SecurityException {

		adminService.addCompany(company);
		return new ResponseEntity<>(HttpStatus.CREATED); //
	}

	@PutMapping("/updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws CompanyException, SecurityException {
		adminService.updateCompany(company);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //

	}

	@DeleteMapping("/deleteCompanyX/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable int companyId) throws CompanyException, SecurityException {
		adminService.deleteCompany(companyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/allCompanies")
	public ResponseEntity<?> getAllCompanies() throws CompanyException, SecurityException {
		return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK); // 200
	}

	@GetMapping("/oneCompany/{companyId}")
	public ResponseEntity<?> getOneCompany(@PathVariable int companyId) throws CompanyException, SecurityException {
		return new ResponseEntity<>(adminService.getOneCompany(companyId), HttpStatus.OK);
	}

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CustomerException {
		adminService.addCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED); //

	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws CustomerException {
		adminService.updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //

	}

	@DeleteMapping("/deleteCompany/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) throws CustomerException, SecurityException {
		adminService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/allCustomers")
	public ResponseEntity<?> getAllCustomers() throws SecurityException {
		return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/oneCustomer/{customerId}")
	public ResponseEntity<?> getOneCustomer(@PathVariable int customerId)
			throws CustomerException, CompanyException, SecurityException {
		return new ResponseEntity<>(adminService.getOneCustomer(customerId), HttpStatus.OK);
	}

}
