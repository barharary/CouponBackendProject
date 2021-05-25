package com.bh.CouponProject3.security;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.services.AdminService;
import com.bh.CouponProject3.services.ClientService;
import com.bh.CouponProject3.services.CompanyService;
import com.bh.CouponProject3.services.CustomerService;

import lombok.Data;

@Component
@Data
public class TokenManager {

	@Autowired
	private Map<String, TokenInformation> map;

	public String createTokenReturnTokenId(ClientService clientService) {
		TokenInformation tokenInformation = new TokenInformation(System.currentTimeMillis(), clientService);
		String tokenId = UUID.randomUUID().toString();
		map.put(tokenId, tokenInformation);
		return tokenId;

	}

	public ClientService getService(String tokenId) {
		return getMap().get(tokenId).getClientService();

	}

	public void isTokenExists(String tokenId) throws SecurityException {
		if (map.get(tokenId) == null) {
			throw new SecurityException("nu nu nu.. Your token id not in the system.");
		}

	}

	public void isTokenExpaired(long time) throws SecurityException {
		if (time < (new Date().getTime() - (1000 * 60 * 15))/* || time > new Date().getTime()+ (1000 * 60 * 10) */) { // 15
																														// min..
			throw new SecurityException("nu nu nu.. Your token id is out of date.");
		}
	}

	public void updateTimeStamp(String tokenId) {
		long time = new Date().getTime();
		time += (1000 * 60 * 10); // extra time token is good
		getMap().get(tokenId).setTimeStamp(time);
	}

	public void isAdminServiceCorrect(String tokenId) throws SecurityException {
		if (!(getService(tokenId) instanceof AdminService)) {
			throw new SecurityException("nu nu nu.. Your client type does NOT have access to this action.");
		}
	}

	public void isCustomerServiceCorrect(String tokenId) throws SecurityException {
		if (!(getService(tokenId) instanceof CustomerService)) {
			throw new SecurityException("nu nu nu.. Your client type does NOT have access to this action.");
		}
	}

	public void isCompanyServiceCorrect(String tokenId) throws SecurityException {
		if (!(getService(tokenId) instanceof CompanyService)) {
			throw new SecurityException("nu nu nu.. Your client type does NOT have access to this action.");
		}

	}

	public void isServiceTypeCorrect(String tokenId, ClientType clientType) throws SecurityException {
		if (clientType == ClientType.ADMIN) {
			isAdminServiceCorrect(tokenId);
		} else if (clientType == ClientType.COMPANY) {
			isCompanyServiceCorrect(tokenId);
		} else if (clientType == ClientType.CUSTOMER) {
			isCustomerServiceCorrect(tokenId); // Learn class cheking for all check mwthods
		}

	}

}
