package cn.jbit.smbms.controller;

import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import cn.jbit.smbms.Util.CommentData;
import cn.jbit.smbms.pojo.User;
import cn.jbit.smbms.service.RoleService;
import cn.jbit.smbms.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="roleService")
	private RoleService roleService;
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	/**
	 * 分页查询
	 * @param model
	 * @param userName
	 * @param userRole
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,String userName, Integer userRole, Integer pageIndex, Integer pageSize) {
		model.addAttribute("roleList",roleService.list());
		model.addAttribute("pager",userService.listByPage(userName, userRole, pageIndex, CommentData.PAGE_SIZE));
		model.addAttribute("userName",userName);
		model.addAttribute("userRole", userRole);
		return "user/list";
	}
	/**
	 * 查询roleList给页面
	 * @param user
	 * @param medel
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute("user") User user,Model model) {
		model.addAttribute("roleList", roleService.list());
		return "user/add";
	}
	/**
	 * 增加用户
	 * @param model
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,User user,HttpSession session,MultipartFile idFile) {
		model.addAttribute("roleList", roleService.list());
		if(!idFile.isEmpty()) {
			//上传文件路径
			String path=session.getServletContext().getRealPath("/statics/upload");
			//上传文件的原始名称
			String oldFileName=idFile.getOriginalFilename();
			//获取上传文件的后缀名
			String ext=FilenameUtils.getExtension(oldFileName);
			if(idFile.getSize()>1024*1024*2) {
				model.addAttribute("fileError", "文件大小不能超过2MB!");
				return "user/add";
			}
			if(!"jpg".equalsIgnoreCase(ext)&&!"png".equalsIgnoreCase(ext)&&!"gif".equalsIgnoreCase(ext)) {
				model.addAttribute("fileError", "文件只能是图片(jpg、png、gif)!");
				return "user/add";
			}
			String fileName="idPic"+System.currentTimeMillis()+(new Random().nextInt(10000))+"."+ext;
			File file=new File(path, fileName);
			try {
				idFile.transferTo(file);
				user.setIdPicPath(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("fileError", "系统上传文件最大只能支持20MB!");
				return "user/add";

			}
		}
		user.setCreatedBy(((User)session.getAttribute(CommentData.LOGIN_SESSION)).getId());
		user.setCreationDate(new Date());
		if(userService.add(user)) {
			return "redirect:/user/list";
		}else
		return "user/add";
	}
	@RequestMapping(value="/modify/{id}",method=RequestMethod.GET)
	public String modify(@ModelAttribute("user") User user,Model model,@PathVariable("id") Integer id) {
		model.addAttribute("user",userService.get(id));
		model.addAttribute("roleList", roleService.list());
		return "user/modify";
	}
	@RequestMapping("/view/{id}")
	public String view(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("user",userService.get(id));
		return "user/view";
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id")Integer id) {
		userService.remove(id);
		return "redirect:/user/list";
	}
	@ResponseBody
	@RequestMapping("/userCodeExists")
	public boolean  userCodeExists(String userCode) {
		User user=userService.listByUserCode(userCode);
			return user!=null?true:false;
	
	}
	/*@ResponseBody
	@RequestMapping("/userInfo")
	public Object userInfo(Integer id) {
		User user =userService.get(id);
		return JSON.toJSONString(user);
	}*/
	
}
