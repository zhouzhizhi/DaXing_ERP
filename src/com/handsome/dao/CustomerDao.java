package com.handsome.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.handsome.dao.provider.CustomerDynaSqlProvider;
import com.handsome.domain.Customer;
import static com.handsome.util.common.HrmConstants.CUSTOMERTABLE;

/**   
 * @Description: CustomerMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @date 2016年7月11日 上午10:53:32 
 * @version V1.0   
 */
public interface CustomerDao {

	// 动态查询
	@SelectProvider(type=CustomerDynaSqlProvider.class,method="selectWhitParam")
	List<Customer> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CustomerDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+CUSTOMERTABLE+" ")
	List<Customer> selectAllCustomer();
	
	@Select("select * from "+CUSTOMERTABLE+" where ID = #{id}")
	Customer selectById(int id);

	// 根据id删除部门
	@Delete(" delete from "+CUSTOMERTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入部门
	@SelectProvider(type=CustomerDynaSqlProvider.class,method="insertCustomer")
	void save(Customer customer);
	
	// 动态修改用户
	@SelectProvider(type=CustomerDynaSqlProvider.class,method="updateCustomer")
	void update(Customer customer);
}
