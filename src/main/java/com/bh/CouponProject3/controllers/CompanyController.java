package com.bh.CouponProject3.controllers;

import org.springframework.http.ResponseEntity;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;

public interface CompanyController {

	ResponseEntity<?> addCoupon(String tokenId, Coupon coupon) throws CouponException;

	ResponseEntity<?> updateCoupon(String tokenId, Coupon coupon) throws CouponException;

	ResponseEntity<?> deleteCoupon(String tokenId, int couponId) throws CouponException;

	ResponseEntity<?> getComapnyCoupons(String tokenId) throws CouponException;

	ResponseEntity<?> getComapnyCoupons(String tokenId, Category category);

	ResponseEntity<?> getComapnyCoupons(String tokenId, double maxPrice);

	ResponseEntity<?> getCompanyDetails(String tokenId) throws CompanyException;

}
