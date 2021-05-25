package com.bh.CouponProject3.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity // to see in the DB
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Categories")

public enum Category {

	FOOD(1), SHOPING(2), TRIP(3);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int Value;

	public int getValue() {
		return Value;
	}
}
