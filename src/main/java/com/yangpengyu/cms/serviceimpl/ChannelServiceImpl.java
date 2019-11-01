package com.yangpengyu.cms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangpengyu.cms.entity.Channel;
import com.yangpengyu.cms.mapper.ChannelMapper;
import com.yangpengyu.cms.service.ChannelService;

/**
 * @author 杨鹏羽
 * @version 创建时间：2019年9月21日 下午4:26:07 
 * 频道业务层
 */
@Service
public class ChannelServiceImpl implements ChannelService {
	
	@Autowired
	ChannelMapper channelMapper;

	@Override
	public List<Channel> getChannels() {
		return channelMapper.getChannels();
	}

}
