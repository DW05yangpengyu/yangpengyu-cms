package com.yangpengyu.cms.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.service.ArticleService;

@Component("kafkaConsumerListenertwo")
public class AllConsumerListener implements MessageListener<String, String>{
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	private ArticleService as;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String value = data.value();
		Gson gson = new Gson();
		Article article = gson.fromJson(value, Article.class);
		// 将数据发送到elasticsearch
		IndexQuery query = new IndexQueryBuilder().withId(article.getId().toString()).withObject(article).build();		
		elasticsearchTemplate.index(query);
	
	}
}
