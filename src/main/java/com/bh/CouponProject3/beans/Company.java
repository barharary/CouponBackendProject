package com.bh.CouponProject3.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bh.CouponProject3.utils.EntityJsonResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Table(name = "companies")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityJsonResolver.class,scope = Company.class)
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Singular
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "company")
	//@JsonIgnoreProperties("company")
	private List<Coupon> companyCoupons = new ArrayList<>();

	@Override
	public String toString() {
		return "|" + id + "\t|" + name + "    \t|" + email + "    \t\t|" + password + "  \t\t|"
				+ companyCoupons.stream().map(c -> c.getId()).sorted().collect(Collectors.toList()) + "\t\t\t\t\t|";
	}
}
