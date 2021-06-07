package com.bh.CouponProject3.clr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.repository.CompanyRepository;
import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.repository.CustomerRepository;
import com.bh.CouponProject3.utils.ArtUtils;
import com.bh.CouponProject3.utils.DateUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(1)
public class A_PrimaryData implements CommandLineRunner {
	private final CompanyRepository companyReposetory;
	private final CustomerRepository customerRepository;
	private final CouponRepository couponRepository;

	@Override

	public void run(String... args) {

		Company company1 = Company.builder() //
				.name("Nice") //
				.email("Nice@gmail.com") //
				.password("1234") //
				.build();

		Company company2 = Company.builder() //
				.name("Zoom") //
				.email("Zoom@zoom.com") //
				.password("1234") //
				.build();

		Company company3 = Company.builder() //
				.name("M&M") //
				.email("M&M@M&M.com") //
				.password("1234") //
				.build();

		Company company4 = Company.builder() //
				.name("Sony") //
				.email("Sony@sony.com") //
				.password("1234") //
				.build();

		Company company5 = Company.builder() //
				.name("Fox") //
				.email("Fox.gmail.com") //
				.password("4321") //
				.build();

		companyReposetory.saveAll(Arrays.asList(company1, company2, company3, company4, company5));
		////////////////////////////////////////////////////////

		Customer customer1 = Customer.builder() //
				.firstName("Moshe") //
				.lastName("Zveda") //
				.email("MosheZv@gmail.com") //
				.password("4321").build();

		Customer customer2 = Customer.builder() //
				.firstName("David") //
				.lastName("Ezra") //
				.email("DavidEzra@gmail.com") //
				.password("4321").build();

		Customer customer3 = Customer.builder() //
				.firstName("Bar") //
				.lastName("Shalom") //
				.email("BarShalom@gmail.com") //
				.password("4321").build();

		Customer customer4 = Customer.builder() //
				.firstName("Kobi") //
				.lastName("Shasha") //
				.email("KobiShasha@gmail.com") //
				.password("4321").build();

		Customer customer5 = Customer.builder() //
				.firstName("Zer") //
				.lastName("Hula") //
				.email("ZerHula@gmail.com") //
				.password("4321").build();

		customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5));

		//////////////////////////////////////////////

		Coupon coupon1 = Coupon.builder().category(Category.FOOD).couponTitle("Free Burger"). //
				couponDescription("free burger before 20:00. everyday!"). //
				startDate(DateUtils.generateDateJava(2015, 2, 11)).//
				endDate(DateUtils.generateDateJava(2028, 4, 12)).//
				amount(20).price(100.0).image("Image").company(company2).build();

		Coupon coupon2 = new Coupon();
		coupon2.setCompany(company3);
		coupon2.setCategory(Category.FOOD);
		coupon2.setCouponTitle("1+1 on Drink");
		coupon2.setCouponDescription("1+1 on Drinks. only NonAlcoholic");
		coupon2.setStartDate(DateUtils.generateDateJava(2020, 6, 18));
		coupon2.setEndDate(DateUtils.generateDateJava(2025, 4, 10));
		coupon2.setAmount(0);
		coupon2.setPrice(20.0);
		coupon2.setImage("Image");

		Coupon coupon3 = new Coupon();
		coupon3.setCompany(company3);
		coupon3.setCategory(Category.SHOPING);
		coupon3.setCouponTitle("50% discount");
		coupon3.setCouponDescription("50% discount on all product in the winter sale");
		coupon3.setStartDate(DateUtils.generateDateJava(2021, 3, 22));
		coupon3.setEndDate(DateUtils.generateDateJava(2022, 9, 18));
		coupon3.setAmount(1000);
		coupon3.setPrice(50.0);
		coupon3.setImage("Image");

		Coupon coupon4 = new Coupon();
		coupon4.setCompany(company3);
		coupon4.setCategory(Category.SHOPING);
		coupon4.setCouponTitle("4 snack in 1");
		coupon4.setCouponDescription("free Shipping to evry part of the country");
		coupon4.setStartDate(new Date());
		coupon4.setEndDate(DateUtils.generateDateJava(2027, 9, 18));
		coupon4.setAmount(200);
		coupon4.setPrice(50.0);
		coupon4.setImage("Image");

		Coupon coupon5 = new Coupon();
		coupon5.setCompany(company1);
		coupon5.setCategory(Category.TRIP);
		coupon5.setCouponTitle("20% discount");
		coupon5.setCouponDescription("20% discount  to all Europe lowCost destinations");
		coupon5.setStartDate(DateUtils.generateDateJava(2020, 2, 15));
		coupon5.setEndDate(DateUtils.generateDateJava(2021, 4, 16));
		coupon5.setAmount(200);
		coupon5.setPrice(50.0);
		coupon5.setImage("Image");

		Coupon coupon6 = new Coupon();
		coupon6.setCompany(company1);
		coupon6.setCategory(Category.TRIP);
		coupon6.setCouponTitle("70% discount");
		coupon6.setCouponDescription("70% discount  to all Asia lowCost destinations");
		coupon6.setStartDate(new Date());
		coupon6.setEndDate(new Date());
		coupon6.setAmount(200);
		coupon6.setPrice(80.0);
		coupon6.setImage("Image");

		Coupon coupon7 = new Coupon();
		coupon7.setCompany(company4);
		coupon7.setCategory(Category.SHOPING);
		coupon7.setCouponTitle("Massage");
		coupon7.setCouponDescription("for early booking on north Zimmer Destanetion");
		coupon7.setStartDate(new Date());
		coupon7.setEndDate(new Date());
		coupon7.setAmount(200);
		coupon7.setPrice(50.0);
		coupon7.setImage("Image");

//		Coupon coupon8 = new Coupon();
//		coupon8.setCompany(company3);
//		coupon8.setCategory(Category.FOOD);
//		coupon8.setCouponTitle("4 snack in 1");
//		coupon8.setCouponDescription("4 snack in 10 NIS from \"Egozi\", \"Teami\" and \"KifKef\"");
//		coupon8.setStartDate(new Date());
//		coupon8.setEndDate(DateUtils.generateDateJava(2020, 4, 16));
//		coupon8.setAmount(200);
//		coupon8.setPrice(50.0);
//		coupon8.setImage("Image");

		couponRepository
				.saveAll(Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7 /* ,coupon8 */));
////////////////////////////////////////////////////////////////////////////////////////

		List<Coupon> coupons1 = new ArrayList<>();
		coupons1.add(coupon7);
		coupons1.add(coupon5);
		customer5.setCoupons(coupons1);
		customerRepository.saveAndFlush(customer5);

		List<Coupon> coupons2 = new ArrayList<>();
		coupons2.add(coupon1);
		company2.setCompanyCoupons(coupons2);
		companyReposetory.saveAndFlush(company2);

		ArtUtils.printPrimaryData();
		ArtUtils.insertToTable("All Coupons", couponRepository.findAll());
		ArtUtils.insertToTable("All companies", companyReposetory.findAll());
		ArtUtils.insertToTable("All Customer", customerRepository.findAll());

	}
}
