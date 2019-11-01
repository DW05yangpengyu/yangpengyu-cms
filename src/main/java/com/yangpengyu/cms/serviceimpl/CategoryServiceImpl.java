package com.yangpengyu.cms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangpengyu.cms.entity.Category;
import com.yangpengyu.cms.mapper.CategoryMapper;
import com.yangpengyu.cms.service.CategoryService;

/**
*@author 杨鹏羽
*@version 创建时间：2019年9月21日 下午4:25:08
*分类业务层
*/
@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	CategoryMapper categoryMapper; 
	
	@Override
	public List<Category> getCategoryByChId(Integer cid) {
		return categoryMapper.getCategoryByChId(cid);
	}

}
