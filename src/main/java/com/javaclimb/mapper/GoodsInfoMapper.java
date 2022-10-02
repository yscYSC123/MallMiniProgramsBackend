package com.javaclimb.mapper;

import com.javaclimb.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 商品相关的Mapper
 */
@Repository
public interface GoodsInfoMapper extends Mapper<GoodsInfo> {
    /**
     * 根据商品名称模糊查询，或者根据id查询一个商品
     * @param name
     * @return
     */
    List<GoodsInfo> findByName(@Param("name")String name,@Param("id")Long id);
}