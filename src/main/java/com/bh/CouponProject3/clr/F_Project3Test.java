package com.bh.CouponProject3.clr;

import javax.security.auth.login.LoginException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.controllers.AdminController;
import com.bh.CouponProject3.controllers.ClientController;
import com.bh.CouponProject3.controllers.CompanyController;
import com.bh.CouponProject3.controllers.CustomerController;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.utils.ArtUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(6)
@RequiredArgsConstructor
public class F_Project3Test implements CommandLineRunner {

	private final AdminController adminController;
	private final CompanyController companyController;
	private final CustomerController customerController;

	@Override
	public void run(String... args)
			throws LoginException, CompanyException, CustomerException, SecurityException, CouponException {

		ArtUtils.printControllerTest();
		System.out.println("Conteroller testing:");
		System.out.println("--------------------");

		ResponseEntity<?> AdminResponseEntity = ((ClientController) adminController).LoginForToken("admin@admin.com",
				"admin", ClientType.ADMIN);
		ResponseEntity<?> CompanyResponseEntity = ((ClientController) companyController).LoginForToken("M&M@M&M.com",
				"1234", ClientType.COMPANY);
		ResponseEntity<?> CustomerResponseEntity = ((ClientController) customerController)
				.LoginForToken("ZerHula@gmail.com", "4321", ClientType.CUSTOMER);
		String adminTokenId = (String) AdminResponseEntity.getBody();
		String companyTokenId = (String) CompanyResponseEntity.getBody();
		String customerTokenId = (String) CustomerResponseEntity.getBody();

		System.out.println("**************************************");
		System.out.println();
		System.out.println("ADMIN toeknId:");
		System.out.println(adminTokenId);
		System.out.println();
		System.out.println("COMPANY toeknId:");
		System.out.println(companyTokenId);
		System.out.println();
		System.out.println("CUSTOMER toeknId:");
		System.out.println(customerTokenId);
		System.out.println();
		System.out.println("**************************************");
		System.out.println();

	}

}
