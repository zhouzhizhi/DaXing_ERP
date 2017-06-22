package com.handsome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.handsome.domain.Product;
import com.handsome.service.HrmService;
import com.handsome.util.tag.PageModel;

@Controller
public class ProductController {

	/**
	 * 自动注入ProductService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理产品列表请求
	 * */
	@RequestMapping("/product/selectProduct")
	public String selectProduct(Model model, Integer pageIndex, @ModelAttribute Product product) {
		
		PageModel pageModel = new PageModel();
		
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Product> products = hrmService.findProduct(product, pageModel);
		model.addAttribute("products", products);
		model.addAttribute("pageModel", pageModel);
		
		return "product/product";
	}
	
	/**
	 * 处理删除产品请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/product/removeProduct")
	public ModelAndView removeProduct(String ids, ModelAndView mv) {
		//分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			//根据id删除部门
			hrmService.removeProductById(Integer.parseInt(id));
		}
		//设置客户端跳转到查询请求
		mv.setViewName("redirect:/product/selectProduct");
		//返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Product  要添加的产品对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/product/addProduct")
	public ModelAndView addProduct(String flag, @ModelAttribute Product product, ModelAndView mv) {
		if (flag.equals("1")) {
			//设置跳转到添加页面
			mv.setViewName("product/showAddProduct");
		} else {
			//执行添加操作
			hrmService.addProduct(product);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/product/selectProduct");
		}
		//返回
		return mv;
	}
	
	/**
	 * 处理修改产品请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Product 要修改部门的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/product/updateProduct")
	public ModelAndView updateProduct(String flag, @ModelAttribute Product product, ModelAndView mv) {
		if (flag.equals("1")) {
			//根据id查询部门
			Product target = hrmService.findProductById(product.getId());
			//设置model数据
			mv.addObject("product", target);
			//设置跳转到修改页面
			mv.setViewName("product/showUpdateProduct");
		} else {
			//执行修改操作
			hrmService.modifyProduct(product);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/product/selectProduct");
		}
		
		//返回
		return mv;
	}
	
}
