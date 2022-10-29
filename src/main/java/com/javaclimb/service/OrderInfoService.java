package com.javaclimb.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.common.ResultCode;
import com.javaclimb.entity.GoodsInfo;
import com.javaclimb.entity.OrderGoodsRel;
import com.javaclimb.entity.OrderInfo;
import com.javaclimb.entity.UserInfo;
import com.javaclimb.exception.CustomException;
import com.javaclimb.mapper.OrderGoodsRelMapper;
import com.javaclimb.mapper.OrderInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderInfoService {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private CartInfoService cartInfoService;

    @Resource
    private OrderGoodsRelMapper orderGoodsRelMapper;

    /**
     * 下单
     */
    @Transactional
    public OrderInfo add(OrderInfo orderInfo){

        /**
         * 1.生成基本的订单信息，用户信息
         *  订单id：用户id+当前时间+4位流水号
         */
        Long userId = orderInfo.getUserid();
        String orderId = userId + DateUtil.format(new Date(),"yyyyMMddHHmm") + RandomUtil.randomNumbers(4);
        orderInfo.setOrderid(orderId);
        //用户相关
        UserInfo userInfo = userInfoService.findById(userId);
        orderInfo.setLinkaddress(userInfo.getAddress());
        orderInfo.setLinkman(userInfo.getNickname());
        orderInfo.setLinkphone(userInfo.getPhone());

        /**
         * 2.保存订单表
         */
        orderInfo.setCreatetime(DateUtil.formatDateTime(new Date()));
        orderInfoMapper.insertSelective(orderInfo);
        List<OrderInfo> orderInfoList = orderInfoMapper.findByOrderId(orderId);

        /**
         * 3.查询订单里面的商品列表
         */
        List<GoodsInfo> goodsList = orderInfo.getGoodsList();
        for (GoodsInfo orderGoodsVO : goodsList){
            Long goodsId = orderGoodsVO.getId();
            //商品信息
            GoodsInfo goodsDetail = goodsInfoService.findById(goodsId);
            if (goodsDetail == null){
                continue;
            }
            Integer orderCount = orderGoodsVO.getCount() == null?0:orderGoodsVO.getCount();     //想买多少
            Integer goodsCount = goodsDetail.getCount() == null?0:goodsDetail.getCount();       //有多少库存

            /**
             * 4.扣库存
             */
            if (orderCount>goodsCount){
                throw new CustomException(ResultCode.ORDER_PAY_ERROR);
            }
            goodsDetail.setCount(goodsCount - orderCount);

            /**
             * 5.增加销量
             */
            int sales = goodsDetail.getSales() == null?0:goodsDetail.getSales();
            goodsDetail.setSales(sales+orderCount);
            goodsInfoService.update(goodsDetail);

            /**
             * 6.商品订单关联表
             */
            OrderGoodsRel orderGoodsRel = new OrderGoodsRel();
            orderGoodsRel.setOrderid(orderInfoList.get(0).getId());
            orderGoodsRel.setGoodsid(goodsId);
            orderGoodsRel.setCount(orderCount);
            orderGoodsRelMapper.insertSelective(orderGoodsRel);
        }

        /**
         * 7.清空购物车
         */
        cartInfoService.empty(userId);

        return orderInfo;
    }

    /**
     * 根据终端用户id和状态查询订单列表
     */
    public PageInfo<OrderInfo> findFrontPages(Long userId,String state,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<OrderInfo> orderInfos;
        if (userId == null){
            orderInfos = new ArrayList<>();
        }else{
            orderInfos = orderInfoMapper.findByEndUserId(userId,state);
        }
        for (OrderInfo orderInfo:orderInfos){
            packOrder(orderInfo);
        }
        return PageInfo.of(orderInfos);
    }

    /**
     * 包装订单的用户和商品信息
     */
    private void packOrder(OrderInfo orderInfo){
        //用户信息
        orderInfo.setUserInfo(userInfoService.findById(orderInfo.getUserid()));
        //商品信息
        Long orderId = orderInfo.getId();
        List<OrderGoodsRel> rels =  orderGoodsRelMapper.findByOrderid(orderId);
        List<GoodsInfo> goodsInfoList = new ArrayList<>();
        for (OrderGoodsRel rel : rels){
            GoodsInfo goodsInfo = goodsInfoService.findById(rel.getGoodsid());
            if (goodsInfo != null){
                //这里的库存是用户加入的商品数量，不是总库存
                goodsInfo.setCount(rel.getCount());
                goodsInfoList.add(goodsInfo);
            }
        }
        orderInfo.setGoodsList(goodsInfoList);
    }
}
