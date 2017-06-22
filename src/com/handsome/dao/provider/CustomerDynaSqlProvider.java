package com.handsome.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.handsome.domain.Customer;

import static com.handsome.util.common.HrmConstants.CUSTOMERTABLE;

/**   
 * @Description: 部门动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public class CustomerDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(CUSTOMERTABLE);
				if(params.get("customer") != null){
					Customer customer = (Customer) params.get("customer");
					if(customer.getName() != null && !customer.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{customer.name},'%') ");
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
				FROM(CUSTOMERTABLE);
				if(params.get("customer") != null){
					Customer customer = (Customer) params.get("customer");
					if(customer.getName() != null && !customer.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{customer.name},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertCustomer(Customer customer){
		
		return new SQL(){
			{
				INSERT_INTO(CUSTOMERTABLE);
				if(customer.getName() != null && !customer.getName().equals("")){
					VALUES("name", "#{name}");
				}
				if(customer.getRemark() != null && !customer.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateCustomer(Customer customer){
		
		return new SQL(){
			{
				UPDATE(CUSTOMERTABLE);
				if(customer.getName() != null){
					SET(" name = #{name} ");
				}
				if(customer.getRemark() != null){
					SET(" remark = #{remark} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}


}
