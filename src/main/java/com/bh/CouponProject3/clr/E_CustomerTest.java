package com.bh.CouponProject3.clr;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.login.LoginManager;
import com.bh.CouponProject3.services.CustomerService;
import com.bh.CouponProject3.utils.ArtUtils;

import lombok.RequiredArgsConstructor;

@Component
@Order(5)
@RequiredArgsConstructor
public class E_CustomerTest implements CommandLineRunner {

	private final LoginManager loginManager;

	@Override
	public void run(String... args) throws LoginException, CompanyException, CustomerException {

		ArtUtils.printCustomerHeadline();
		CustomerService customerService = (CustomerService) loginManager.login("DavidEzra@gmail.com", "4321",
				ClientType.CUSTOMER);

		ArtUtils.printRegularHeadLine("Full customer Details before Purchase");
		Customer customer = customerService.getCustomerDetails();
		System.out.println(customer);

		List<Coupon> couponsToChoose = customerService.getListOfCouponToChooseFrom();
		ArtUtils.insertToTable("List of Coupons to choose from", couponsToChoose);

		int IdChooseCoupon4 = 4; // o.k and already have
		Coupon coupon4 = couponsToChoose.stream().filter(c -> c.getId() == IdChooseCoupon4).collect(Collectors.toList())
				.get(0);
		int IdChooseCoupon2 = 2; // no left
		Coupon coupon2 = couponsToChoose.stream().filter(c -> c.getId() == IdChooseCoupon2).collect(Collectors.toList())
				.get(0);
		int IdChooseCoupon5 = 5; // Date exception
		Coupon coupon5 = couponsToChoose.stream().filter(c -> c.getId() == IdChooseCoupon5).collect(Collectors.toList())
				.get(0);
		int IdChooseCoupon6 = 6;
		Coupon coupon6 = couponsToChoose.stream().filter(c -> c.getId() == IdChooseCoupon6).collect(Collectors.toList())
				.get(0);

		ArtUtils.printXxsHeadLine("Purchase coupon Id " + IdChooseCoupon2 + " with \"amount 0 exception\"", 1);
		try {
			customerService.purchaseCoupon(coupon2);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}

		ArtUtils.printXxsHeadLine("Purchase coupon Id " + IdChooseCoupon5 + " with \"Date exception\"", 1);
		try {
			customerService.purchaseCoupon(coupon5);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}
////////////////////////////////////////////////////////////////////////// fall here i thnk
		try {
			customerService.purchaseCoupon(coupon4);
			customerService.purchaseCoupon(coupon6);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}

		List<Coupon> myCoupons = customerService.getMyCustomerCoupon().stream()
				.sorted((o1, o2) -> o1.getId() - o2.getId()).collect(Collectors.toList());
		ArtUtils.insertToTable("Purchase coupon Id " + IdChooseCoupon4 + " and " + IdChooseCoupon6 + " to my coupons",
				myCoupons);

		ArtUtils.printXxsHeadLine("Purchase coupon Id " + IdChooseCoupon6 + " with already have coupon exception", 1);
		try {
			customerService.purchaseCoupon(coupon6);
		} catch (CouponException e) {
			System.out.println(e.getMessage());
		}

		Category category = Category.TRIP;
		ArtUtils.insertToTable("Get My Customer coupons by category " + category,
				customerService.getMyCustomerCoupon(category));

		int maxPrice = 60;
		ArtUtils.insertToTable("Get My Customer coupons by max price of " + maxPrice,
				customerService.getMyCustomerCoupon(maxPrice));
		ArtUtils.printRegularHeadLine("Full customer Details after purchase");
		Customer customer2 = customerService.getCustomerDetails();
		System.out.println(customer2);
	}

}
