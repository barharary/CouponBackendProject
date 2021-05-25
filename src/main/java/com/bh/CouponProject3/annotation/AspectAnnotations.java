package com.bh.CouponProject3.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.bh.CouponProject3.exceptions.SecurityException;
import com.bh.CouponProject3.security.TokenManager;
import com.bh.CouponProject3.security.login.ClientType;

import lombok.RequiredArgsConstructor;

@Component
@Aspect
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class AspectAnnotations {

	private final TokenManager tokenManager;

	@Before("@annotation(TokenCheckAndUpdate)")
	public void isTokenValid(JoinPoint joinPoint) throws SecurityException {
		MethodSignature m = (MethodSignature) joinPoint.getSignature();
		System.out.println("Method in action ===> " + m.getName());
		Object[] parameters = joinPoint.getArgs();
		if (parameters[0] instanceof String) {
			String tokenId = (String) parameters[0];
			tokenManager.isTokenExists(tokenId);
			ClientType clientTypeFromAnnotation = m.getMethod().getAnnotation(TokenCheckAndUpdate.class).clientType();
			tokenManager.isServiceTypeCorrect(tokenId, clientTypeFromAnnotation);
			tokenManager.isTokenExpaired(tokenId);

		}
	}

	@After("@annotation(TokenCheckAndUpdate)")
	public void updateTimeStamp(JoinPoint joinPoint) throws SecurityException {
		Object[] parameters = joinPoint.getArgs();
		MethodSignature m = (MethodSignature) joinPoint.getSignature();
		if (parameters[0] instanceof String && m.getParameterNames()[0].equals("tokenId")) {
			System.out.println("parameters[0] instanceof:  "+parameters[0].getClass().getSimpleName() + "  " + "getParameterNames()[0] = " +m.getParameterNames()[0] );
			String tokenId = (String) parameters[0];
			tokenManager.updateTimeStamp(tokenId);

		}
		System.out.println("TEST: tokenMap after evry request  web");
		tokenManager.getMap().keySet().forEach(System.out::println);

	}

}
