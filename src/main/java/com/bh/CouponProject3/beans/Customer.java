package com.bh.CouponProject3.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	// Learn for after: set auto primary key X 2
	@ManyToMany(cascade = CascadeType.ALL)
	@Singular
	@JoinTable(name = "customers_vs_coupons")
	private List<Coupon> Coupons;

	@Override
	public String toString() {
		return "|" + id + "\t|" + firstName + "\t|" + lastName + "\t|" + email + "\t|" + password + "\t\t|"
				+ Coupons.stream().map(c -> c.getId()).sorted().collect(Collectors.toList()) + "\t\t\t\t\t\t|";
	}

}
