package com.yangpengyu.cms;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yangpengyu.cms.entity.Article;
import com.yangpengyu.cms.service.ArticleService;
import com.yangpengyu.common.utils.FileUtils;

public class MonthTest extends TestBase{
	@Autowired 
	ArticleService arService;
	
	@Test
	public void testarticle(){
		
		List<String> fileList = FileUtils.getFileList("C:\\Users\\18437\\Desktop\\附件");
		Random random = new Random();
		for (String string : fileList) {
			try {
				Article article = new Article();
				String file = FileUtils.readFile(string);
				article.setContent(file);
				article.setTitle(string.substring(string.lastIndexOf("\\")+1, string.lastIndexOf('.')));
				article.setHot(random.nextInt(2));
				article.setUserId(39);
				article.setChannelId(1+random.nextInt(8));
				int add = arService.add(article);
				if(add>0){
					System.out.println("导入成功");
				}
			} catch (Exception e) {
				System.out.println(string);
			}
		}
	}
}
