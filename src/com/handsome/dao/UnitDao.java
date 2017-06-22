package com.handsome.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.handsome.dao.provider.UnitDynaSqlProvider;
import com.handsome.domain.Unit;
import static com.handsome.util.common.HrmConstants.UNITTABLE;

/**   
 * @Description: UnitMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @date 2016年7月11日 上午10:53:32 
 * @version V1.0   
 */
public interface UnitDao {

	// 动态查询
	@SelectProvider(type=UnitDynaSqlProvider.class,method="selectWhitParam")
	List<Unit> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=UnitDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+UNITTABLE+" ")
	List<Unit> selectAllUnit();
	
	@Select("select * from "+UNITTABLE+" where ID = #{id}")
	Unit selectById(int id);

	// 根据id删除部门
	@Delete(" delete from "+UNITTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入部门
	@SelectProvider(type=UnitDynaSqlProvider.class,method="insertUnit")
	void save(Unit unit);
	
	// 动态修改用户
	@SelectProvider(type=UnitDynaSqlProvider.class,method="updateUnit")
	void update(Unit unit);
}
