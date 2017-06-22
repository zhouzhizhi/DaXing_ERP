package com.handsome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.handsome.domain.Unit;
import com.handsome.service.HrmService;
import com.handsome.util.tag.PageModel;

@Controller
public class UnitController {

	/**
	 * 自动注入UnitService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理产品列表请求
	 * */
	@RequestMapping("/unit/selectUnit")
	public String selectUnit(Model model, Integer pageIndex, @ModelAttribute Unit unit) {
		
		PageModel pageModel = new PageModel();
		
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Unit> units = hrmService.findUnit(unit, pageModel);
		model.addAttribute("units", units);
		model.addAttribute("pageModel", pageModel);
		
		return "unit/unit";
	}
	
	/**
	 * 处理删除产品请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/unit/removeUnit")
	public ModelAndView removeUnit(String ids, ModelAndView mv) {
		//分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			//根据id删除部门
			hrmService.removeUnitById(Integer.parseInt(id));
		}
		//设置客户端跳转到查询请求
		mv.setViewName("redirect:/unit/selectUnit");
		//返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Unit  要添加的产品对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/unit/addUnit")
	public ModelAndView addUnit(String flag, @ModelAttribute Unit unit, ModelAndView mv) {
		if (flag.equals("1")) {
			//设置跳转到添加页面
			mv.setViewName("unit/showAddUnit");
		} else {
			//执行添加操作
			hrmService.addUnit(unit);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/unit/selectUnit");
		}
		//返回
		return mv;
	}
	
	/**
	 * 处理修改产品请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Unit 要修改部门的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/unit/updateUnit")
	public ModelAndView updateUnit(String flag, @ModelAttribute Unit unit, ModelAndView mv) {
		if (flag.equals("1")) {
			//根据id查询部门
			Unit target = hrmService.findUnitById(unit.getId());
			//设置model数据
			mv.addObject("unit", target);
			//设置跳转到修改页面
			mv.setViewName("unit/showUpdateUnit");
		} else {
			//执行修改操作
			hrmService.modifyUnit(unit);
			//设置客户端跳转到查询请求
			mv.setViewName("redirect:/unit/selectUnit");
		}
		
		//返回
		return mv;
	}
	
}
