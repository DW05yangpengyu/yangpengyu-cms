package com.yangpengyu.cms;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangpengyu.cms.entity.Article;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ESTest {

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Test
	public void testAdd() {
		for(int i =0;i< 10;i++) {
			Article article = new Article();
			article.setId(i + 1);
			article.setTitle("中华人民共和国" + i);
			
			IndexQuery query = new IndexQuery();
			query.setId(article.getId().toString());
			query.setObject(article);
			elasticsearchTemplate.index(query );
		}
	}
}
