package com.bh.CouponProject3.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.services.AdminService;

@Component
public class FactoryUtils {


	public static Company BringOneCompany() {

		Company company = Company.builder() //
				.name("Doritos") //
				.email("Doritos@d.com") //
				.password("S34") //
				.build();
		return company;
	}

	public static Company BringAlreadyExistsCompanyName() {

		Company company = Company.builder() //
				.name("Nice") //
				.email("Jonhnas@doritos.com") //
				.password("S234") //
				.build();

		return company;
	}

	public static Customer BringOneCustomer() {

		Customer company = Customer.builder() //
				.firstName("John").lastName("Smith") //
				.email("Jonh123@gmail.com") //
				.password("1s234") //
				.build();
		return company;
	}

	public static Customer BringAlreadyExistsCustomerEmail() {

		Customer customer = Customer.builder() //
				.firstName("Hofi").lastName("Smith") //
				.email("DavidEzra@gmail.com") //
				.password("1s234") //
				.build();
		return customer;
	}

	public static Coupon bringOneCoupon() throws CompanyException {
		// CompanyRepository companyRepository = ctx.getBean(CompanyRepository.class);
		Coupon coupon = new Coupon();
		//AdminService adminService = ctx.getBean(AdminService.class);
		//Company company = adminService.getOneCompany(3);
		
		coupon.setCategory(Category.FOOD);
		coupon.setCouponTitle("BEST PRICE X");
		coupon.setCouponDescription("this is a Wonder Coupon");
		coupon.setStartDate(DateUtils.generateDateJava(2019, 2, 11));
		coupon.setEndDate(DateUtils.generateDateJava(2022, 4, 12));
		coupon.setAmount(20);
		coupon.setPrice(100.0);
		coupon.setImage("Image");
		return coupon;
	}

	@Autowired
	private static ApplicationContext ctx;

	public static Coupon bringCouponWithException() throws CompanyException {
		Coupon coupon = new Coupon();
		AdminService adminService = ctx.getBean(AdminService.class);
		Company company = adminService.getOneCompany(1);

		coupon.setCompany(company);
		coupon.setCategory(Category.FOOD);
		coupon.setCouponTitle("Free Joice");
		coupon.setCouponDescription("this is a WOW Coupon");
		coupon.setStartDate(DateUtils.generateDateJava(2019, 2, 11));
		coupon.setEndDate(DateUtils.generateDateJava(2022, 4, 12));
		coupon.setAmount(20);
		coupon.setPrice(100.0);
		coupon.setImage("Image");
		return coupon;
	}

}
