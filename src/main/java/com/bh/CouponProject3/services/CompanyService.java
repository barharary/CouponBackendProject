package com.bh.CouponProject3.services;

import java.util.List;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;

public interface CompanyService {

	public void addCoupon(Coupon coupon) throws CouponException;

	void updateCoupon(Coupon coupon) throws CouponException;

	void deleteCoupon(int couponId) throws CouponException;

	public List<Coupon> getComapnyCoupons() throws CouponException;

	public List<Coupon> getComapnyCoupons(Category category);

	public List<Coupon> getComapnyCoupons(double maxPrice);

	public Company getCompanyDetails() throws CompanyException;

	Company getOneCompany(int id) throws CompanyException;

	int getCompanyId();
}
