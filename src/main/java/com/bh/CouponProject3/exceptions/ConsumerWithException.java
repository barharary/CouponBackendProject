package com.bh.CouponProject3.exceptions;

public interface ConsumerWithException<T, E extends Exception> {

	void accept1(T t) throws CouponException;

}
