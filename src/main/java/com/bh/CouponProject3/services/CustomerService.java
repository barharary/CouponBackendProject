package com.bh.CouponProject3.services;

import java.util.List;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.beans.Customer;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;

public interface CustomerService {

	public List<Coupon> getMyCustomerCoupon(Category category);

	public List<Coupon> getMyCustomerCoupon(double maxPrice);

	public Customer getCustomerDetails() throws CustomerException;

	public List<Coupon> getListOfCouponToChooseFrom();

	void purchaseCoupon(Coupon coupon) throws CouponException;

	List<Coupon> getMyCustomerCoupon();

	public int getCustomerId();

}
