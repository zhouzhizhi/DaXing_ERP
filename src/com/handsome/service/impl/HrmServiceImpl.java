package com.handsome.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.handsome.dao.CustomerDao;
import com.handsome.dao.DeptDao;
import com.handsome.dao.DocumentDao;
import com.handsome.dao.EmployeeDao;
import com.handsome.dao.JobDao;
import com.handsome.dao.NoticeDao;
import com.handsome.dao.OrderDao;
import com.handsome.dao.OrderItemDao;
import com.handsome.dao.ProductDao;
import com.handsome.dao.UnitDao;
import com.handsome.dao.UserDao;
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
import com.handsome.service.HrmService;
import com.handsome.util.tag.PageModel;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private UserDao userDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UnitDao unitDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	
	/***********************************用户服务接口实现*************************************/
	
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}

	/**
	 * HrmServiceImpl接口findUserById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口findUser方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<User> users = userDao.selectByPage(params);
		
		return users;
	}

	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
	}
	
	/**
	 * HrmServiceImpl接口addUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);
	}

	/**
	 * HrmServiceImpl接口modifyUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addUser(User user) {
		userDao.save(user);
	}

	/***********************************员工服务接口实现*************************************/
	
	/**
	 * HrmService接口findEmployee方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("employee", employee);
		
		int recordCount = employeeDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<Employee> employees = employeeDao.selectByPage(params);
		
		return employees;
	}

	/**
	 * HrmService接口removeEmployeeById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);
	}

	/**
	 * HrmService接口findEmployeeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Employee findEmployeeById(Integer id) {
		return employeeDao.selectById(id);
	}

	/**
	 * HrmService接口addEmployee方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);
	}

	/**
	 * HrmService接口modifyEmployee方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyEmployee(Employee employee) {
		employeeDao.update(employee);
	}
	
	/***********************************部门服务接口实现*************************************/
	
	/**
	 * HrmServiceImpl接口findDept方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("dept", dept);
		int recordCount = deptDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<Dept> depts = deptDao.selectByPage(params);
		
		return depts;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		return deptDao.selectAllDept();
	}

	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);
	}

	/**
	 * HrmServiceImpl接口addDept方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addDept(Dept dept) {
		deptDao.save(dept);
	}

	/**
	 * HrmServiceImpl接口findDeptById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Dept findDeptById(Integer id) {
		return deptDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口modifyDept方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyDept(Dept dept) {
		deptDao.update(dept);
	}
	
	/***********************************职位服务接口实现*************************************/

	/**
	 * HrmService接口findAllJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findAllJob() {
		return jobDao.selectAllJob();
	}

	/**
	 * HrmService接口findJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("job", job);
		int recordCount = jobDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<Job> jobs = jobDao.selectByPage(params);
		
		return jobs;
	}

	/**
	 * HrmService接口removeJobById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeJobById(Integer id) {
		jobDao.deleteById(id);
	}

	/**
	 * HrmService接口addJob方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addJob(Job job) {
		jobDao.save(job);
	}

	/**
	 * HrmService接口findJobById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Job findJobById(Integer id) {
		return jobDao.selectById(id);
	}

	/**
	 * HrmService接口modifyJob方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyJob(Job job) {
		jobDao.update(job);
	}
	
	/***********************************公告服务接口实现*************************************/

	@Transactional(readOnly=true)
	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("notice", notice);
		int recordCount = noticeDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<Notice> notices = noticeDao.selectByPage(params);
		
		return notices;
	}

	/**
	 * HrmService接口findNoticeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Notice findNoticeById(Integer id) {
		return noticeDao.selectById(id);
	}

	/**
	 * HrmService接口removeNoticeById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeNoticeById(Integer id) {
		noticeDao.deleteById(id);
	}

	/**
	 * HrmService接口addNotice方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addNotice(Notice notice) {
		noticeDao.save(notice);
	}

	/**
	 * HrmService接口modifyNotice方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyNotice(Notice notice) {
		noticeDao.update(notice);
	}
	
	/***********************************文件服务接口实现*************************************/

	/**
	 * HrmService接口findDocument方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Document> findDocument(Document document, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("document", document);
		int recordCount = documentDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<Document> documents = documentDao.selectByPage(params);
		
		return documents;
	}

	/**
	 * HrmService接口addDocument方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addDocument(Document document) {
		documentDao.save(document);
	}

	/**
	 * HrmService接口findDocumentById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Document findDocumentById(Integer id) {
		return documentDao.selectById(id);
	}

	/**
	 * HrmService接口removeDocumentById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeDocumentById(Integer id) {
		documentDao.deleteById(id);
	}

	/**
	 * HrmService接口modifyDocument方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyDocument(Document document) {
		documentDao.update(document);
	}
	
	/***********************************产品服务接口实现*************************************/

	/**
	 * HrmServiceImpl接口findAllProduct方法实现
	 */
	@Transactional(readOnly=true)
	@Override
	public List<Product> findAllProduct() {
		return productDao.selectAllProduct();
	}

	@Transactional(readOnly=true)
	@Override
	public List<Product> findProduct(Product product, PageModel pageModel) {
		/**当前需要分页的总数据条数*/
		Map<String, Object> params = new HashMap<>();
		params.put("product", product);
		int recordCount = productDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据 查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<Product> products = productDao.selectByPage(params);
		
		return products;
	}

	/**
	 * HrmServiceImpl接口removeProductById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeProductById(Integer id) {
		productDao.deleteById(id);
	}

	/**
	 * HrmServiceImpl接口addProduct方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addProduct(Product product) {
		productDao.save(product);
	}

	/**
	 * HrmServiceImpl接口findProductById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Product findProductById(Integer id) {
		return productDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口modifyProduct方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyProduct(Product product) {
		productDao.update(product);
	}

	/***********************************单位服务接口实现*************************************/
	
	@Transactional(readOnly=true)
	@Override
	public List<Unit> findAllUnit() {
		return unitDao.selectAllUnit();
	}

	/**
	 * HrmServiceImpl接口findUnit方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Unit> findUnit(Unit unit, PageModel pageModel) {
		/** 当前需要分页的总数据条数 */
		Map<String, Object> params = new HashMap<>();
		params.put("unit", unit);
		int recordCount = unitDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据 查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<Unit> units = unitDao.selectByPage(params);
		
		return units;
	}

	/**
	 * HrmServiceImpl接口removeUnitById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeUnitById(Integer id) {
		unitDao.deleteById(id);
	}

	/**
	 * HrmServiceImpl接口addUnit方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addUnit(Unit unit) {
		unitDao.save(unit);
	}

	/**
	 * HrmServiceImpl接口findUnitById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Unit findUnitById(Integer id) {
		return unitDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口modifyUnit方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyUnit(Unit unit) {
		unitDao.update(unit);
	}

	/***********************************客户服务接口实现*************************************/
	
	@Transactional(readOnly=true)
	@Override
	public List<Customer> findAllCustomer() {
		return customerDao.selectAllCustomer();
	}

	/**
	 * HrmServiceImpl接口findCustomer方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Customer> findCustomer(Customer customer, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("customer", customer);
		int recordCount = customerDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<Customer> customers = customerDao.selectByPage(params);
		
		return customers;
	}

	/**
	 * HrmServiceImpl接口removeCustomerById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeCustomerById(Integer id) {
		customerDao.deleteById(id);
	}

	/**
	 * HrmServiceImpl接口addCustomer方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addCustomer(Customer customer) {
		customerDao.save(customer);
	}

	/**
	 * HrmServiceImpl接口findCustomerById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Customer findCustomerById(Integer id) {
		return customerDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口modifyCustomer方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyCustomer(Customer customer) {
		customerDao.update(customer);
	}

	/***********************************订单服务接口实现*************************************/
	
	@Transactional(readOnly=true)
	@Override
	public List<Order> findAllOrder() {
		return orderDao.selectAllOrder();
	}

	/**
	 * HrmServiceImpl接口findOrder方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Order> findOrder(Order order, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("order", order);
		int recordCount = orderDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<Order> orders = orderDao.selectByPage(params);
		
		return orders;
	}

	/**
	 * HrmServiceImpl接口removeOrderById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeOrderById(Integer id) {
		orderDao.deleteById(id);
	}

	/**
	 * HrmServiceImpl接口addOrder方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addOrder(Order order) {
		orderDao.save(order);
	}

	/**
	 * HrmServiceImpl接口findOrderById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Order findOrderById(Integer id) {
		return orderDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口modifyOrder方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyOrder(Order order) {
		orderDao.update(order);
	}

	/***********************************订单项服务接口实现*************************************/
	
	@Transactional(readOnly=true)
	@Override
	public List<OrderItem> findAllOrderItem() {
		return orderItemDao.selectAllOrderItem();
	}

	/**
	 * HrmServiceImpl接口findOrderItem方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<OrderItem> findOrderItem(OrderItem orderItem, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String, Object> params = new HashMap<>();
		params.put("orderItem", orderItem);
		int recordCount = orderItemDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		
		List<OrderItem> orderItems = orderItemDao.selectByPage(params);
		
		return orderItems;
	}

	/**
	 * HrmServiceImpl接口removeOrderItemById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeOrderItemById(Integer id) {
		orderItemDao.deleteById(id);
	}

	/**
	 * HrmServiceImpl接口addOrderItem方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addOrderItem(OrderItem orderItem) {
		orderItemDao.save(orderItem);
	}

	/**
	 * HrmServiceImpl接口findOrderItemById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public OrderItem findOrderItemById(Integer id) {
		return orderItemDao.selectById(id);
	}

	/**
	 * HrmServiceImpl接口modifyOrderItem方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyOrderItem(OrderItem orderItem) {
		orderItemDao.update(orderItem);
	}

	/**
	 * 根据订单号查找订单项
	 */
	@Override
	public List<OrderItem> findOrderItemByOid(Integer oid) {
		return orderItemDao.selectOrderItemByOid(oid);
	}
	
}
