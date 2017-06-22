package com.handsome.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.handsome.domain.Dept;
import com.handsome.domain.Job;
import com.handsome.domain.Order;
import com.handsome.domain.OrderItem;
import com.handsome.domain.Product;
import com.handsome.domain.Unit;
import com.handsome.service.HrmService;
import com.handsome.util.tag.PageModel;

/**
 * 处理用户请求控制器
 * */
@Controller
public class OrderItemController {
	/**
	 * 自动注入OrderItemService
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
	@RequestMapping(value="/orderItem/selectOrderItem")
	public String selectOrderItem(Integer pageIndex, Integer oid, Integer pid, Integer uid, @ModelAttribute OrderItem orderItem, Model model) {
		
		//模糊查询时判断是否有关联对象传递 如果有 创建并封装关联对象
		this.genericAssociation(oid, pid, uid, orderItem);
		
		//创建分页对象
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Order> orders = hrmService.findAllOrder();
		List<Product> products = hrmService.findAllProduct();
		List<Unit> units = hrmService.findAllUnit();
		List<OrderItem> orderItems = hrmService.findOrderItem(orderItem, pageModel);
		
		model.addAttribute("orderItems", orderItems);
		model.addAttribute("pageModel", pageModel);
		
		model.addAttribute("products", products);
		model.addAttribute("units", units);
		model.addAttribute("orders", orders);
		
		return "orderItem/orderItem";
	}
	
	//根据oid查询订单项
	@RequestMapping(value="/orderItem/selectOrderItemByOid")
	public String selectOrderItemByoid(Integer oid,Integer pid, Integer uid, @ModelAttribute OrderItem orderItem, Model model) {
		
		//模糊查询时判断是否有关联对象传递 如果有 创建并封装关联对象
		this.genericAssociation(oid, pid, uid, orderItem);
		
		List<OrderItem> orderItems = hrmService.findOrderItemByOid(oid);
		List<Order> orders = hrmService.findAllOrder();
		List<Product> products = hrmService.findAllProduct();
		List<Unit> units = hrmService.findAllUnit();
		
		model.addAttribute("orderItems", orderItems);
		
		model.addAttribute("products", products);
		model.addAttribute("units", units);
		model.addAttribute("orders", orders);
		
		return "orderItem/orderItem";
	}
	
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/orderItem/removeOrderItem")
	public ModelAndView removeOrderItem(String ids,ModelAndView mv) {
		// 分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			//根据id删除员工
			hrmService.removeOrderItemById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/orderItem/selectOrderItem");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理修改用户请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param OrderItem OrderItem  要修改用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/orderItem/updateOrderItem")
	public ModelAndView updateOrderItem(String flag, @ModelAttribute OrderItem orderItem, ModelAndView mv) {
		if (flag.equals("1")) {
			// 根据id查询用户
			OrderItem target = hrmService.findOrderItemById(orderItem.getId());
			// 设置Model数据
			mv.addObject("orderItem", target);
			// 返回修改员工页面
			mv.setViewName("orderItem/showUpdateOrderItem");
		} else {
			// 执行修改操作
			hrmService.modifyOrderItem(orderItem);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/orderItem/selectOrderItem");
		}
		//返回
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param OrderItem OrderItem  要添加用户的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/orderItem/addOrderItem")
	public ModelAndView addOrderItem(String flag, @ModelAttribute OrderItem orderItem, ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到添加页面
			mv.setViewName("orderItem/showAddOrderItem");
		} else {
			// 执行添加操作
			hrmService.addOrderItem(orderItem);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/orderItem/selectOrderItem");
		}
		
		//返回
		return mv;
	}
	
	/**
	 * 由于部门和职位在Employee中是对象关联映射，
	 * 所以不能直接接收参数，需要创建Job对象和Dept对象
	 * */
	private void genericAssociation(Integer oid, Integer pid, Integer uid, OrderItem orderItem) {
		if (oid != null) {
			Order order = new Order();
			order.setId(oid);
			orderItem.setOrder(order);
		} 
		if (pid != null) {
			Product product = new Product();
			product.setId(pid);
			orderItem.setProduct(product);
		}
		if (uid != null) {
			Unit unit = new Unit();
			unit.setId(uid);
			orderItem.setUnit(unit);
		}
	}
	
}
