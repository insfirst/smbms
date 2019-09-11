package cn.jbit.smbms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.jbit.smbms.Util.CommentData;
import cn.jbit.smbms.pojo.Bill;
import cn.jbit.smbms.pojo.User;
import cn.jbit.smbms.service.BillService;
import cn.jbit.smbms.service.ProviderService;

@Controller
@RequestMapping("/bill")
public class BillController {
	@Resource(name="billService")
	private BillService billService;
	@Resource(name="providerService")
	private ProviderService providerService;
	@RequestMapping("/list")
	public String list(String productName,Integer providerId,Model model,Integer pageIndex,Integer pageSize,Integer isPayment) {
		model.addAttribute("providerList", providerService.listAll());
		model.addAttribute("pager",billService.listByPage(productName, providerId, pageIndex, CommentData.PAGE_SIZE,isPayment));
		model.addAttribute("productName",productName);
		model.addAttribute("providerId", providerId);
		model.addAttribute("isPayment", isPayment);
		return "bill/list";
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Bill bill,Model model) {
		model.addAttribute("providerList",providerService.listAll());
		return "bill/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Bill bill,Model model,HttpSession session) {
		bill.setCreatedBy(((User)session.getAttribute("userLogin")).getId());
		bill.setCreationDate(new Date());
		if(billService.insert(bill)>0) {
			return "redirect:/bill/list";
		}
		return "bill/add";
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable(value="id")Integer id,Model model) {
		model.addAttribute("bill",billService.select(id));
		return "bill/view";
	}
	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id")Integer id) {
		billService.delete(id);
		return "redirect:/bill/list";
	}
	
	
	public BillService getBillService() {
		return billService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
}
