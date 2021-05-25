package com.bh.CouponProject3.clr;

import javax.security.auth.login.LoginException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.utils.ArtUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(2)
public class B_LoginTest implements CommandLineRunner {

	private final LoginManager loginManager;

	@Override
	public void run(String... args) {

		ArtUtils.printLoginHeadline();
///////////////////////////////////////////////////////////////////////////////////////////
		ClientType client1 = ClientType.ADMIN;
		ArtUtils.printXxsHeadLine("Incorrect " + client1.name() + " login", 1);
		try {
			loginManager.login("INCORRECT@admin.com", "WRONG", client1);
		} catch (LoginException | CompanyException | CustomerException e) {
			System.out.println(e.getMessage());
		}
		ArtUtils.printVvsHeadLine("correct " + client1.name() + " login", 1);
		try {
			loginManager.login("admin@admin.com", "admin", client1);
		} catch (LoginException | CompanyException | CustomerException e) {
			System.out.println(e.getMessage());
		}
///////////////////////////////////////////////////////////////////////////////////////////
		ClientType client2 = ClientType.COMPANY;
		ArtUtils.printXxsHeadLine("Incorrect " + client2.name() + " login", 1);
		try {
			loginManager.login("INCORRECT@M&M.com", "111234", client2);
		} catch (LoginException | CompanyException | CustomerException e) {
			System.out.println(e.getMessage());
		}
		ArtUtils.printVvsHeadLine("correct " + client2.name() + " login", 1);
		try {
			loginManager.login("M&M@M&M.com", "1234", client2);
		} catch (LoginException | CompanyException | CustomerException e) {
			System.out.println(e.getMessage());
		}
//////////////////////////////////////////////////////////////////////////////////////////
		ClientType client3 = ClientType.CUSTOMER;

		ArtUtils.printXxsHeadLine("Incorrect " + client3.name() + " login", 1);
		try {
			loginManager.login("WRONG@gmail.com", "4321", client3);
		} catch (LoginException | CompanyException | CustomerException e) {
			System.out.println(e.getMessage());
		}
		ArtUtils.printVvsHeadLine("correct " + client3.name() + " Login", 1);
		try {
			loginManager.login("DavidEzra@gmail.com", "4321", client3);
		} catch (LoginException | CompanyException | CustomerException e) {
			System.out.println(e.getMessage());
		}
	}

}
