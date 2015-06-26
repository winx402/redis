package com.redis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.redis.domain.User;
import com.redis.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/addUser")
	public String addUser(User user,Model model) {
		userService.addUser(user);
		model.addAttribute("name", "添加成功");
		return "result";
	}

	@RequestMapping("/delUser")
	public String deleteUserById(Integer userId,Model model) {
		userService.deleteUserById(userId);
		model.addAttribute("name", "删除成功");
		return "result";
	}

	@RequestMapping("/getUser")
	public ModelAndView getUserById(Integer userId) {
		ModelAndView mav = new ModelAndView("result");
		mav.addObject("name", userService.getUserNameById(userId));
		return mav;
	}
	
	@RequestMapping("/userLogin")
	public String userLogin(@RequestParam("userId")Integer userId,HttpSession session) {
		session.setAttribute("user", userId);
		return "result";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println(session.getAttribute("user"));
		return "result";
	}
	
}
