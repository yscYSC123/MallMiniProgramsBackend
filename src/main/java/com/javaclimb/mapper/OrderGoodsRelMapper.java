package com.javaclimb.mapper;

import com.javaclimb.entity.OrderGoodsRel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderGoodsRelMapper extends Mapper<OrderGoodsRel> {
    /**
     * 根据订单id获取商品列表
     */
    List<OrderGoodsRel> findByOrderid(Long orderId);
}