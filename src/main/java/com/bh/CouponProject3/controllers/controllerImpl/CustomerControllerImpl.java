package com.bh.CouponProject3.controllers.controllerImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bh.CouponProject3.annotation.TokenCheckAndUpdate;
import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.controllers.ClientController;
import com.bh.CouponProject3.controllers.CustomerController;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.CustomerService;

@RestController
@RequestMapping("customer") // http://localhost:8080/admin
public class CustomerControllerImpl extends ClientController implements CustomerController {

	public CustomerControllerImpl(LoginManager loginManager, TokenManager tokenManager) {
		super(loginManager, tokenManager);
		// FIXME please... Auto-generated constructor stub
	}

	@Override
	@GetMapping("/AllMyCustomerCoupons")
	@TokenCheckAndUpdate(clientType = ClientType.CUSTOMER)
	public ResponseEntity<?> getMyCustomerCoupon(@RequestHeader(name = "tokenId") String tokenId) {
		CustomerService customerService = ((CustomerService) tokenManager.getService(tokenId));
		System.out.println("this is the customerId: " + customerService.getCustomerId());
		return new ResponseEntity<>(((CustomerService) tokenManager.getService(tokenId)).getMyCustomerCoupon(),
				HttpStatus.OK);
	} 

	@Override
	@GetMapping("/MyCustomerCouponsByCategory")
	@TokenCheckAndUpdate(clientType = ClientType.CUSTOMER)
	public ResponseEntity<?> getMyCustomerCoupon(@RequestHeader(name = "tokenId") String tokenId,
			@RequestHeader Category category) {
		CustomerService customerService = ((CustomerService) tokenManager.getService(tokenId));
		System.out.println("this is the customerId: " + customerService.getCustomerId());
		return new ResponseEntity<>(((CustomerService) tokenManager.getService(tokenId)).getMyCustomerCoupon(category),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/MyCustomerCouponsByMaxPrice")
	@TokenCheckAndUpdate(clientType = ClientType.CUSTOMER)
	public ResponseEntity<?> getMyCustomerCoupon(@RequestHeader(name = "tokenId") String tokenId,
			@RequestHeader double maxPrice) {
		CustomerService customerService = ((CustomerService) tokenManager.getService(tokenId));
		System.out.println("this is the customerId: " + customerService.getCustomerId());
		return new ResponseEntity<>(((CustomerService) tokenManager.getService(tokenId)).getMyCustomerCoupon(maxPrice),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/myCustomerDetails")
	@TokenCheckAndUpdate(clientType = ClientType.CUSTOMER)
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "tokenId") String tokenId)
			throws CustomerException {
		CustomerService customerService = ((CustomerService) tokenManager.getService(tokenId));
		System.out.println("this is the customerId: " + customerService.getCustomerId());
		return new ResponseEntity<>(((CustomerService) tokenManager.getService(tokenId)).getCustomerDetails(),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/CouponsAvilable")
	@TokenCheckAndUpdate(clientType = ClientType.CUSTOMER)
	public ResponseEntity<?> getListOfCouponToChooseFrom(@RequestHeader(name = "tokenId") String tokenId) {
		CustomerService customerService = ((CustomerService) tokenManager.getService(tokenId));
		System.out.println("this is the customerId: " + customerService.getCustomerId());
		return new ResponseEntity<>(((CustomerService) tokenManager.getService(tokenId)).getListOfCouponToChooseFrom(),
				HttpStatus.OK);
	}

	@Override
	@PostMapping
	@TokenCheckAndUpdate(clientType = ClientType.CUSTOMER)
	public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "tokenId") String tokenId, @RequestBody Coupon coupon)
			throws CouponException {
		System.out.println(coupon);
		CustomerService customerService = ((CustomerService) tokenManager.getService(tokenId));
		customerService.purchaseCoupon(coupon);
		System.out.println("this is the customerId: " + customerService.getCustomerId());
		return new ResponseEntity<>(HttpStatus.CREATED); // TODO 1. Swagger filter client only to choose from id and
															// show the title and description and endDate
	}

}
