package com.yangpengyu.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*@author yangpengyu
*@version 管理员控制层
*/
@Controller
@RequestMapping("admin")
public class AdminController {
	
	/**
	 * 跳转到管理员管理页面
	 * @return
	 */
	@GetMapping("index")
	public String index() {
		return "admin/index";
	}
	

}
