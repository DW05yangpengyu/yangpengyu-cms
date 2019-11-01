package com.yangpengyu.cms;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.entity.Category;
import com.yangpengyu.cms.entity.Channel;
import com.yangpengyu.cms.service.ArticleService;
import com.yangpengyu.cms.service.CategoryService;
import com.yangpengyu.cms.service.ChannelService;
import com.yangpengyu.cms.utils.RandomUtil;
import com.yangpengyu.common.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class KafkaProduceTest {
	
	@Autowired
	public KafkaTemplate<String, String> kafka;
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private CategoryService categoryService;
	@Test
	public void test01() throws IOException{
		
		List<String> fileList = FileUtils.getFileList("D:\\1705DJsoup");
		Article article = null;
		for (String string : fileList) {
			article = new Article();
			String title = string.substring(string.lastIndexOf("\\"), string.lastIndexOf("."));
			
			String context = FileUtils.readFile(string);
			
			if(context.length()<140){
				article.setRemark(context);
			}else{
				article.setRemark(context.substring(0,140));
			}
			
			article.setTitle(title);
			article.setContent(context);
			
			Random random = new Random();
			
			//点击量
			article.setHits(random.nextInt(10000));
			
			//是否热门 0 1
			article.setHot(random.nextInt(2));
			
			//频道
			List<Channel> channels = channelService.getChannels();
			
			//获取随机频道
			Channel channel = channels.get(random.nextInt(channels.size()));
			
			article.setChannelId(channel.getId());
			
			//分类
			List<Category> categories = categoryService.getCategoryByChId(channel.getId());
			if(categories != null && categories.size() > 0) {
				//根据随机下标，获取对应的数据
				Category category = categories.get(random.nextInt(categories.size()));
				
				article.setCategoryId(category.getId());
			}
			article.setStatus(1);
			
			//设置用户ID
			article.setUserId(39);
			Date randomDate = RandomUtil.randomDate("2018-12-12","2019-10-25");
			article.setCreated(randomDate);
			Gson gson = new Gson();
			String json = gson.toJson(article);
			
			kafka.sendDefault(json);
		}
		
		
		
		 
	}
}
