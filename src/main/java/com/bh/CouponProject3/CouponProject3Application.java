package com.bh.CouponProject3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponProject3Application {

	public static void main(String[] args) {
		SpringApplication.run(CouponProject3Application.class, args);

	}
}
