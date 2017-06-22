package com.handsome.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import com.handsome.domain.OrderItem;
import static com.handsome.util.common.HrmConstants.ORDERITEMTABLE;

/**   
 * @Description: 用户动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @date 2016年7月11日 上午11:19:23 
 * @version V1.0   
 */
public class OrderItemDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(ORDERITEMTABLE);
				if(params.get("orderItem") != null){
					OrderItem orderItem = (OrderItem)params.get("orderItem");
					if(orderItem.getOrder() != null && orderItem.getOrder().getId() != null && !orderItem.getOrder().getId().equals("")){
						WHERE("  oid LIKE CONCAT ('%',#{orderItem.order.id},'%') ");
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
				FROM(ORDERITEMTABLE);
				if(params.get("orderItem") != null){
					OrderItem orderItem = (OrderItem)params.get("orderItem");
					if(orderItem.getOrder() != null && orderItem.getOrder().getId() != null && !orderItem.getOrder().getId().equals("")){
						WHERE(" oid LIKE CONCAT ('%',#{orderItem.order.id},'%') ");
					}
				}
			}
		}.toString();
	}	
	
	// 动态插入
	public String insertOrderItem(OrderItem orderItem){
		
		return new SQL(){
			{
				INSERT_INTO(ORDERITEMTABLE);
				if(orderItem.getProduct().getId() != null && !orderItem.getProduct().getId().equals("")){
					VALUES("pid", "#{pid}");
				}
				if(orderItem.getOrder().getId() != null && !orderItem.getOrder().getId().equals("")){
					VALUES("oid", "#{oid}");
				}
				if(orderItem.getNumber() != null && !orderItem.getNumber().equals("")){
					VALUES("number", "#{number}");
				}
				if(orderItem.getOprice() != null && !orderItem.getOprice().equals("")){
					VALUES("oprice", "#{oprice}");
				}
				if(orderItem.getUnit().getId() != null && !orderItem.getUnit().getId().equals("")){
					VALUES("uid", "#{uid}");
				}
				if (orderItem.getSprice() != null && !orderItem.getSprice().equals("")) {
					VALUES("sprice","#{sprice}");
				}
				if (orderItem.getSpecification() != null && !orderItem.getSpecification().equals("")) {
					VALUES("specification","#{specification}");
				}
				if (orderItem.getRemark() != null && !orderItem.getRemark().equals("")) {
					VALUES("remark","#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
		public String updateOrderItem(OrderItem orderItem){
			
			return new SQL(){
				{
					UPDATE(ORDERITEMTABLE);
					if(orderItem.getProduct().getId() != null){
						SET(" pid = #{pid} ");
					}
					if(orderItem.getNumber() != null){
						SET(" number = #{number} ");
					}
					if(orderItem.getOprice() != null){
						SET(" oprice = #{oprice} ");
					}
					if(orderItem.getUnit().getId() != null){
						SET(" uid = #{uid} ");
					}
					if(orderItem.getSprice() != null){
						SET(" sprice = #{sprice} ");
					}
					if (orderItem.getSpecification() != null) {
						SET("specification = #{specification}");
					}
					if (orderItem.getRemark() != null) {
						SET("remark = #{remark}");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
