package com.bh.CouponProject3.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bh.CouponProject3.config.RestTemplateConfig;

import io.swagger.models.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
//@Component
public class RestTemplateUtils<T> {

	@Autowired
	private RestTemplate restTemplate1;
	private final RestTemplateConfig restTemplateConfig;
	final String localhostURL = "http://localhost:8080";

	public ResponseEntity<Object> abc(String url, HttpMethod verb, Map<String, String> headers, Object requestBody) {
		HttpHeaders httpHeaders = new HttpHeaders();

		restTemplate1 = restTemplateConfig.SetRestConfig(restTemplate1);
		System.out.println("headres: " + headers);
//		for (int i = 0; i < headers.size(); i++) {
//			httpHeaders.add((String) headers.keySet().toArray()[i], headers.get(headers.keySet().toArray()[i]));
//		}

		for (Entry<String, String> e : headers.entrySet()) {
			httpHeaders.add(e.getKey(), e.getValue());
		}

//		String companyRegisterURLFaild = localhostURL + "/company/login";
//		LoginBody companyLoginBodyFaild = new LoginBody("Buzzz@Buzz.com", "XXX");
//		HttpHeaders companyLoginHeaderFaild = new HttpHeaders();
//		companyLoginHeaderFaild.add("ClientType", ClientType.COMPANY.name());
//		HttpEntity<?> companyLoginHttpEntityFaild = new HttpEntity<>(companyLoginBodyFaild, companyLoginHeaderFaild);

		HttpEntity<?> httpEntity = new HttpEntity<>(requestBody, httpHeaders);
		// Object object = restTemplate.exchange(url,verb.PUT , httpEntity
		// ,Object.class).getBody();
		// return restTemplate.exchange(url, verb, httpEntity, Object.class);

		// ResponseEntity<?> a =
		// restTemplate.exchange(url,verb,httpEntity,Object.class);

		return null;

//		private Object httpExchangeInOneLine(String URL, HttpMethod method, Map<String, String> headerPairs, Object giveObject) {
//
//			// URL = "http://localhost:8080/company/updateCoupon";
//			HttpHeaders headers = new HttpHeaders();
//
//			// for (int i = 0; i < headerPairs.entrySet().size(); i++) {
//			// headers.add(headerPairs.keySet()); // these will not change in each loop...
//
//			// Map<String, String> map = headerPairs;
//
//			for (Entry<String, String> e : headerPairs.entrySet()) {
//				headers.add(e.getKey(), e.getValue());
//			}
//			HttpEntity<?> httpEntity = new HttpEntity<>(giveObject, headers);
//			Object obj = restTemplate.exchange(URL, HttpMethod.PUT, // where, what,
//					httpEntity, Object.class).getBody(); // give, take
//			return obj;
//		}

	}

	public void ddd() {
		restTemplate1 = restTemplateConfig.SetRestConfig(restTemplate1);

		Object object = "aaa";
		Map<String, String> mapa = new HashMap<>();
		HttpHeaders headres1 = new HttpHeaders();
		headres1.add("aa", "bb");
		HttpEntity<Object> httpEntity1 = new HttpEntity<Object>(object, headres1);
		// Object object = restTemplate.exchange(url,verb.PUT , httpEntity
		// ,Object.class).getBody();
		// return restTemplate.exchange(url, verb, httpEntity, Object.class);

		// ResponseEntity<Object> a = restTemplate.exchange("aaa", HttpMethod.POST,
		// httpEntity1, Object.class);

	}

}
