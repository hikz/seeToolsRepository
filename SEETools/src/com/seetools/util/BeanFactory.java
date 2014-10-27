package com.seetools.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {

	
	public static Object getBean(String beanName){
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bean-config.xml");
		
		Object obj = applicationContext.getBean(beanName);
		
		applicationContext.close();
		
		return obj;
	}
}
