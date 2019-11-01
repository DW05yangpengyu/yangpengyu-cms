package com.yangpengyu.cms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.mapper.ArticleMapper;
import com.yangpengyu.cms.service.ArticleService;

/**
*@author 杨鹏羽
*@version 创建时间：2019年9月21日 下午3:49:25
*文章业务层
*/
@Service
public class ArticleServiceImpl implements ArticleService {

	
	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	RedisTemplate<String,Article> redisTemplate;
	
	@Override
	public int post(Article article) {
		return articleMapper.add(article);
	}

	@Override
	public int update(Article article) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logicDelete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logicDeleteBatch(Integer[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Article article) {
		System.err.println(article);
		return articleMapper.add(article);
	}

	@Override
	public int check(Integer id, Integer status) {
		return articleMapper.updateStatus(id,status);
	}

	@Override
	public int setHot(Integer id, Integer status) {
		return articleMapper.updateHot(id,status);
	}

	
	@Override
	public int updatea(Integer id, String title, Integer categoryId, Integer channelId, String content1) {
		return articleMapper.updatea(id, title, categoryId, channelId, content1);
	}
	
	@Override
	public Article findById(Integer articleId) {
		return articleMapper.findById(articleId);
	}

	@Override
	public PageInfo<Article> list(Integer pageNum, Integer channelId, Integer cid) {
		if(pageNum==0) {
			PageHelper.startPage(pageNum, articleMapper.list(0, 0).size());
			List<Article> articles =   articleMapper.list(channelId,cid);
			return new PageInfo<Article>(articles);
		}else {
			PageHelper.startPage(pageNum, 3);
			List<Article> articles =   articleMapper.list(channelId,cid);
			return new PageInfo<Article>(articles);
		}
		
	}

	@Override
	public PageInfo<Article> getByUserId(Integer id, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<Article> pageInfo = new PageInfo<Article>(articleMapper.listByUser(id));
		
		return pageInfo;
	}

	@Override
	public PageInfo<Article> checkList(Integer status, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Article> articles=  articleMapper.checkList(status);
		
		return new PageInfo<Article>(articles);
	}

	@Override
	public PageInfo<Article> listhots(String title,Integer pageNum, Integer pageSize) {
		
		System.out.println("title   is ============ " + title);
		List<Article> articles= null;
		
		//获取redis操作list集合的对象
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		
		//先查询redis中是否存储热点文章，
		if(redisTemplate.hasKey("hots_list")) {
			//如果存储，则直接取出
			//articles = opsForList.range("hots_list",0 ,-1);
			articles = opsForList.range("hots_list",(pageNum - 1) * pageSize, pageNum * pageSize - 1);
			
		}else {
			//如果没有，则查询数据库，并将数据存储到redis中。
			//查询数据库
			List<Article> hotList = articleMapper.hotList(title);
			
			PageHelper.startPage(pageNum, pageSize);
			articles =  articleMapper.hotList(title);
			
			//存入redis
			opsForList.rightPushAll("hots_list", hotList);
		}
		PageInfo<Article> pageInfo = new PageInfo<Article>(articles);
		//获取总条数
		int totils = opsForList.size("hots_list").intValue();
		int pages = totils / pageSize;
		if(totils % pageSize >0) {
			pages ++;
		}
		System.out.println("总页数"+pages);
		//设置总页数
		pageInfo.setPages(pages);
		//设置当前页
		pageInfo.setPageNum(pageNum);
		return pageInfo;
	}

	@Override
	public List<Article> last() {
		List<Article> list = null;
		
		//获取redis操作list集合的对象
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		
		//先查询redis中是否存储最新文章
		if(redisTemplate.hasKey("last_list")) {
			//如果存储，则直接取出；
			list = opsForList.range("last_list", 0,-1);
		}else {
			//如果没有，则查询数据库，并将数据存储到redis中。
			list = articleMapper.lastArticles();
			opsForList.rightPushAll("last_list", list);
		}
		return list;
	}

	@Override
	public List<Article> gethits() {
		// TODO Auto-generated method stub
		return articleMapper.gethits();
	}

	@Override
	public List<Article> getcomment() {
		// TODO Auto-generated method stub
		return articleMapper.getcomment();
	}

	@Override
	public int insert(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.insert(article);
	}
	

}

