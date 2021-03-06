package com.bh.CouponProject3.controllers.controllerImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.CouponProject3.annotation.TokenCheckAndUpdate;
import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.controllers.AdminController;
import com.bh.CouponProject3.controllers.ClientController;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.AdminService;

@RestController
@RequestMapping("admin") // == http://localhost:8080/admin
@CrossOrigin
public class AdminControllerImpl extends ClientController implements AdminController {

	public AdminControllerImpl(LoginManager loginManager, TokenManager tokenManager, AdminService adminService) {
		super(loginManager, tokenManager);
	}

	@PostMapping("/company")
	@Override
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN)
	public ResponseEntity<?> addCompany(@RequestHeader(name = "tokenId") String tokenId, @RequestBody Company company)
			throws CompanyException, SecurityException {
		System.out.println(company);
		((AdminService) tokenManager.getService(tokenId)).addCompany(company);
		return new ResponseEntity<>(HttpStatus.CREATED); //
	}

	@PutMapping("/company")
	@Override
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN) // AOP :)
	public ResponseEntity<?> updateCompany(@RequestHeader(name = "tokenId") String tokenId,
			@RequestBody Company company) throws CompanyException, SecurityException {
		((AdminService) tokenManager.getService(tokenId)).updateCompany(company);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //

	}

	@DeleteMapping("/company/{companyId}")
	@Override
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN) // AOP :)
	public ResponseEntity<?> deleteCompany(@RequestHeader(name = "tokenId") String tokenId, @PathVariable int companyId)
			throws CompanyException, SecurityException {
		((AdminService) tokenManager.getService(tokenId)).deleteCompany(companyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/company/all")
	@Override
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN) // AOP :)
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "tokenId") String tokenId)
			throws CompanyException, SecurityException {
		return new ResponseEntity<>(((AdminService) tokenManager.getService(tokenId)).getAllCompanies(), HttpStatus.OK); // 200
	}

	@Override
	@GetMapping("/company/{companyId}")
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN) // AOP :)
	public ResponseEntity<?> getOneCompany(@RequestHeader(name = "tokenId") String tokenId, @PathVariable int companyId)
			throws CompanyException, SecurityException {
		return new ResponseEntity<>(((AdminService) tokenManager.getService(tokenId)).getOneCompany(companyId),
				HttpStatus.OK);
	}

	@Override
	@PostMapping("/customer")
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN)
	public ResponseEntity<?> addCustomer(@RequestHeader(name = "tokenId") String tokenId,
			@RequestBody Customer customer) throws CustomerException {
		((AdminService) tokenManager.getService(tokenId)).addCustomer(customer);
		return new ResponseEntity<>(HttpStatus.CREATED); //

	}

	@Override
	@PutMapping("/customer")
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN)
	public ResponseEntity<?> updateCustomer(@RequestHeader(name = "tokenId") String tokenId,
			@RequestBody Customer customer) throws CustomerException {
		((AdminService) tokenManager.getService(tokenId)).updateCustomer(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //

	}

	@Override
	@DeleteMapping("/customer/{customerId}")
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN)
	public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "tokenId") String tokenId,
			@PathVariable int customerId) throws CustomerException, SecurityException {
		((AdminService) tokenManager.getService(tokenId)).deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	@GetMapping("/customer/all")
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN)
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "tokenId") String tokenId) throws SecurityException {
		return new ResponseEntity<>(((AdminService) tokenManager.getService(tokenId)).getAllCustomers(), HttpStatus.OK);
	}

	@Override
	@GetMapping("/customer/{customerId}")
	@TokenCheckAndUpdate(clientType = ClientType.ADMIN)
	public ResponseEntity<?> getOneCustomer(@RequestHeader(name = "tokenId") String tokenId,
			@PathVariable int customerId) throws CustomerException, CompanyException, SecurityException {
		return new ResponseEntity<>(((AdminService) tokenManager.getService(tokenId)).getOneCustomer(customerId),
				HttpStatus.OK);
	}

}
