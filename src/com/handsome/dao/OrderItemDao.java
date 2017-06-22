package com.handsome.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.handsome.dao.provider.OrderItemDynaSqlProvider;
import com.handsome.domain.OrderItem;
import static com.handsome.util.common.HrmConstants.ORDERITEMTABLE;

/**   
 * @Description: OrderItemMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @date 2016年7月11日 上午10:53:32 
 * @version V1.0   
 */
public interface OrderItemDao {

	// 动态查询
	@SelectProvider(type=OrderItemDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="SPECIFICATION",property="specification"),
		@Result(column="REMARK",property="remark"),
		@Result(column="NUMBER",property="number"),
		@Result(column="OPRICE",property="oprice"),
		@Result(column="SPRICE",property="sprice"),
		@Result(column="OID",property="order",
			one=@One(select="com.handsome.dao.OrderDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="UID",property="unit",
			one=@One(select="com.handsome.dao.UnitDao.selectById",
	fetchType=FetchType.EAGER)),
		@Result(column="PID",property="product",
			one=@One(select="com.handsome.dao.ProductDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<OrderItem> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=OrderItemDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+ORDERITEMTABLE+" ")
	List<OrderItem> selectAllOrderItem();
	
	@Select("select * from " + ORDERITEMTABLE + " where oid = #{oid}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="SPECIFICATION",property="specification"),
		@Result(column="REMARK",property="remark"),
		@Result(column="NUMBER",property="number"),
		@Result(column="OPRICE",property="oprice"),
		@Result(column="SPRICE",property="sprice"),
		@Result(column="OID",property="order",
			one=@One(select="com.handsome.dao.OrderDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="UID",property="unit",
			one=@One(select="com.handsome.dao.UnitDao.selectById",
	fetchType=FetchType.EAGER)),
		@Result(column="PID",property="product",
			one=@One(select="com.handsome.dao.ProductDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<OrderItem> selectOrderItemByOid(Integer oid);
	
	//根据id查找订单项
	@Select("select * from "+ORDERITEMTABLE+" where ID = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="SPECIFICATION",property="specification"),
		@Result(column="REMARK",property="remark"),
		@Result(column="NUMBER",property="number"),
		@Result(column="OPRICE",property="oprice"),
		@Result(column="SPRICE",property="sprice"),
		@Result(column="OID",property="order",
			one=@One(select="com.handsome.dao.OrderDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="UID",property="unit",
			one=@One(select="com.handsome.dao.UnitDao.selectById",
	fetchType=FetchType.EAGER)),
		@Result(column="PID",property="product",
			one=@One(select="com.handsome.dao.ProductDao.selectById",
		fetchType=FetchType.EAGER))
	})
	OrderItem selectById(int id);

	// 根据id删除部门
	@Delete(" delete from "+ORDERITEMTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入部门
	@SelectProvider(type=OrderItemDynaSqlProvider.class,method="insertOrderItem")
	void save(OrderItem orderItem);
	
	// 动态修改用户
	@SelectProvider(type=OrderItemDynaSqlProvider.class,method="updateOrderItem")
	void update(OrderItem orderItem);
}
