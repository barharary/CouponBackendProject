package com.bh.CouponProject3.clr;

import javax.security.auth.login.LoginException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.CompanyService;
import com.bh.CouponProject3.utils.ArtUtils;
import com.bh.CouponProject3.utils.DateUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(4)
@RequiredArgsConstructor
public class D_CompanyTest implements CommandLineRunner {
	private final LoginManager loginManager;

	@Override
	public void run(String... args) throws LoginException, CompanyException, CustomerException {

		ArtUtils.printCompanyHeadline();
		CompanyService companyService = (CompanyService) loginManager.login("M&M@M&M.com", "1234", ClientType.COMPANY);

		ArtUtils.printRegularHeadLine("Full company Details Before addional CRUD actions");
		Company company = companyService.getCompanyDetails();
		System.out.println(company);

		Coupon coupon1 = new Coupon();

		coupon1.setCompany(company);
		coupon1.setCategory(Category.FOOD);
		coupon1.setCouponTitle("Free Joice");
		coupon1.setCouponDescription("this is a Wonder Coupon");
		coupon1.setStartDate(DateUtils.generateDateJava(2019, 2, 11));
		coupon1.setEndDate(DateUtils.generateDateJava(2022, 4, 12));
		coupon1.setAmount(20);
		coupon1.setPrice(100.0);
		coupon1.setImage("Image");
		try {
			companyService.addCoupon(coupon1);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
		try {
			ArtUtils.insertToTable("Add coupon titled \"" + coupon1.getCouponTitle() + "\" to my company coupon list",
					companyService.getComapnyCoupons()); //

		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
		Coupon coupon2 = new Coupon();

		coupon2.setCompany(company);
		coupon2.setCategory(Category.FOOD);
		coupon2.setCouponTitle("Free Joice");
		coupon2.setCouponDescription("this is a WOW Coupon");
		coupon2.setStartDate(DateUtils.generateDateJava(2019, 2, 11));
		coupon2.setEndDate(DateUtils.generateDateJava(2022, 4, 12));
		coupon2.setAmount(20);
		coupon2.setPrice(100.0);
		coupon2.setImage("Image");

		ArtUtils.printXxsHeadLine("Add coupon titled \"" + coupon2.getCouponTitle() + "\" with exception", 1);
		try {
			companyService.addCoupon(coupon2);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}

		int idToUpdate = 9;
		String newTitle = "I-Am-New";
		coupon1.setCouponTitle(newTitle);
		try {
			companyService.updateCoupon(coupon1);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
		try {
			ArtUtils.insertToTable("Update coupon Id " + idToUpdate + " with title \"" + newTitle + "\"",
					companyService.getComapnyCoupons());
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}

		idToUpdate = 4;
		coupon1.setCompany(companyService.getOneCompany(idToUpdate));
		ArtUtils.printXxsHeadLine("Update coupon with exception ", 1);
		try {
			companyService.updateCoupon(coupon1);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
		coupon1.setCompany(companyService.getOneCompany(3)); // return to previos condition
		int idToDelete = 3;
		try {
			companyService.deleteCoupon(idToDelete);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
		try {
			ArtUtils.insertToTable("Delete coupon id " + idToDelete, companyService.getComapnyCoupons());
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
		idToDelete = 20;
		ArtUtils.printXxsHeadLine("Delete coupon Id " + idToDelete + " with exception ", 1);
		try {
			companyService.deleteCoupon(idToDelete);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
		Category category = Category.SHOPING;
		ArtUtils.insertToTable("Get Company coupons by Category " + category,
				companyService.getComapnyCoupons(category));
		double maxPrice = 60;
		ArtUtils.insertToTable("Get Company coupons by max price of " + maxPrice,
				companyService.getComapnyCoupons(maxPrice));

		ArtUtils.printRegularHeadLine("Full company Details after CRUD");
		Company company2 = companyService.getCompanyDetails();
		System.out.println(company2);
	}

}
