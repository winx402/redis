package com.redis.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redis.domain.Mail.mail;
import com.redis.util.RabbitmqSender;


@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private RabbitmqSender rabbitmqSender;

	@SuppressWarnings("restriction")
	@RequestMapping("/rabbitTest")
	public String logout(Model model) {
		mail.Builder m = mail.newBuilder();
		m.setContent("haha");
		m.setGetter("4491074@qq.com");
		m.setTitle("hello");
		rabbitmqSender.sendDataToCrQueue(m.build().toByteArray());
		model.addAttribute("result", "结束");
		return "result";
	}
	
	@RequestMapping("/sessionTest")
	public void sessionTest(HttpSession session) {
		System.out.println(session.getAttribute("user"));
		session.setAttribute("user", "haha1");
		System.out.println(session.getId());
	}
}
