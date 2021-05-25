package com.bh.CouponProject3.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bh.CouponProject3.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	boolean existsByEmail(String email);

	Optional<Customer> findByEmailAndPassword(String customerEmail, String customerPassword);

	@Query(value = "select Exists(select * from db3.customers_vs_coupons where customer_id = ? and coupons_id = ?) as IsExsits", nativeQuery = true)
	int isExistsBycustomerIdAndCouponId(int customerId, int couponId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO `db3`.`customers_vs_coupons` (`customer_id`, `coupons_id`) VALUES (?, ?)", nativeQuery = true)
	void purchaseCoupon(int customer_id, int coupon_id);

}
