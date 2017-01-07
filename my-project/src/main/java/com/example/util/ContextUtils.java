package com.example.util;

import java.beans.Introspector;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ClassUtils;

public class ContextUtils {

	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext context) {
		ContextUtils.context = context;
	}
	
	public static String getBeanName(Object bean){
		String shortClassName = ClassUtils.getShortName(bean.getClass());
		return Introspector.decapitalize(shortClassName);
	}
	
}
