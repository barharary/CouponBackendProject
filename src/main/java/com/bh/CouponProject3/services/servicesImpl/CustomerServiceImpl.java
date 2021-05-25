package com.bh.CouponProject3.services.servicesImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.repository.CompanyRepository;
import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.repository.CustomerRepository;
import com.bh.CouponProject3.services.ClientService;
import com.bh.CouponProject3.services.CustomerService;

@Service
@Scope("prototype")
public class CustomerServiceImpl extends ClientService implements CustomerService {

	private int customerId;

	@Override
	public boolean login(String customerEmail, String customerPasswrod) throws CustomerException {

		Customer customer = customerRepository.findByEmailAndPassword(customerEmail, customerPasswrod)
				.orElseThrow(() -> new CustomerException("Incorrect Email Or Password"));
		this.customerId = customer.getId();
		return true;
	}

	@Override
	public void purchaseCoupon(Coupon coupon) throws CouponException {
		if (customerRepository.isExistsBycustomerIdAndCouponId(this.customerId, coupon.getId()) == 1) {
			throw new CouponException("You already purchase this coupon.");
		}
		Coupon couponToPurchase = couponRepository.getOne(coupon.getId());
		if (couponToPurchase.getAmount() < 1) {
			throw new CouponException("No coupon id " + coupon.getId() + " left");
		}
		if (couponToPurchase.getEndDate().before(new Date(new Date().getTime() - 1000 * 60 * 60 * 24))) {
			throw new CouponException("Coupon id " + coupon.getId() + " has expeired.");
		}
		customerRepository.purchaseCoupon(this.customerId, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);
		couponRepository.saveAndFlush(coupon);

	}

	@Override
	public List<Coupon> getMyCustomerCoupon() {
		System.out.println(customerId);
		return couponRepository.FindAllMyCoupons(this.customerId);
	}

	@Override
	public List<Coupon> getMyCustomerCoupon(Category category) {
		return couponRepository.FindAllMyCoupons(this.customerId).stream().filter(c -> c.getCategory() == category)
				.collect(Collectors.toList());

	}

	@Override
	public List<Coupon> getMyCustomerCoupon(double maxPrice) {
		return couponRepository.FindAllMyCoupons(this.customerId).stream().filter(c -> c.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}

	@Override
	public Customer getCustomerDetails() throws CustomerException {
		return customerRepository.findById(this.customerId)
				.orElseThrow(() -> new CustomerException("Customer Id NOT found."));
//		return customerRepository.getOne(this.customerId);

	}

	@Override
	public List<Coupon> getListOfCouponToChooseFrom() {
		return couponRepository.findAll().stream().sorted().collect(Collectors.toList());
	}

	public CustomerServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

	public int getCustomerId() {
		return customerId;
	}

}
