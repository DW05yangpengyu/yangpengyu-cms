package com.yangpengyu.cms.mapper;

import java.util.List;

import com.yangpengyu.cms.entity.Channel;



/**
*@author 作者:杨鹏羽
*@version 创建时间：2019年9月21日 上午10:26:59
*频道Mapper
*/
public interface ChannelMapper {

	/**
	 * 查询所有频道
	 * @return
	 */
	List<Channel> getChannels();
}
