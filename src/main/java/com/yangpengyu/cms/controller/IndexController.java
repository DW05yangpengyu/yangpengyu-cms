package com.yangpengyu.cms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.entity.Channel;
import com.yangpengyu.cms.entity.Link;
import com.yangpengyu.cms.service.ArticleService;
import com.yangpengyu.cms.service.ChannelService;
import com.yangpengyu.cms.utils.ESUtils;
import com.yangpengyu.cms.utils.PageUtil;

/**
*@author 杨鹏羽
*@version 
*主页控制层
*/
@Controller
public class IndexController {
private Logger log = Logger.getLogger(IndexController.class);
	
	@Autowired
	ChannelService cService;
	
	@Autowired
	ArticleService articleService ;
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	/**
	 * 获取热门，最新，友情链接，以及搜索功能数据
	 * @param request
	 * @param pageSize
	 * @param pageNum
	 * @param key
	 * @return
	 */
	@RequestMapping(value= {"/index","/",""},method=RequestMethod.GET)
	public  String index(HttpServletRequest request,
			 @RequestParam( value="pageSize",defaultValue = "3") Integer pageSize,
			 @RequestParam(value="page",defaultValue = "1") Integer pageNum,
			 @RequestParam(defaultValue = "") String key 
			) {
		
		List<Channel> channels = cService.getChannels();
		request.setAttribute("channels", channels);
		
		//获取热门
		PageInfo<Article> arPage = null;
		if(key == null || "".equals(key.trim())){
			arPage = articleService.listhots(key,pageNum, pageSize);
		}else{
			AggregatedPage<Article> page = (AggregatedPage<Article>)ESUtils.selectObjects(elasticsearchTemplate, Article.class, 
					pageNum-1, pageSize, "id", new String[]{"title"}, key);
					
			List<Article> content = page.getContent();
			arPage = new PageInfo<Article>(content);
			//设置总页数
			arPage.setPages(page.getTotalPages());
			
			//设置当前页
			arPage.setPageNum(pageNum);
			
		}
		request.setAttribute("pageInfo", arPage);
		request.setAttribute("key", key);
		//获取最新
		List<Article> lastArticles = articleService.last();
		request.setAttribute("lasts", lastArticles);
		//友情链接
		List<Link> links =  new ArrayList<Link>();
		links.add(new Link("http://www.bwie.net","八维好厉害"));
		links.add(new Link("http://www.bwie.org","八维真牛"));
		links.add(new Link("http://www.bwie.com","八维顶呱呱"));
		request.setAttribute("links", links);
		
		String pageString = PageUtil.page(arPage.getPageNum(), arPage.getPages(), "/article/hots?key="+key, arPage.getPageSize());
		request.setAttribute("pageStr", pageString);
		return "index/index";
	}
	
}
