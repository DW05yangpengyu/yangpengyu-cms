package com.yangpengyu.cms.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
*@author 杨鹏羽
*@version 创建时间：2019年9月18日 下午7:35:50
*类功能说明
*/

@Document(indexName="cms",type="article")
public class Article implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 328422532115312725L;
	@Id
	private Integer id;
	// 标题
	private String  title;
	//文章内容
	private String  content;
	
	private Integer commentCnt;

	//图片url
	private String  picture;// 
	
	//频道id
	private Integer channelId;
	
	private String chnName;
	
	//分类id
	private Integer categoryId;
	
	private String catName;

	//用户的id
	private Integer userId;
	
	private String username;
	
	private String tags;
	
	
	//点击量
	private Integer hits;
	//是否热门
	private Integer hot;
	
	//文章的状态   审核通过  未通过  待审
	private Integer status;
	
	//文章被删除
	private Integer deleted;
	
	//发表的日期
	private Date created;
	//修改的日期
	private Date updated;
	
	//摘要
	private String remark;
	
	public Integer getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}
	public String getChnName() {
		return chnName;
	}
	public void setChnName(String chnName) {
		this.chnName = chnName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Article(Integer id, String title, String content,
			Integer commentCnt, String picture, Integer channelId,
			String chnName, Integer categoryId, String catName, Integer userId,
			String username, String tags, Integer hits, Integer hot,
			Integer status, Integer deleted, Date created, Date updated,
			String remark) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.commentCnt = commentCnt;
		this.picture = picture;
		this.channelId = channelId;
		this.chnName = chnName;
		this.categoryId = categoryId;
		this.catName = catName;
		this.userId = userId;
		this.username = username;
		this.tags = tags;
		this.hits = hits;
		this.hot = hot;
		this.status = status;
		this.deleted = deleted;
		this.created = created;
		this.updated = updated;
		this.remark = remark;
	}
	public Article() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", picture=" + picture
				+ ", channelId=" + channelId + ", categoryId=" + categoryId + ", userId=" + userId + ", hits=" + hits
				+ ", hot=" + hot + ", status=" + status + ", deleted=" + deleted + ", created=" + created + ", updated="
				+ updated + "]";
	}
	
	

	
}

