package com.chexiang.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chexiang.entity.User;

import com.chexiang.service.LoginService;

@Controller
@RequestMapping("/user")
public class LoginController {
	@Resource
	private LoginService loginService;

	/*
	 * @RequestMapping("/show") public String login(HttpServletRequest request,
	 * Model model) { String username = request.getParameter("username"); String
	 * password = request.getParameter("password"); if (username == "") { return
	 * "login"; } else if (password == "") { return "login"; } String
	 * passwordfromdb = this.loginService.setUserName(username); User user =
	 * this.loginService.getUserByName(username);
	 * 
	 * if (password.equals(passwordfromdb)) { model.addAttribute("user", user);
	 * return "main"; } else { return "login"; }
	 * 
	 * }
	 */
	@RequestMapping("/show")
	public String login(@RequestParam(value = "username") String username,
			String password, Model model) {
		String passwordfromdb = this.loginService.setUserName(username);
		User user = this.loginService.getUserByName(username);
		System.out.println(user.getName() + "      " + password);
		if (passwordfromdb.equals(password)) {
			model.addAttribute("user", user);
			return "main";
		} else {
			return "login";
		}
	}

}