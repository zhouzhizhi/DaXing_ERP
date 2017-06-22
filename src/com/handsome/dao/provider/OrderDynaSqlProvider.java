package com.handsome.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.handsome.domain.Order;

import static com.handsome.util.common.HrmConstants.ORDERTABLE;;

/**   
 * @Description: 部门动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public class OrderDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(ORDERTABLE);
				if(params.get("order") != null){
					Order order = (Order) params.get("order");
					if(order.getNumber() != null && !order.getNumber().equals("")){
						WHERE("  number LIKE CONCAT ('%',#{order.number},'%') ");
					}
					if(order.getCid() != null && !order.getCid().equals("")){
						WHERE("  cid LIKE CONCAT ('%',#{order.cid},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(ORDERTABLE);
				if(params.get("order") != null){
					Order order = (Order) params.get("order");
					if(order.getNumber() != null && !order.getNumber().equals("")){
						WHERE("  number LIKE CONCAT ('%',#{order.number},'%') ");
					}
					if(order.getCid() != null && !order.getCid().equals("")){
						WHERE("  cid LIKE CONCAT ('%',#{order.cid},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertOrder(Order order){
		
		return new SQL(){
			{
				INSERT_INTO(ORDERTABLE);
				if (order.getCid() != null) {
					VALUES("cid", "#{cid}");
				}
				if(order.getNumber() != null && !order.getNumber().equals("")){
					VALUES("number", "#{number}");
				}
				if (order.getPrice() != null) {
					VALUES("price", "#{price}");
				}
				if(order.getRemark() != null && !order.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateOrder(Order order){
		
		return new SQL(){
			{
				UPDATE(ORDERTABLE);
				if (order.getCid() != null) {
					SET(" cid = #{cid} ");
				}
				if(order.getNumber() != null){
					SET(" number = #{number} ");
				}
				if (order.getPrice() != null) {
					SET(" price = #{price} ");
				}
				if(order.getRemark() != null){
					SET(" remark = #{remark} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}


}
