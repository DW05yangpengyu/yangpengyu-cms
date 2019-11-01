package com.yangpengyu.cms.service;

import java.util.List;

import com.yangpengyu.cms.entity.Article;

public interface RedisArticleService {
	void save(List<Article> articles);
}
