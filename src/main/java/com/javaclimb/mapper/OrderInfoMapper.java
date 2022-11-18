package com.javaclimb.mapper;

import com.javaclimb.entity.OrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

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

    /**
     * 删除订单
     * @param id
     */
    void deleteById(Long id);

    /**
     * 统计总交易额
     */
    @Select("select sum(totalPrice) from order_info where state = '已收货'")
    Double totalPrice();

    /**
     * 分类总销售额
     */
    @Select("SELECT SUM(a.count*b.price) as `price`,c.`name` FROM order_goods_rel as a\n" +
            "LEFT JOIN goods_info as b on a.goodsId=b.id\n" +
            "LEFT JOIN type_info as c on c.id=b.typeId\n" +
            "GROUP BY b.typeId")
    List<Map<String, Object>> getTypePrice();

    /**
     * 分类总销量
     */
    @Select("SELECT SUM(a.count) as `count`,c.`name` FROM order_goods_rel as a\n" +
            "LEFT JOIN goods_info as b on a.goodsId=b.id\n" +
            "LEFT JOIN type_info as c on c.id=b.typeId\n" +
            "GROUP BY b.typeId")
    List<Map<String, Object>> getTypeCount();
}