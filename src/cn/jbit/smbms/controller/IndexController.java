package cn.jbit.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jbit.smbms.Util.CommentData;
import cn.jbit.smbms.pojo.User;
import cn.jbit.smbms.service.UserService;


@Controller
public class IndexController {
	@Resource(name="userService")
	private UserService userService;
	@RequestMapping(value= {"index",""})
	public String index() {
		return "login";
	}
	@RequestMapping("/login")
	public String login(HttpSession session,String userCode,String userPassword,Model model) {
		User user=userService.login(userCode, userPassword);
		if(null!=user) {
			session.setAttribute(CommentData.LOGIN_SESSION, user);
			return"redirect:/welcome";
		}else {
			model.addAttribute("error", "用户或密码错误！");
			return "index";
		}
	}
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
