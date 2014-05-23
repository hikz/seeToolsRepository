package com.seetools.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanFactory {

	
	public static Object getBean(String beanName){
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		return applicationContext.getBean(beanName);
	}
}
