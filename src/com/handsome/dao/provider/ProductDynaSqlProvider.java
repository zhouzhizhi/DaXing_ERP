package com.handsome.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.handsome.domain.Product;

import static com.handsome.util.common.HrmConstants.PRODUCTTABLE;;

/**   
 * @Description: 部门动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public class ProductDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(PRODUCTTABLE);
				if(params.get("product") != null){
					Product product = (Product) params.get("product");
					if(product.getName() != null && !product.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{product.name},'%') ");
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
				FROM(PRODUCTTABLE);
				if(params.get("product") != null){
					Product product = (Product) params.get("product");
					if(product.getName() != null && !product.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{product.name},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertProduct(Product product){
		
		return new SQL(){
			{
				INSERT_INTO(PRODUCTTABLE);
				if(product.getName() != null && !product.getName().equals("")){
					VALUES("name", "#{name}");
				}
				if(product.getRemark() != null && !product.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateProduct(Product product){
		
		return new SQL(){
			{
				UPDATE(PRODUCTTABLE);
				if(product.getName() != null){
					SET(" name = #{name} ");
				}
				if(product.getRemark() != null){
					SET(" remark = #{remark} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}


}
