package com.bruis.api.product.service.dao;

import com.bruis.common.portal.model.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LuoHaiYang
 */
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();

    List<Product> selectByNameAndProductId(@Param("productName")String productName,@Param("productId") Integer productId);

    List<Product> selectByNameAndCategoryIds(@Param("productName")String productName, @Param("idList")List<Integer> idList, @Param("categoryIdList")List<Integer> categoryIdList);

    List<Product> selectByNameAndCategoryIdsLimit(@Param("productName")String productName,@Param("categoryIdList") List<Integer> categoryIdList);
}
