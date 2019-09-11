package cn.jbit.smbms.controller;

import java.io.File;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cn.jbit.smbms.Util.CommentData;
import cn.jbit.smbms.pojo.Provider;
import cn.jbit.smbms.pojo.User;
import cn.jbit.smbms.service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	@Resource
	private ProviderService providerService;
	
	@RequestMapping(value="/list")
	public String list(Model model,String proCode,String proName,Integer pageIndex) {
		model.addAttribute("pager", providerService.listByPage(proCode, proName, pageIndex,CommentData.PAGE_SIZE));
		model.addAttribute("proCode",proCode);
		model.addAttribute("proName", proName);
		return "provider/list";
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Provider provider) {
		return "provider/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Model model,User user,HttpSession session,Provider provider,MultipartFile licPic) {
		if(!licPic.isEmpty()) {
			String path=session.getServletContext().getRealPath("/statics/upload");
			String oldFileName=licPic.getOriginalFilename();
			String ext=FilenameUtils.getExtension(oldFileName);
			if(licPic.getSize()>1024*1024*2) {
				model.addAttribute("fillError", "上传文件不能大于2MB!");
				return "provider/add";
			}
			if(!"jpg".equalsIgnoreCase(ext)&&!"png".equalsIgnoreCase(ext)&&!"gif".equalsIgnoreCase(ext)) {
				model.addAttribute("fillError", "上传文件知道是图片类型(jpg,png,gif)!");
				return "provider/add";
			}
			String fileName="lic"+System.currentTimeMillis()+(new Random().nextInt(10000))+"."+ext;
			File file=new File(path,fileName);
			try {
				licPic.transferTo(file);
				provider.setLicPicPath(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
		User u=(User)session.getAttribute("userLogin");
		provider.setCreatedBy(u.getId());
		provider.setCreattionDate(new Date());
		if(providerService.add(provider)>0) {
			return "redirect:/provider/list";
		}else {
			return "provider/add";
		}
		
	}
	@RequestMapping(value="/view/{id}")
	public String view(Model model,@PathVariable("id")Integer id) {
		model.addAttribute("provider", providerService.get(id));
		return "provider/view";
	}
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id")Integer id) {
		providerService.remove(id);
		return "redirect:/provider/list";
	}
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
}
