package com.handsome.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import com.handsome.dao.provider.ProductDynaSqlProvider;
import com.handsome.domain.Product;
import static com.handsome.util.common.HrmConstants.PRODUCTTABLE;

/**   
 * @Description: ProductMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @date 2016年7月11日 上午10:53:32 
 * @version V1.0   
 */
public interface ProductDao {

	// 动态查询
	@SelectProvider(type=ProductDynaSqlProvider.class,method="selectWhitParam")
	List<Product> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=ProductDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+PRODUCTTABLE+" ")
	List<Product> selectAllProduct();
	
	@Select("select * from "+PRODUCTTABLE+" where ID = #{id}")
	Product selectById(int id);

	// 根据id删除部门
	@Delete(" delete from "+PRODUCTTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入部门
	@SelectProvider(type=ProductDynaSqlProvider.class,method="insertProduct")
	void save(Product product);
	
	// 动态修改用户
	@SelectProvider(type=ProductDynaSqlProvider.class,method="updateProduct")
	void update(Product product);
}
