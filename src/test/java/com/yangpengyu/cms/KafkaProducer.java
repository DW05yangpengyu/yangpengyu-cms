package com.yangpengyu.cms;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.yangpengyu.cms.entity.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-beans.xml", "classpath:spring-kafka-producer.xml" })
public class KafkaProducer {
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;

	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	@Test
	public void sendMsg() throws Exception {
		//从redis获取数据
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		
		List<Article> list = opsForList.range("articles", 0, -1);
		//发送到kafka
		for (Article article : list) {
			//转成json
			Gson gson = new Gson();
			
			String json = gson.toJson(article);
			//发送
			kafkaTemplate.sendDefault(json);
		}
	}
}
