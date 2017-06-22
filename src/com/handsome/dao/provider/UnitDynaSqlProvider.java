package com.handsome.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.handsome.domain.Unit;

import static com.handsome.util.common.HrmConstants.UNITTABLE;;

/**   
 * @Description: 部门动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public class UnitDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(UNITTABLE);
				if(params.get("unit") != null){
					Unit unit = (Unit) params.get("unit");
					if(unit.getName() != null && !unit.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{unit.name},'%') ");
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
				FROM(UNITTABLE);
				if(params.get("unit") != null){
					Unit unit = (Unit) params.get("unit");
					if(unit.getName() != null && !unit.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{unit.name},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertUnit(Unit unit){
		
		return new SQL(){
			{
				INSERT_INTO(UNITTABLE);
				if(unit.getName() != null && !unit.getName().equals("")){
					VALUES("name", "#{name}");
				}
				if(unit.getRemark() != null && !unit.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateUnit(Unit unit){
		
		return new SQL(){
			{
				UPDATE(UNITTABLE);
				if(unit.getName() != null){
					SET(" name = #{name} ");
				}
				if(unit.getRemark() != null){
					SET(" remark = #{remark} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}


}
