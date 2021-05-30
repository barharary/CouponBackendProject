package com.bh.CouponProject3.controllers.controllerImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.bh.CouponProject3.utils.ArtUtils;

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
		// System.out.println(coupon);
		CompanyService companyService = ((CompanyService) tokenManager.getService(tokenId));
		companyService.addCoupon(coupon);
		System.out.println("this is the CompanyId: " + companyService.getCompanyId());
		ArtUtils.insertToTable("get all coupons", companyService.getComapnyCoupons());
		return new ResponseEntity<>(HttpStatus.CREATED); // 
															// details.

	}

	@Override
	@PutMapping
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> updateCoupon(@RequestHeader(name = "tokenId") String tokenId, @RequestBody Coupon coupon)
			throws CouponException {
		CompanyService companyService = ((CompanyService) tokenManager.getService(tokenId));
		companyService.updateCoupon(coupon);
		System.out.println("this is the CompanyId: " + companyService.getCompanyId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 
															// nested details.

	}

	@Override
	@DeleteMapping("/{id}")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "tokenId") String tokenId, int Id)
			throws CouponException {
		CompanyService companyService = ((CompanyService) tokenManager.getService(tokenId));
		companyService.deleteCoupon(Id);
		System.out.println("this is the CompanyId: " + companyService.getCompanyId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@Override
	@GetMapping("/allMyCompanyCoupons")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> getComapnyCoupons(@RequestHeader(name = "tokenId") String tokenId) throws CouponException {
		CompanyService companyService = ((CompanyService) tokenManager.getService(tokenId));
		System.out.println("this is the CompanyId: " + companyService.getCompanyId());
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getComapnyCoupons(),
				HttpStatus.OK);
	}

	@Override
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	@GetMapping("/getMyCompanyCouponsByCategory")
	public ResponseEntity<?> getComapnyCoupons(@RequestHeader(name = "tokenId") String tokenId,
			@RequestHeader Category category) {
		CompanyService companyService = ((CompanyService) tokenManager.getService(tokenId));
		System.out.println("this is the CompanyId: " + companyService.getCompanyId());
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getComapnyCoupons(category),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/getMyCompanyCouponsByMaxPrice")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> getComapnyCoupons(@RequestHeader(name = "tokenId") String tokenId,
			@RequestHeader double maxPrice) {
		CompanyService companyService = ((CompanyService) tokenManager.getService(tokenId));
		System.out.println("this is the CompanyId: " + companyService.getCompanyId());
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getComapnyCoupons(maxPrice),
				HttpStatus.OK);
	}

	@Override
	@GetMapping("/getCompanyDetails")
	@TokenCheckAndUpdate(clientType = ClientType.COMPANY)
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "tokenId") String tokenId)
			throws CompanyException {
		CompanyService companyService = ((CompanyService) tokenManager.getService(tokenId));
		System.out.println("this is the CompanyId: " + companyService.getCompanyId());
		return new ResponseEntity<>(((CompanyService) tokenManager.getService(tokenId)).getCompanyDetails(),
				HttpStatus.OK);
	}

}
