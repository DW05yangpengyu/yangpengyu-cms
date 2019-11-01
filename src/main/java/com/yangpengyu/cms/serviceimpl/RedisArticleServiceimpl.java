package com.yangpengyu.cms.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.service.RedisArticleService;

@Service
public class RedisArticleServiceimpl implements RedisArticleService{
	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;

	
	public void save(List<Article> articles) {
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring-kafka-producer.xml","classpath:spring-beans.xml");*/
		// 存入redis
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		opsForList.rightPushAll("articles", articles);
		
		//从redis获取数据
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
	
	
	
	