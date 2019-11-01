package com.yangpengyu.cms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KafkaConsumerTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext cpxac = new ClassPathXmlApplicationContext("classpath:spring-kafka-consumer.xml","classpath:spring-beans.xml");
		
	}
}
