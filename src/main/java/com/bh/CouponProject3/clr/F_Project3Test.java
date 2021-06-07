package com.bh.CouponProject3.clr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.config.RestTemplateConfig;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.exceptions.CustomerException;
import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.login.ClientType;
import com.bh.CouponProject3.security.model.LoginBody;
import com.bh.CouponProject3.security.model.LoginResponse;
import com.bh.CouponProject3.utils.ArtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@Order(6)
@RequiredArgsConstructor
public class F_Project3Test implements CommandLineRunner {

	private final RestTemplateConfig restTemplateConfig;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void run(String... args) throws LoginException, CompanyException, CustomerException, SecurityException,
			CouponException, JsonMappingException, JsonProcessingException {

		restTemplate = restTemplateConfig.SetRestConfig(restTemplate);
		final String localhostURL = "http://localhost:8080";

		ArtUtils.printControllerTest();

////////////////////////////////////////////////////////////////////////////////////
///////////////////////////LOGIN SUCCESS ADMIN ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
		ArtUtils.printVvsHeadLine("Admin Login success", 1);
		String adminRegisterURL = localhostURL + "/admin/login";
		LoginBody AdminLoginBody = new LoginBody("admin@admin.com", "admin");
		HttpHeaders adminLoginHeader = new HttpHeaders();
		adminLoginHeader.add("ClientType", ClientType.ADMIN.name());
		HttpEntity<?> httpEntity = new HttpEntity<>(AdminLoginBody, adminLoginHeader);
		ResponseEntity<LoginResponse> responseEntity = restTemplate.//
				exchange(adminRegisterURL, HttpMethod.POST, httpEntity, LoginResponse.class); // NOT
		HttpStatus adminTokenIdHttpStatus = responseEntity.getStatusCode();
		LoginResponse adminTokenIdLoginResponse = (LoginResponse) responseEntity.getBody();
		String adminTokenId = adminTokenIdLoginResponse.getTokenId();
		System.out.println(adminTokenIdHttpStatus);
		System.out.println(adminTokenId);

////////////////////////////////////////////////////////////////////////////////////
///////////////////////////LOGIN FAILD ADMIN ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
		ArtUtils.printXxsHeadLine("Admin Login faild", 1);
		AdminLoginBody = new LoginBody("XXX", "XXX");
		HttpEntity<?> httpEntityFaild = new HttpEntity<>(AdminLoginBody, adminLoginHeader);
		ResponseEntity<?> responseEntityFaild = restTemplate.//
				exchange(adminRegisterURL, HttpMethod.POST, httpEntityFaild, String.class); // NOT
		adminTokenIdHttpStatus = responseEntityFaild.getStatusCode();
		String adminTokenIdLoginResponseFaild = (String) responseEntityFaild.getBody();
		System.out.println(adminTokenIdLoginResponseFaild);

////////////////////////////////////////////////////////////////////////////////////
///////////////////////////GET ALL COMPANIES ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
		Object object = "aaa";
		Map<String, String> mapa = new HashMap<>();
	HttpHeaders headres1 = new HttpHeaders();
	headres1.add("aa", "bb");
		HttpEntity<?> httpEntity1 = new HttpEntity<>(object, headres1);
		// Object object = restTemplate.exchange(url,verb.PUT , httpEntity
		// ,Object.class).getBody();
		// return restTemplate.exchange(url, verb, httpEntity, Object.class);

		ResponseEntity<?> a = restTemplate.exchange("aaa", HttpMethod.POST, httpEntity1, Object.class);
		// return null;

		ArtUtils.barTestingHeader("Get all companies");

		String allCompaniesURL = localhostURL + "/admin/allCompanies";

		HttpHeaders adminTokenIdheader = new HttpHeaders();
		adminTokenIdheader.add("tokenId", adminTokenId);
		HttpEntity<?> allCompanieshttpEntity = new HttpEntity<>(adminTokenIdheader);

		ArtUtils.printVvsHeadLine("ALL COMPANIES", 1);
		ResponseEntity<Company[]> AllCompaniesResponseEntity = restTemplate.//
				exchange(allCompaniesURL, HttpMethod.GET, allCompanieshttpEntity, Company[].class); // NOT
		System.out.println(AllCompaniesResponseEntity.getStatusCode());
		ArtUtils.insertToTable("All Companies: ", Arrays.asList(AllCompaniesResponseEntity.getBody()));

		// TODO admin methods Rest...

////////////////////////////////////////////////////////////////////////////////////
///////////////////////////LOGIN SUCCESS M&M COMPANY ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
		ArtUtils.printVvsHeadLine("Company Login success!", 1);
		String companyRegisterURL = localhostURL + "/company/login";
		LoginBody companyLoginBody = new LoginBody("M&M@M&M.com", "1234");
		HttpHeaders companyLoginHeader = new HttpHeaders();
		companyLoginHeader.add("ClientType", ClientType.COMPANY.name());
		HttpEntity<?> companyLoginHttpEntity = new HttpEntity<>(companyLoginBody, companyLoginHeader);
		ResponseEntity<?> companyLoginResponseEntity = restTemplate.//
				exchange(companyRegisterURL, HttpMethod.POST, companyLoginHttpEntity, String.class); // NOT
		String loginResponse = ((String) companyLoginResponseEntity.getBody());
		System.out.println("Status code: " + companyLoginResponseEntity.getStatusCode());
		System.out.println(loginResponse);

///////////////////////    JSON To Object for keeping companyTokenId	 ////////////////////////////////////////

		String companyTokenId = null;
		if (companyLoginResponseEntity.getStatusCodeValue() == 201) {
			LoginResponse companyToken = new ObjectMapper().readerFor(LoginResponse.class).readValue(loginResponse);
			companyTokenId = companyToken.getTokenId();
		}

		//////////////////////// ParameterizedTypeReference
		//////////////////////// //////////////////////////////////////

//		ParameterizedTypeReference<List<?>> typeRef = new ParameterizedTypeReference<List<?>>() {
//		};
//
//		System.out.println(typeRef.getType());
//
//		ResponseEntity<?> companyLoginResponseEntity1 = restTemplate.//
//				exchange(companyRegisterURL, HttpMethod.POST, companyLoginHttpEntity, typeRef); // NOT
//		LoginResponse loginResponse1 = ((LoginResponse) companyLoginResponseEntity1.getBody());
//
//		System.out.println("Status code: " + companyLoginResponseEntity1.getStatusCode());
//		System.out.println(loginResponse1);

////////////////////////////////////////////////////////////////////////////////////
///////////////////////////LOGIN FAILD M&M COMPANY ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////

		ArtUtils.printXxsHeadLine("Company Login Faild!", 1);
		String companyRegisterURLFaild = localhostURL + "/company/login";
		LoginBody companyLoginBodyFaild = new LoginBody("Buzzz@Buzz.com", "XXX");
		HttpHeaders companyLoginHeaderFaild = new HttpHeaders();
		companyLoginHeaderFaild.add("ClientType", ClientType.COMPANY.name());
		HttpEntity<?> companyLoginHttpEntityFaild = new HttpEntity<>(companyLoginBodyFaild, companyLoginHeaderFaild);
		ResponseEntity<?> companyLoginResponseEntityFaild = restTemplate.//
				exchange(companyRegisterURLFaild, HttpMethod.POST, companyLoginHttpEntityFaild, String.class); // NOT
		System.out.println(companyLoginResponseEntityFaild.getBody());

////////////////////////////////////////////////////////////////////////////////////
///////////////////////////GET COUPONS of M&M COMPANY ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
//		ArtUtils.printVvsHeadLine("Get Company Coupons!", 1);
//		String getAllCompanyCouponURL = localhostURL + "/company/myCoupons";
//		HttpHeaders companyTokenIdHeader = new HttpHeaders();
//		companyTokenIdHeader.add("tokenId", companyTokenId);
//		HttpEntity<?> companyTokenIdHttpEntity = new HttpEntity<>(companyTokenIdHeader); // !!!TOKEN-ID!!!
//		ResponseEntity<?> getAllMyCouponsResponseEntity = restTemplate.//
//				exchange(getAllCompanyCouponURL, HttpMethod.GET, companyTokenIdHttpEntity, String.class); // NOT
//
//		if (getAllMyCouponsResponseEntity.getStatusCodeValue() == 200) {
//			ObjectMapper objectMapper = new ObjectMapper();
//			String jasonCoupons = (String) getAllMyCouponsResponseEntity.getBody();
//			Coupon[] coupons = objectMapper.readValue(jasonCoupons, Coupon[].class);
//			List<Coupon> couponsList = Arrays.asList(coupons);
//			ArtUtils.insertToTable("REST TEMPLATE GET COMPANY COUPONS", couponsList);
//		}
//
/////////////////////////////GET COUPON of M&M COMPANY  BY CATEGORY ///////////////////////////////
//
//		Category category = Category.SHOPING;
//		ArtUtils.printVvsHeadLine("Company Coupons by catrgory: " + category.name(), 1);
//		String CompanyCouponByCategoryURL = localhostURL + "/company/myCouponsByCategory";
//		HttpHeaders companyHeaderCouponsByCategory = new HttpHeaders();
//		companyHeaderCouponsByCategory.add("tokenId", companyTokenId);
//		companyHeaderCouponsByCategory.add("category", category.name());
//
//		HttpEntity<?> couponsByCategoryHttpEntity = new HttpEntity<>(companyHeaderCouponsByCategory); // !!!TOKEN-ID!!!
//		ResponseEntity<?> CouponsByCategoryResponseEntity = restTemplate.//
//				exchange(CompanyCouponByCategoryURL, HttpMethod.GET, couponsByCategoryHttpEntity, String.class); // NOT
//
//		if (CouponsByCategoryResponseEntity.getStatusCodeValue() == 200) {
//			ObjectMapper objectMapper = new ObjectMapper();
//			String jasonCoupons = (String) CouponsByCategoryResponseEntity.getBody();
//			Coupon[] coupons = objectMapper.readValue(jasonCoupons, Coupon[].class);
//			List<Coupon> couponsList = Arrays.asList(coupons);
//			ArtUtils.insertToTable("REST TEMPLATE GET COMPANY COUPONS BY CATEGORY", couponsList);
//		}
//
/////////////////////////////GET COUPON of M&M COMPANY  BY MAX PRICE ///////////////////////////////
//		String maxPrice = "70.0";
//		ArtUtils.printVvsHeadLine("Company Coupons by MAX PRICE of: " + maxPrice, 1);
//		String CompanyCouponByMaxPriceURL = localhostURL + "/company/myCouponsByMaxPrice";
//		HttpHeaders companyHeaderCouponsByMaxPrice = new HttpHeaders();
//		companyHeaderCouponsByMaxPrice.add("tokenId", companyTokenId);
//		companyHeaderCouponsByMaxPrice.add("maxPrice", maxPrice);
//
//		HttpEntity<?> couponsByMaxPriceHttpEntity = new HttpEntity<>(companyHeaderCouponsByMaxPrice); // !!!TOKEN-ID!!!
//		ResponseEntity<?> CouponsByMaxPriceResponseEntity = restTemplate.//
//				exchange(CompanyCouponByMaxPriceURL, HttpMethod.GET, couponsByMaxPriceHttpEntity, String.class); // NOT
//		List<Coupon> couponsList = null;
//		if (CouponsByMaxPriceResponseEntity.getStatusCodeValue() == 200) {
//			ObjectMapper objectMapper = new ObjectMapper();
//			String jasonCoupons = (String) CouponsByMaxPriceResponseEntity.getBody();
//			Coupon[] allMyCoupons = objectMapper.readValue(jasonCoupons, Coupon[].class);
//			couponsList = Arrays.asList(allMyCoupons);
//			ArtUtils.insertToTable("REST TEMPLATE GET COMPANY COUPONS BY MAX PRICE", couponsList);
//		}
//
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////DELETE COUPON of M&M COMPANY ///////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
//		List<Integer> IdsToDelete = new ArrayList<>();
//		Integer[] idsArray = { 1, 2, 4, 9, 10 };
//		IdsToDelete.addAll(Arrays.asList(idsArray));
//		ArtUtils.printVvsHeadLine("DELETE Company Coupon Id: " + IdsToDelete, 1);
//		IdsToDelete.forEach(id -> {
//			String DeleteCompanyCouponURL = localhostURL + "/company/" + id;
//			ResponseEntity<?> deleteResponseEntity = restTemplate.exchange(DeleteCompanyCouponURL, //
//					HttpMethod.DELETE, companyTokenIdHttpEntity, String.class);
//			if (deleteResponseEntity.getStatusCodeValue() != 204) {
//				System.out.println(deleteResponseEntity.getBody());
//			}
//		});
//
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////Get M&M Company Details ///////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//		ArtUtils.printVvsHeadLine("GET Company details", 1);
//		String getMyCompanyURL = localhostURL + "/company/getCompanyDetails";
//		ResponseEntity<Company> getMyCompanyResponseEntity = restTemplate.//
//				exchange(getMyCompanyURL, HttpMethod.GET, companyTokenIdHttpEntity, Company.class); // NOT
//		Company myCompany = getMyCompanyResponseEntity.getBody();
//		System.out.println("Status code: " + getMyCompanyResponseEntity.getStatusCode());
//		System.out.println("THIS IS MY COMPANY: ");
//		System.out.println(myCompany);
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////SET Coupon To M&M Company ///////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//		Coupon coupon = FactoryUtils.bringOneCoupon();
//		coupon.setCompany(myCompany);
//		coupon.setCouponTitle("1+1!   ");
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////ADD COUPON to M&M COMPANY ///////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//		ArtUtils.printVvsHeadLine("Company add Coupon titled: " + coupon.getCouponTitle() + " success!", 1);
//		String addCouponURL = localhostURL + "/company";
//		HttpEntity<?> addCouponHttpEntity = new HttpEntity<>(coupon, companyTokenIdHeader);
//		ResponseEntity<String> addCouponResponseEntity = restTemplate.//
//				exchange(addCouponURL, HttpMethod.POST, addCouponHttpEntity, String.class); // NOT
//		HttpStatus addCouponHttpStatus = addCouponResponseEntity.getStatusCode();
//		System.out.println(addCouponHttpStatus);
//
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////ADD COUPON WITH EXCEPTION ////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//		ArtUtils.printXxsHeadLine("Company add Coupon FAILED!", 1);
//
//		coupon.setCouponTitle("1+1!   ");
//		HttpEntity<?> addCouponHttpEntityExcep = new HttpEntity<>(coupon, companyTokenIdHeader);
//		ResponseEntity<String> addCouponResponseEntityExcep = restTemplate.//
//				exchange(addCouponURL, HttpMethod.POST, addCouponHttpEntityExcep, String.class); // NOT
//		System.out.println(addCouponResponseEntityExcep.getStatusCode());
//		System.out.println(addCouponResponseEntityExcep.getBody());
//
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////GET COUPONS of M&M COMPANY ///////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//
//		getAllMyCouponsResponseEntity = restTemplate.//
//				exchange(getAllCompanyCouponURL, HttpMethod.GET, companyTokenIdHttpEntity, String.class); // NOT
//
//		if (getAllMyCouponsResponseEntity.getStatusCodeValue() == 200) {
//			ObjectMapper objectMapper = new ObjectMapper();
//			String jasonCoupons = (String) getAllMyCouponsResponseEntity.getBody();
//			Coupon[] coupons = objectMapper.readValue(jasonCoupons, Coupon[].class);
//			couponsList = Arrays.asList(coupons);
//			ArtUtils.insertToTable("REST TEMPLATE GET COMPANY COUPONS", couponsList);
//		}
//
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////    UPDATE COUPON     ///////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//
//		Coupon couponToUpdate = couponsList.get(0);
//		couponToUpdate.setCouponTitle("Ultemate!");
//		couponToUpdate.setAmount(135);
//		ArtUtils.printVvsHeadLine("Company UPDATE Coupon Id: " + couponToUpdate.getId(), 1);
//		String UpdateCouponURL = localhostURL + "/company";
//		HttpEntity<?> updateCouponHttpEntity = new HttpEntity<>(couponToUpdate, companyTokenIdHeader);
//		ResponseEntity<String> updateCouponResponseEntity = restTemplate.//
//				exchange(UpdateCouponURL, HttpMethod.PUT, updateCouponHttpEntity, String.class); // NOT
//		HttpStatus updateCouponHttpStatus = updateCouponResponseEntity.getStatusCode();
//		System.out.println(updateCouponHttpStatus);
//		System.out.println(updateCouponResponseEntity.getBody());
//
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////GET COUPONS of M&M COMPANY ///////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
//
//		ResponseEntity<Coupon[]> getAllMyCouponsResponseEntity1 = restTemplate.//
//				exchange(getAllCompanyCouponURL, HttpMethod.GET, companyTokenIdHttpEntity, Coupon[].class); // NOT
//
//		if (getAllMyCouponsResponseEntity1.getStatusCodeValue() == 200) {
//			ObjectMapper objectMapper = new ObjectMapper();
//			Coupon[] jasonCoupons = getAllMyCouponsResponseEntity1.getBody();
//			// Coupon[] coupons = objectMapper.readValue(jasonCoupons, Coupon[].class);
//			// couponsList = Arrays.asList(coupons);
//			couponsList = Arrays.asList(jasonCoupons);
//			ArtUtils.insertToTable("REST TEMPLATE GET COMPANY COUPONS", couponsList);
//		}

	}

}
