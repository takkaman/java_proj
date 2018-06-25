package com.adam.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adam.domain.User;
import com.adam.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index.html")
	public String loginPage() {
//		System.out.println("Here");
		return "login";
	}
	
	@RequestMapping(value = "/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
		boolean isVaildUser = userService.hasMatchUser(loginCommand.getUsername(), loginCommand.getPassword());
		if(!isVaildUser){
			return new ModelAndView("login", "error", "用户名或密码错误");
		}else{
			User user = userService.findUserByUsername(loginCommand.getUsername());
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		}
	}
}
