package com.yangpengyu.cms.yy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.entity.Category;
import com.yangpengyu.cms.entity.Channel;
import com.yangpengyu.cms.service.CategoryService;
import com.yangpengyu.cms.service.ChannelService;
import com.yangpengyu.cms.service.RedisArticleService;
import com.yangpengyu.cms.utils.RandomUtil;
import com.yangpengyu.common.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-beans.xml" })
public class RedisTest {

	@Resource
	private ChannelService channelService;

	@Resource
	private CategoryService categoryService;
	
	@Resource
	private RedisArticleService ras;

	@Test
	public void save() throws Exception {
		
		List<Article> articles = new ArrayList<Article>();
		
		// 读取文件
		// 读取多个文件
		List<String> fileList = FileUtils.getFileList("D:\\1705DJsoup");

		// 读取文件内容
		for (String file_path : fileList) {

			// 获取到文件内容
			String content = FileUtils.readFile(file_path);

			// 获取到文章的名称 将\和.txt去掉
			String title = file_path.substring(file_path.lastIndexOf("\\") + 1, file_path.lastIndexOf("."));

			// 创建实体类对象
			Article article = new Article();

			article.setTitle(title); // 名称
			article.setContent(content); // 内容

			if (content.length() <= 140) {
				article.setRemark(content);
			} else {
				article.setRemark(content.substring(0, 140));
			}

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
			article.setStatus(0);
			
			//设置用户ID
			article.setUserId(39);
			Date randomDate = RandomUtil.randomDate("2018-01-01","2019-10-30");
			article.setCreated(randomDate);
			articles.add(article);
		}
		ras.save(articles);
		System.out.println("存储完毕");
	}

}
