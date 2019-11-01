package com.yangpengyu.cms.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yangpengyu.cms.entity.Article4Vote;
import com.yangpengyu.cms.entity.User;
import com.yangpengyu.cms.entity.VoteStatic;
import com.yangpengyu.cms.service.Article4VoteService;
import com.yangpengyu.cms.utils.ConstantFinal;


/**
*@author 杨鹏羽
*@version 
*投票控制层
*/
@RequestMapping("vote")
@Controller
public class VoteController {

	@Autowired
	Article4VoteService avService;

	/**
	 * 查询投票列表
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		List<Article4Vote> list = avService.list();
		request.setAttribute("list", list);
		return "my/vote/list";
		
	}
	
	/**
	 * 去添加投票页面
	 * @param request
	 * @return
	 */
	@GetMapping("push")
	public String push(HttpServletRequest request) {
		return "my/vote/add";
		
	}
	
	/**
	 * 添加投票
	 * @param request
	 * @param av
	 * @return
	 */
	@PostMapping("push")
	@ResponseBody
	public boolean  push(HttpServletRequest request,Article4Vote av) {
		return avService.publish(av)>0;
		
	}
	
	/**
	 * 获取票数信息
	 * @param request
	 * @param arId  投票的id
	 * @return
	 */
	@GetMapping("getVote")
	public String getVote(HttpServletRequest request,int arId) {
		Article4Vote av = avService.findById(arId);
		request.setAttribute("voteArticle", av);
		Gson gson = new Gson();
		System.out.println(av.getContent());
		LinkedHashMap<String,String> map = gson.fromJson(av.getContent(), LinkedHashMap.class);
		
		
		LinkedHashMap<String,VoteStatic> lmap = new LinkedHashMap<String,VoteStatic>();
		Set<Entry<String, String>> entrySet = map.entrySet();
		
		List<VoteStatic> voteStatics = avService.getVoteStatics(arId);
		// 計算總共有多少人投票
		int totalNum = 0;
		for (VoteStatic voteStatic : voteStatics) {
			totalNum+=voteStatic.getVoteNum();
		}
		
		// 生成新的map集合存放統計數據
		for (Entry<String, String> entry : entrySet) {
			VoteStatic voteStatic = new VoteStatic();
			voteStatic.setOptionKey(entry.getKey());
			voteStatic.setOptionTitle(entry.getValue());
			voteStatic.setVoteNumTotal(totalNum);
			lmap.put(entry.getKey(), voteStatic);
			
		}
		
		
		
		//获取统计的每一项结果
		for (VoteStatic voteStatic : voteStatics) {
			VoteStatic showStatic = lmap.get(voteStatic.getOptionKey());
			showStatic.setVoteNum(voteStatic.getVoteNum());
		}
		
		request.setAttribute("lmap", lmap);
		
		return "my/vote/detail";
	}
	
	
	/**
	 * 进行投票
	 * @param request
	 * @param articleId
	 * @param option
	 * @return
	 */
	@PostMapping("vote")
	@ResponseBody
	public Boolean push(HttpServletRequest request,Integer articleId,Character option) {
		User loginUser = (User)request.getSession().getAttribute(ConstantFinal.USER_SESSION_KEY);
		if(loginUser==null)
			return false;
		
		return avService.vote(loginUser.getId(), articleId, option)>0;
		
	}

	
}
