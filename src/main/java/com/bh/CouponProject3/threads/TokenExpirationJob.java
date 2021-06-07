package com.bh.CouponProject3.threads;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.utils.ArtUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenExpirationJob {

	private final TokenManager tokenManager;
	private final long fixedRate = 1000 * 60 * 20; // for testing every 20 sec. token deletion if pass 15 sec from last
													// correct request

	@Scheduled(fixedRate = fixedRate)
	public void run() {
		ArtUtils.printThreadHeadline();
		ArtUtils.insertToTable("TOKEN DELETION JOB ==> Before deletion: ", tokenManager.getMap());
		tokenManager.getMap().entrySet() // testing time
				.removeIf(entrySet -> (entrySet.getValue().getExpiredTime() < new Date().getTime() - (1000 * 60))
						|| (entrySet.getValue().getExpiredTime() > (new Date().getTime()) + 1000 * 60 * 30));
		ArtUtils.insertToTable("TOKEN DELETION JOB ==> After deletion: ", tokenManager.getMap());
		System.out.println("\t\t\t\t" + ArtUtils.getSmaily() + "   ---   " + ArtUtils.getSmaily());
		System.out.println();
		ArtUtils.printVvsHeadLine("Feel free to check this code with Swagger or PostMan" + " " + ArtUtils.getSmaily(),
				5);
	}
}
