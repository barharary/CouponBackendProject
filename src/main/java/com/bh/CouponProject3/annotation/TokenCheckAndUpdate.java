package com.bh.CouponProject3.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bh.CouponProject3.security.login.ClientType;

@Retention(RUNTIME)
@Target(METHOD)
@Configuration
public @interface TokenCheckAndUpdate {

	@Bean
	ClientType clientType();


}
