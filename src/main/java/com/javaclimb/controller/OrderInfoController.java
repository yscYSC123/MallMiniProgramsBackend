package com.javaclimb.controller;

import com.javaclimb.common.Result;
import com.javaclimb.common.ResultCode;
import com.javaclimb.entity.GoodsInfo;
import com.javaclimb.entity.OrderInfo;
import com.javaclimb.exception.CustomException;
import com.javaclimb.service.OrderInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 下单
     */
    @PostMapping
    public Result<OrderInfo> add(@RequestBody OrderInfo orderInfo){
        Long userId = orderInfo.getUserid();
        List<GoodsInfo> goodsList = orderInfo.getGoodsList();
        if (userId == null || goodsList == null || goodsList.size() == 0){
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        orderInfo.setState("待付款");
        return Result.success(orderInfoService.add(orderInfo));
    }

}
