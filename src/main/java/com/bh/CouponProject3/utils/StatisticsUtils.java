package com.bh.CouponProject3.utils;

import org.springframework.stereotype.Component;

import com.bh.CouponProject3.repository.CompanyRepository;
import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.repository.CustomerRepository;
import com.bh.CouponProject3.security.TokenManager;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class StatisticsUtils {

	private final CouponRepository couponRepository;
	private final CompanyRepository companyRepository;
	private final CustomerRepository customerRepository;
	private final TokenManager tokenManager;

	public Object[][] statistics() {
		Object[][] statistics = new Object[2][7]; // could switch to a HashMap... meh... מתקמפל!
		statistics[0][0] = "coupons";
		statistics[0][1] = "companies";
		statistics[0][2] = "customers";
		statistics[0][3] = "cvc"; //
		statistics[0][4] = "stock";
		statistics[0][5] = "admins";
		statistics[0][6] = "liveTokens"; // ...ANGULAR INSPIRATION; 20.8.2021

		statistics[1][0] = couponRepository.count(); // TODO (simplify)
		statistics[1][1] = companyRepository.count(); // TODO
		statistics[1][2] = customerRepository.count(); // TODO
		statistics[1][3] = couponRepository.countCustomersvCoupons(); // TODO
		statistics[1][4] = couponRepository.countTotlaAmount(); // TODO ...native Queries, for no good reason...
		statistics[1][5] = 1; // ...JPA repo (correct)
		statistics[1][6] = tokenManager.getMap().size(); //

		if (statistics[1][4] == null) {
			statistics[1][4] = 0; // didn't fully understand why this
			// was happening in Angular,
			// when all coupons were deleted, there was no badge for STOCK.
			// was 'null' in console.
		}
		// maybe switch to Map <String, Integer> (see below)
		return statistics;
	}

}
