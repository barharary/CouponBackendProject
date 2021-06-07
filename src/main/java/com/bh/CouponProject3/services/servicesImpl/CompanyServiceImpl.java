package com.bh.CouponProject3.services.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.LoginException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.bh.CouponProject3.beans.Category;
import com.bh.CouponProject3.beans.Company;
import com.bh.CouponProject3.beans.Coupon;
import com.bh.CouponProject3.exceptions.CompanyException;
import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.repository.CompanyRepository;
import com.bh.CouponProject3.repository.CouponRepository;
import com.bh.CouponProject3.repository.CustomerRepository;
import com.bh.CouponProject3.services.ClientService;
import com.bh.CouponProject3.services.CompanyService;

@Scope("prototype")
@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

	private int companyId;

	@Override
	public boolean login(String companyEmail, String companyPassword) throws LoginException {
		Company company = companyRepository.findIdByEmailAndPassword(companyEmail, companyPassword)
				.orElseThrow(() -> new LoginException("Incorrect email or password."));
		this.companyId = company.getId();
		return true;
	}

	@Override
	public void addCoupon(Coupon coupon) throws CouponException {
		
		if(coupon.getCompany().getId() != this.companyId) {
			throw new CouponException("You try to ADD coupon that NOT your own companyId. change the companyId of the coupon to your own.");
		}
		
		if (couponRepository.findByCouponTitleAndCompanyId(coupon.getCouponTitle(), this.companyId).isPresent()) {
			throw new CouponException("Coupon Titled " + coupon.getCouponTitle() + " Already exists.");
		} 
		couponRepository.save(coupon); 

	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponException {
	
		if(coupon.getCompany().getId() != this.companyId) {
			throw new CouponException("You try to UPDATE coupon that NOT your own companyId. change the companyId of the coupon to your own.");
		}
		
		if (!couponRepository.existsByIdAndCompanyId(coupon.getId(), this.companyId)) {
			throw new CouponException("\n"
					+ "Cannot change couponId and CompanyId OR you dont have this couponId in your company coupon list.");
		}
		couponRepository.saveAndFlush(coupon);
	}

	@Override
	public void deleteCoupon(int couponId) throws CouponException {
		if (!couponRepository.existsByIdAndCompanyId(couponId, this.companyId)) {
			throw new CouponException("Coupon Id " + couponId + " does not in your coupon List.");
		}

		couponRepository.deleteById(couponId);
	}

	@Override
	public List<Coupon> getComapnyCoupons() {
		return couponRepository.findAllByCompanyId(this.companyId).stream().sorted().collect(Collectors.toList());
	}

	@Override
	public List<Coupon> getComapnyCoupons(Category category) {
		return couponRepository.findAllByCompanyIdAndCategory(this.companyId, category);
	}

	@Override
	public List<Coupon> getComapnyCoupons(double maxPrice) {
		return couponRepository.findAllByCompanyIdAndPriceLessThan(this.companyId, maxPrice);
	}

	@Override
	public Company getCompanyDetails() throws CompanyException {
		return companyRepository.findById(this.companyId).orElseThrow(() -> new CompanyException("Problem with Id."));
	}

	public CompanyServiceImpl(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super(companyRepository, customerRepository, couponRepository);
	}

	@Override
	public Company getOneCompany(int id) throws CompanyException {
		return companyRepository.findById(id).orElseThrow(() -> new CompanyException("Company Id not found."));
	}

	public int getCompanyId() {
		return companyId;
	}

}
