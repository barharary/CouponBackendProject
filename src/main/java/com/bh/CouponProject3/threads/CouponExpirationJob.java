package com.bh.CouponProject3.threads;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.utils.ArtUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CouponExpirationJob {

	private final CouponRepository couponRepository;
	private final long fixedRate = 30000; // every 3o sec..

	@Scheduled(fixedRate = fixedRate)
	public void run() {
		ArtUtils.insertToTable("COUPON DELETION JOB ==> Before deletion: ", couponRepository.findAll());
		couponRepository.deleteByEndDateBefore(new Date(new Date().getTime() - 1000 * 60 * 60 * 24));
		ArtUtils.insertToTable("COUPON DELETION JOB ==> After deletion ", couponRepository.findAll());
		System.out.println("\t\t\t\t" + ArtUtils.getSmaily() + "   ---   " + ArtUtils.getSmaily());

		System.out.println();
		ArtUtils.printVvsHeadLine("Fill free to check this code with Swagger or PostMan" + " " + ArtUtils.getSmaily(),
				5);

	}
}

// another way -> couponRepository.deleteByEndDateBefore(java.sql.Date.valueOf(LocalDate.now().minusDays(1)));
