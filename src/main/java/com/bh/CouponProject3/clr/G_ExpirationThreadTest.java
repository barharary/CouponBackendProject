package com.bh.CouponProject3.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.utils.ArtUtils;

@Component
@Order(7)
public class G_ExpirationThreadTest implements CommandLineRunner {

	@Override
	public void run(String... args) {
		// System.out.println("I stop the thread.");
		ArtUtils.printVvsHeadLine(
				"Feel free to check all the code with Swagger or PostMan" + " " + ArtUtils.getSmaily(), 5);

	}

}
