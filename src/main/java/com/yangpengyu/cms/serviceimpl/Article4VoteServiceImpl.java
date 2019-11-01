package com.yangpengyu.cms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangpengyu.cms.entity.Article4Vote;
import com.yangpengyu.cms.entity.VoteStatic;
import com.yangpengyu.cms.mapper.Article4VoteMapper;
import com.yangpengyu.cms.service.Article4VoteService;

/**
*@author 杨鹏羽
*@version 创建时间：2019年9月21日 下午3:44:38
*投票业务层
*/
@Service
public class Article4VoteServiceImpl implements Article4VoteService {

	@Autowired
	Article4VoteMapper avMapper;
	
	@Override
	public int publish(Article4Vote av) {
		return avMapper.add(av);
	}

	@Override
	public int vote(Integer userId, Integer articleId, Character option) {
		return avMapper.vote(userId,  articleId,  option);
	}

	@Override
	public Article4Vote findById(Integer id) {
		return avMapper.getById(id);
	}

	@Override
	public List<Article4Vote> list() {
		return avMapper.list();
	}

	@Override
	public List<VoteStatic> getVoteStatics(Integer articleId) {
		return avMapper.getVoteStatics(articleId);
	}

}
