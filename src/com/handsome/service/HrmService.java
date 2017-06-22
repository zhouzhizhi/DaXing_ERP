package com.handsome.service;

import java.util.List;

import com.handsome.domain.Customer;
import com.handsome.domain.Dept;
import com.handsome.domain.Document;
import com.handsome.domain.Employee;
import com.handsome.domain.Job;
import com.handsome.domain.Notice;
import com.handsome.domain.Order;
import com.handsome.domain.OrderItem;
import com.handsome.domain.Product;
import com.handsome.domain.Unit;
import com.handsome.domain.User;
import com.handsome.util.tag.PageModel;

/**   
 * @Description: 人事管理服务层接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public interface HrmService {

	/***********************************用户服务接口*************************************/
	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname,String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	List<User> findUser(User user,PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	/**
	 * 修改用户
	 * @param User 用户对象
	 * */
	void modifyUser(User user);
	
	/**
	 * 添加用户
	 * @param User 用户对象
	 * */
	void addUser(User user);
	
	/***********************************员工服务接口*************************************/
	
	/**
	 * 获得所有员工
	 * @param employee 查询条件
	 * @param pageModel 分页对象
	 * @return Dept对象的List集合
	 * */
	List<Employee> findEmployee(Employee employee,PageModel pageModel);
	
	/**
	 * 根据id删除员工
	 * @param id
	 * */
	void removeEmployeeById(Integer id);
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return 员工对象
	 * */
	Employee findEmployeeById(Integer id);
	
	/**
	 * 添加员工
	 * @param employee 员工对象
	 * */
	void addEmployee(Employee employee);
	
	/**
	 * 修改员工
	 * @param employee 员工对象
	 * */
	void modifyEmployee(Employee employee);
	
	/***********************************部门服务接口*************************************/
	
	/**
	 * 获得所有部门，分页查询
	 * @return Dept对象的List集合
	 * */
	List<Dept> findDept(Dept dept,PageModel pageModel);
	
	/**
	 * 获得所有部门
	 * @return Dept对象的List集合
	 * */
	List<Dept> findAllDept();
	
	/**
	 * 根据id删除部门
	 * @param id
	 * */
	public void removeDeptById(Integer id);
	
	/**
	 * 添加部门
	 * @param dept 部门对象
	 * */
	void addDept(Dept dept);
	
	/**
	 * 根据id查询部门
	 * @param id
	 * @return 部门对象
	 * */
	Dept findDeptById(Integer id);
	
	/**
	 * 修改部门
	 * @param dept 部门对象
	 * */
	void modifyDept(Dept dept);
	
	/***********************************职位服务接口*************************************/
	
	/**
	 * 获得所有职位
	 * @return Job对象的List集合
	 * */
	List<Job> findAllJob();
	
	List<Job> findJob(Job job,PageModel pageModel);
	
	/**
	 * 根据id删除职位
	 * @param id
	 * */
	public void removeJobById(Integer id);
	
	/**
	 * 添加职位
	 * @param Job 部门对象
	 * */
	void addJob(Job job);
	
	/**
	 * 根据id查询职位
	 * @param id
	 * @return 职位对象
	 * */
	Job findJobById(Integer id);
	
	/**
	 * 修改职位
	 * @param dept 部门对象
	 * */
	void modifyJob(Job job);
	
	/***********************************公告服务接口*************************************/
	
	/**
	 * 获得所有公告
	 * @return Notice对象的List集合
	 * */
	List<Notice> findNotice(Notice notice,PageModel pageModel);
	
	/**
	 * 根据id查询公告
	 * @param id
	 * @return 公告对象
	 * */
	Notice findNoticeById(Integer id);
	
	/**
	 * 根据id删除公告
	 * @param id
	 * */
	public void removeNoticeById(Integer id);
	
	/**
	 * 添加公告
	 * @param Notice 公告对象
	 * */
	void addNotice(Notice notice);
	
	/**
	 * 修改公告
	 * @param Notice 公告对象
	 * */
	void modifyNotice(Notice notice);
	
	/***********************************文件服务接口*************************************/
	
	/**
	 * 获得所有文档
	 * @return Document对象的List集合
	 * */
	List<Document> findDocument(Document document,PageModel pageModel);
	
	/**
	 * 添加文档
	 * @param Document 文件对象
	 * */
	void addDocument(Document document);
	
	/**
	 * 根据id查询文档
	 * @param id
	 * @return 文档对象
	 * */
	Document findDocumentById(Integer id);
	
	/**
	 * 根据id删除文档
	 * @param id
	 * */
	public void removeDocumentById(Integer id);
	
	/**
	 * 修改文档
	 * @param Document 公告对象
	 * */
	void modifyDocument(Document document);
	
	/***********************************产品服务接口*************************************/
	
	/**
	 * 获得所有产品
	 * @return Product对象的List集合
	 * */
	List<Product> findAllProduct();
	
	List<Product> findProduct(Product product,PageModel pageModel);
	
	/**
	 * 根据id删除产品
	 * @param id
	 * */
	public void removeProductById(Integer id);
	
	/**
	 * 添加产品
	 * @param Product 产品对象
	 * */
	void addProduct(Product product);
	
	/**
	 * 根据id查询产品
	 * @param id
	 * @return 产品对象
	 * */
	Product findProductById(Integer id);
	
	/**
	 * 修改产品
	 * @param product 产品对象
	 * */
	void modifyProduct(Product product);
	
	/***********************************单位服务接口*************************************/
	
	/**
	 * 获得所有单位
	 * @return Unit对象的List集合
	 * */
	List<Unit> findAllUnit();
	
	List<Unit> findUnit(Unit unit,PageModel pageModel);
	
	/**
	 * 根据id删除单位
	 * @param id
	 * */
	public void removeUnitById(Integer id);
	
	/**
	 * 添加单位
	 * @param Unit 单位对象
	 * */
	void addUnit(Unit unit);
	
	/**
	 * 根据id查询单位
	 * @param id
	 * @return 单位对象
	 * */
	Unit findUnitById(Integer id);
	
	/**
	 * 修改单位
	 * @param unit 单位对象
	 * */
	void modifyUnit(Unit unit);
	
	/***********************************客户服务接口*************************************/
	
	/**
	 * 获得所有客户
	 * @return Customer对象的List集合
	 * */
	List<Customer> findAllCustomer();
	
	List<Customer> findCustomer(Customer customer,PageModel pageModel);
	
	/**
	 * 根据id删除客户
	 * @param id
	 * */
	public void removeCustomerById(Integer id);
	
	/**
	 * 添加客户
	 * @param Customer 客户对象
	 * */
	void addCustomer(Customer customer);
	
	/**
	 * 根据id查询客户
	 * @param id
	 * @return 客户对象
	 * */
	Customer findCustomerById(Integer id);
	
	/**
	 * 修改客户
	 * @param customer 客户对象
	 * */
	void modifyCustomer(Customer customer);
	
	/***********************************订单服务接口*************************************/
	
	/**
	 * 获得所有订单
	 * @return Order对象的List集合
	 * */
	List<Order> findAllOrder();
	
	List<Order> findOrder(Order order,PageModel pageModel);
	
	/**
	 * 根据id删除订单
	 * @param id
	 * */
	public void removeOrderById(Integer id);
	
	/**
	 * 添加订单
	 * @param Order 订单对象
	 * */
	void addOrder(Order order);
	
	/**
	 * 根据id查询订单
	 * @param id
	 * @return 订单对象
	 * */
	Order findOrderById(Integer id);
	
	/**
	 * 修改订单
	 * @param Order 订单对象
	 * */
	void modifyOrder(Order order);
	
	/***********************************订单项服务接口*************************************/
	/**
	 * 获得所有订单项
	 * @return OrderItem对象的List集合
	 * */
	List<OrderItem> findAllOrderItem();
	
	List<OrderItem> findOrderItem(OrderItem orderItem,PageModel pageModel);
	
	/**
	 * 根据id删除订单项
	 * @param id
	 * */
	public void removeOrderItemById(Integer id);
	
	/**
	 * 添加订单项
	 * @param OrderItem 订单项对象
	 * */
	void addOrderItem(OrderItem orderItem);
	
	/**
	 * 根据id查询订单项
	 * @param id
	 * @return 订单项对象
	 * */
	OrderItem findOrderItemById(Integer id);
	
	/**
	 * 修改订单项
	 * @param Order 订单项对象
	 * */
	void modifyOrderItem(OrderItem orderItem);
	
	/**
	 * 根据订单号查找订单项
	 * */
	List<OrderItem> findOrderItemByOid(Integer id);
	
}
