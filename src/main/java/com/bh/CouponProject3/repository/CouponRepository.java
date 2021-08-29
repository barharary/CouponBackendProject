package com.bh.CouponProject3.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	Optional<Coupon> findByCouponTitleAndCompanyId(String couponTitle, int companyId);

	boolean existsByIdAndCompanyId(int couponId, int companyId);

	List<Coupon> findAllByCompanyId(int companyId);

	List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);

	List<Coupon> findAllByCompanyIdAndPriceLessThan(int companyId, double maxPrice);

	@Query(value = "select * from coupons where id " //
			+ " in(select coupons_id from customers_vs_coupons where customer_id = ? )", nativeQuery = true)
	List<Coupon> FindAllMyCoupons(int customerId);

	@Transactional
	@Modifying
	void deleteByEndDateBefore(Date date);
	
	@Query(value = "select count(*) from " 
			+ "customers_vs_coupons", nativeQuery = true)
	Integer countCustomersvCoupons();
	
	@Query(value = "SELECT sum(amount) FROM coupons;", //
			nativeQuery = true)
	Integer countTotlaAmount();

}
