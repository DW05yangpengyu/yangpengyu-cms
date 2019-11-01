package com.yangpengyu.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yangpengyu.cms.entity.Category;



/**
*@author 杨鹏羽
*@version 创建时间：2019年9月21日 上午10:25:13
*分类Mapper
*/
@Mapper
public interface CategoryMapper {

	/**
	 * 查询分类列表(通过频道id，用于二级联动)
	 * @param cid
	 * @return
	 */
	List<Category> getCategoryByChId(Integer cid);
	
}
