package com.yangpengyu.cms.yy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KafkaConsumertwo {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring-kafka-consumertwo.xml","classpath:spring-beans.xml");

	}
}
