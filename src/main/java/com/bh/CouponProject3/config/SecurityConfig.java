package com.bh.CouponProject3.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.bh.CouponProject3.security.TokenInformation;

public class SecurityConfig {

	@Configuration
	public static class SecConfig {

		@Bean
		public static Map<String, TokenInformation> createMap() {
			return new HashMap<>();
		}

		@Bean
		public static RestTemplate createRestTemplate() {
			return new RestTemplate();
		}

	}

}
