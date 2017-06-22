package com.handsome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.handsome.domain.Customer;
import com.handsome.service.HrmService;
import com.handsome.util.tag.PageModel;

@Controller
public class CustomerController {

	/**
	 * 自动注入CustomerService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理客户列表请求
	 * */
	@RequestMapping("/customer/selectCustomer")
	public String selectCustomer(Model model, Integer pageIndex, @ModelAttribute Customer customer) {
		
		PageModel pageModel = new PageModel();
		
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Customer> customers = hrmService.findCustomer(customer, pageModel);
		model.addAttribute("customers", customers);
		model.addAttribute("pageModel", pageModel);
		
		return "customer/customer";
	}
	
	/**
	 * 处理删除客户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/customer/removeCustomer")
	public ModelAndView removeCustomer(String ids, ModelAndView mv) {
		//分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			//根据id删除部门
			hrmService.removeCustomerById(Integer.parseInt(id));
		}
		//设置客户端跳转到查询请求
		mv.setViewName("redirect:/customer/selectCustomer");
		//返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Customer  要添加的客户对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/customer/addCustomer")
	public ModelAndView addCustomer(String flag, @ModelAttribute Customer customer, ModelAndView mv) {
		if (flag.equals("1")) {
			//设置跳转到添加页面
			mv.setViewName("customer/showAddCustomer");
		} else {
			//执行添加操作
			hrmService.addCustomer(customer);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/customer/selectCustomer");
		}
		//返回
		return mv;
	}
	
	/**
	 * 处理修改客户请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Customer 要修改客户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/customer/updateCustomer")
	public ModelAndView updateCustomer(String flag, @ModelAttribute Customer customer, ModelAndView mv) {
		if (flag.equals("1")) {
			//根据id查询部门
			Customer target = hrmService.findCustomerById(customer.getId());
			//设置model数据
			mv.addObject("customer", target);
			//设置跳转到修改页面
			mv.setViewName("customer/showUpdateCustomer");
		} else {
			//执行修改操作
			hrmService.modifyCustomer(customer);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/customer/selectCustomer");
		}
		
		//返回
		return mv;
	}
	
}
