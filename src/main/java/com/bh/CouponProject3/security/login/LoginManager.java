package com.bh.CouponProject3.security.login;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.services.AdminService;
import com.bh.CouponProject3.services.ClientService;
import com.bh.CouponProject3.services.CompanyService;
import com.bh.CouponProject3.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginManager {

	private final AdminService adminService;

	@Autowired
	ApplicationContext ctx;

	public ClientService login(String email, String password, ClientType clientType)
			throws LoginException, CompanyException, CustomerException {

		if (clientType.equals(ClientType.ADMIN)) {
			if (((ClientService) adminService).login(email, password)) {
				loginAsMessage(email, clientType);
				return (ClientService) adminService;
			}
		} else if (clientType.equals(ClientType.CUSTOMER)) {
			ClientService customerService = (ClientService) ctx.getBean(CustomerService.class);
			customerService.login(email, password);
			loginAsMessage(email, clientType);
			return (ClientService) customerService;

		} else if (clientType.equals(ClientType.COMPANY)) {
			CompanyService companyService1 = ctx.getBean(CompanyService.class);
			((ClientService) companyService1).login(email, password);
			loginAsMessage(email, clientType);
			return (ClientService) companyService1;

		}
		throw new LoginException("Problem with the login.");

	}

	void loginAsMessage(String email, ClientType clientType) {
		System.out.println("Login success as: " + email + ". Client type is: " + clientType.name());
	}

}
