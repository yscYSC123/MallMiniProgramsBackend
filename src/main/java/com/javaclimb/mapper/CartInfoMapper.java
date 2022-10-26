package com.javaclimb.mapper;

import com.javaclimb.entity.CartInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CartInfoMapper extends Mapper<CartInfo> {

    /**
     * 根据用户获取购物车列表
     * @param userId
     * @return
     */
    List<CartInfo> findCartByUserId(Long userId);

    /**
     * 删除某用户购物车的某个商品
     * @return
     */
    @Delete("delete from cart_info where userId=#{userId} and goodsId=#{goodsId}")
    int deleteGoods(@Param("userId")Long userId,@Param("goodsId")Long goodsId);

    /**
     * 根据用户id清空购物车
     * @param userId
     */
    @Delete("delete from cart_info where userId=#{userId}")
    int deleteByUserId(@Param("userId")Long userId);
}