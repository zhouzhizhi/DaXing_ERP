package com.handsome.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.handsome.domain.Order;
import com.handsome.domain.OrderItem;
import com.handsome.service.HrmService;
import com.handsome.util.tag.PageModel;

/**
 * 处理用户请求控制器
 * */
@Controller
public class OrderController {
	/**
	 * 自动注入OrderService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */
	@RequestMapping(value="/order/selectOrder")
	public String selectOrder(Integer pageIndex, @ModelAttribute Order order, Model model) {
		
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Order> orders = hrmService.findOrder(order, pageModel);
		model.addAttribute("orders", orders);
		model.addAttribute("pageModel", pageModel);
		
//		for (Order u : orders) {
//			System.out.println(u);
//		}
		
		return "order/order";
	}
	
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/order/removeOrder")
	public ModelAndView removeOrder(String ids,ModelAndView mv) {
		// 分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			//根据id删除员工
			hrmService.removeOrderById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/order/selectOrder");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理修改用户请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Order Order  要修改用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/order/updateOrder")
	public ModelAndView updateOrder(String flag, @ModelAttribute Order order, ModelAndView mv) {
		if (flag.equals("1")) {
			// 根据id查询用户
			Order target = hrmService.findOrderById(order.getId());
			// 设置Model数据
			mv.addObject("order", target);
			// 返回修改员工页面
			mv.setViewName("order/showUpdateOrder");
		} else {
			// 执行修改操作
			hrmService.modifyOrder(order);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/order/selectOrder");
		}
		//返回
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Order Order  要添加用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/order/addOrder")
	public ModelAndView addOrder(String flag, @ModelAttribute Order order, ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到添加页面
			mv.setViewName("order/showAddOrder");
		} else {
			// 执行添加操作
			hrmService.addOrder(order);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/order/selectOrder");
		}
		
		//返回
		return mv;
	}
	
}
