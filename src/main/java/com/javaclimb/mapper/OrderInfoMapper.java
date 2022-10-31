package com.javaclimb.mapper;

import com.javaclimb.entity.OrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderInfoMapper extends Mapper<OrderInfo> {

    /**
     * 根据订单id查询一条订单数据
     * @param orderId
     * @return
     */
    @Select("select * from order_info where orderId = #{orderId}")
    List<OrderInfo> findByOrderId(@Param("orderId") String orderId);

    /**
     * 根据终端用户id和状态查询订单列表
     */
    List<OrderInfo> findByEndUserId(@Param("userId")Long userId,@Param("state")String state);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Select("select * from order_info where id = #{id}")
    OrderInfo findById(@Param("id")Long id);

    /**
     * 更新订单状态
     * @param id
     * @param state
     */
    @Update("update order_info set state = #{state} where id = #{id}")
    void updateState(@Param("id") Long id, @Param("state") String state);

    void deleteById(Long id);
}