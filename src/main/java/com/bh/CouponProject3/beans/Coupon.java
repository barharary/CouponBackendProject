package com.bh.CouponProject3.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.bh.CouponProject3.exceptions.CouponException;
import com.bh.CouponProject3.repository.CompanyRepository;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "coupons")
public class Coupon implements Comparable<Coupon> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JsonIdentityReference(alwaysAsId = true)
	@JsonIgnoreProperties("companyCoupons")
	private Company company;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, name = "category_name")
	private Category category;

	@Column(nullable = false)
	private String couponTitle;

	@Column(nullable = false)
	private String couponDescription;

	@Column(nullable = false)
	private Date startDate;

	@Column(nullable = false)
	private Date endDate;

	@Column(nullable = false)
	private int amount;

	@Column(nullable = false)
	private double price;

	@Column(nullable = false)
	private String image;

	@Override
	public String toString() {
		return "|" + id + "   |" + company.getId() + "\t       |" + category + "\t  |" //
				+ couponTitle + "\t|" + descriptionManipulation() + "\t|" + startDate.getDate() + "."
				+ (startDate.getMonth() + 1) + "." + (startDate.getYear() + 1900)//
				+ "\t|" + endDate.getDate() + "." + (endDate.getMonth() + 1) + "." + (endDate.getYear() + 1900) + "\t|"
				+ amount + "\t|" + price + "\t|" + image + "\t|";
	}

	@Override
	public int compareTo(Coupon o) {
		return (int) ((this.getId() - o.getId()));
	}

	public String descriptionManipulation() {

		return (couponDescription.length() < 12) ? couponDescription : couponDescription.substring(0, 12) + "..";
	}

}
