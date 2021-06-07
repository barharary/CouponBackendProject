package com.bh.CouponProject3.exceptions.advice;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ErrorDetails {

	private String key;
	private String errorMessage;
	private int errorCode;
}
