package com.yangpengyu.cms;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.yangpengyu.common.utils.FileUtils;



public class JsoupTest {
	@Test
	public void test_xinlang() throws IOException{
		//记录文章数
		int count =0;
		//获取连接对象
		Connection connect = Jsoup.connect("https://news.sina.com.cn/");
		//获取文档对象
		Document document = connect.get();
		//获取当前文档的所有超链接
		Elements ahrefs = document.select("a[href]");
		//遍历元素对象
		for (Element href : ahrefs) {
			//超链接的URL地址
			String url = href.attr("href");
			//定义表达式 https://news.163.com开头，一html结尾
			
			String regex = "https://news\\.sina\\.com\\.cn.*shtml$";
			
			
			//特殊要求
			if(url !=null &&Pattern.matches(regex, url)){
				//连接的文本内容
				String title = href.text();
				System.out.println(url + "@@@@@@@@@" + title);
				count++;
				//获取文章的文档对象
				Document articleDoc = Jsoup.connect(url).get();
				//获取文章的内容元素对象
				Element byId = articleDoc.getElementById("article");
				//判断元素是否为空
				if(byId!=null){
					String content = byId.text();
					
					//去除标题中的图书符号
					title=title.replace("?","").replace(":","").replace("\"","");
					
					//写入到文件中
					FileUtils.writeFile("D:\\1705DJsoup\\" + title + ".txt", content, "utf8");
				}
			}
		}
		System.out.println("首页中找到了符合条件的网址有："+count+"篇文章");	
	}
	
	
	@Test
	public void xx() throws IOException{
		//记录文章数
		int count =0;
		//获取连接对象https://www.bxwx8.org/b/19/19765/index.html
		Connection connect = Jsoup.connect("https://www.bxwx8.org/b/19/19765/index.html");
		//获取文档对象
		Document document = connect.get();
		//获取当前文档的所有超链接
		Elements ahrefs = document.select("a[href]");
		//遍历元素对象
		for (Element href : ahrefs) {
			//超链接的URL地址
			String url = href.attr("href");
			//定义表达式 https://news.163.com开头，一html结尾https://www.bxwx8.org/b/19/19765/3600274.html
			
			String regex = "[0-9]+\\.html";
		
			
			//特殊要求
			if(url !=null &&Pattern.matches(regex, url)){
				//连接的文本内容
				String title = href.text();
				System.out.println(url + "@@@@@@@@@" + title);
				count++;
				//获取文章的文档对象
				Document articleDoc = Jsoup.connect("https://www.bxwx8.org/b/19/19765/" +url).get();
				//获取文章的内容元素对象
				Element byId = articleDoc.getElementById("content");
				//判断元素是否为空
				if(byId!=null){
					String content = byId.text();
					
					//去除标题中的图书符号
					title=title.replace("?","").replace(":","").replace("\"","");
					
					//写入到文件中
					FileUtils.writeFile("D:\\1705DJsoup\\" + title + ".txt", content, "utf8");
				}
			}
		}
		System.out.println("首页中找到了符合条件的网址有："+count+"篇文章");	
	}
	
}
