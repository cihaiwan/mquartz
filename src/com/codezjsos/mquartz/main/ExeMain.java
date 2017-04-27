package com.codezjsos.mquartz.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExeMain {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
	}
}
