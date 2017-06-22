package com.handsome.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.handsome.dao.provider.OrderDynaSqlProvider;
import com.handsome.domain.Order;
import static com.handsome.util.common.HrmConstants.ORDERTABLE;

/**   
 * @Description: OrderMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @date 2016年7月11日 上午10:53:32 
 * @version V1.0   
 */
public interface OrderDao {

	// 动态查询
	@SelectProvider(type=OrderDynaSqlProvider.class,method="selectWhitParam")
	List<Order> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=OrderDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+ORDERTABLE+" ")
	List<Order> selectAllOrder();
	
	@Select("select * from "+ORDERTABLE+" where id = #{id}")
	Order selectById(int id);

	// 根据id删除部门
	@Delete(" delete from "+ORDERTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入部门
	@SelectProvider(type=OrderDynaSqlProvider.class,method="insertOrder")
	void save(Order order);
	
	// 动态修改用户
	@SelectProvider(type=OrderDynaSqlProvider.class,method="updateOrder")
	void update(Order order);
}
