package com.yangpengyu.cms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨鹏羽
 * @version 创建时间：2019年9月23日 下午8:24:38 类功能说明
 */
public class Comment implements Serializable{

	private static final long serialVersionUID = 5272488365152386382L;

	private Integer id;	//评论id
	private Integer articleId;//文章id
	private Integer userId;//用户id
	private String userName;//用户名
	private String content;//评论内容
	private Date created;//创建时间
	private String nickname;
	private String articleTitle;//文章标题

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Comment(Integer id, Integer articleId, Integer userId,
			String userName, String content, Date created, String nickname,
			String articleTitle) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.userId = userId;
		this.userName = userName;
		this.content = content;
		this.created = created;
		this.nickname = nickname;
		this.articleTitle = articleTitle;
	}
	public Comment() {
		// TODO Auto-generated constructor stub
	}
}
