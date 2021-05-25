package com.bh.CouponProject3.controllers;

import org.springframework.http.ResponseEntity;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;

public interface CustomerController {

	ResponseEntity<?> getMyCustomerCoupon(String tokenId);

	ResponseEntity<?> getMyCustomerCoupon(String tokenId, Category category);

	ResponseEntity<?> getMyCustomerCoupon(String tokenId, double maxPrice);

	ResponseEntity<?> getCustomerDetails(String tokenId) throws CustomerException;

	ResponseEntity<?> getListOfCouponToChooseFrom(String tokenId);

	ResponseEntity<?> purchaseCoupon(String tokenId, Coupon coupon) throws CouponException;

}
