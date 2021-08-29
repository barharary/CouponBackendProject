package com.bh.CouponProject3.controllers.controllerImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.controllers.ClientController;
import com.bh.CouponProject3.controllers.CompanyController;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.CompanyService;

@RestController
@RequestMapping("company") // http://localhost:8080/company
public class CompanyControllerImpl extends ClientController implements CompanyController {

	public CompanyControllerImpl(LoginManager loginManager, TokenManager tokenManager, CompanyService companyService) {
		super(loginManager, tokenManager);
	}

	@Override
	@PostMapping
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> addCoupon(@RequestHeader(name = "tokenId") String tokenId, @RequestBody Coupon coupon)
			throws CouponException {
		((CompanyService) tokenManager.getService(tokenId)).addCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@Override
	@PutMapping
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> updateCoupon(@RequestHeader(name = "tokenId") String tokenId, @RequestBody Coupon coupon)
			throws CouponException {
		((CompanyService) tokenManager.getService(tokenId)).updateCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //
															// nested details.

	}

	@Override
	@DeleteMapping("/{id}")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "tokenId") String tokenId, @PathVariable int id)
			throws CouponException {
		((CompanyService) tokenManager.getService(tokenId)).deleteCoupon(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@Override
	@GetMapping("/all")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> getComapnyCoupons(@RequestHeader(name = "tokenId") String tokenId) throws CouponException {
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getComapnyCoupons(),
				HttpStatus.OK);
	}

	@Override
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	@GetMapping("/category")
	public ResponseEntity<?> getComapnyCoupons(@RequestHeader(name = "tokenId") String tokenId,
			@RequestHeader Category category) {
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getComapnyCoupons(category),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/maxPrice")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> getComapnyCoupons(@RequestHeader(name = "tokenId") String tokenId,
			@RequestHeader double maxPrice) {
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getComapnyCoupons(maxPrice),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/details")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "tokenId") String tokenId)
			throws CompanyException {
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getCompanyDetails(),
				HttpStatus.OK);
	}

}
