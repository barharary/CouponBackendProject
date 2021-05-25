package com.bh.CouponProject3.security;

import com.bh.CouponProject3.services.ClientService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenInformation {
//
	private long expiredTime;
	private ClientService clientService;

	@Override
	public String toString() {
		return "\t| timeStamp: " + expiredTime + "\t| " + clientService.getClass().getSimpleName() + "\t\t|";
	}

}
