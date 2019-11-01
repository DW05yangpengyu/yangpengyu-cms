package com.yangpengyu.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.service.ArticleService;
@Component("kafkaConsumerListener")
public class KafkaConsumerListener implements MessageListener<String, String>{

	@Autowired
	private ArticleService as;
	@Resource
	private RedisTemplate<String, Article> redisTemplate;
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String value = data.value();
		Gson gson = new Gson();
		Article article = gson.fromJson(value, Article.class);
		int add = as.insert(article);
		/*if(article.getStatus()==1){
			// 将数据发送到elasticsearch
		IndexQuery query = new IndexQueryBuilder().withId(article.getId().toString()).withObject(article).build();		
		elasticsearchTemplate.index(query);
		}*/
		ListOperations<String, Article> list = redisTemplate.opsForList();
		if(add>0){
			Article leftPop = list.leftPop("articles");
			System.out.println("${"+article.getTitle()+"}已导入完毕");
			
		}
	}

}
