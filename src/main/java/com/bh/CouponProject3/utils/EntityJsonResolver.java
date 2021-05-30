package com.bh.CouponProject3.utils;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;

@Component
public class EntityJsonResolver implements ObjectIdResolver {

	@Autowired
	EntityManager entityManger;

	@Override
	public void bindItem(IdKey id, Object pojo) {
	}

	@Override
	public Object resolveId(IdKey id) {

		return entityManger.find(id.scope, id.key);
	}

	@Override
	public ObjectIdResolver newForDeserialization(Object context) {
		// FIXME please... Auto-generated method stub
		return this;
	}

	@Override
	public boolean canUseFor(ObjectIdResolver resolverType) {
		// FIXME please... Auto-generated method stub
		return false;
	}

}
