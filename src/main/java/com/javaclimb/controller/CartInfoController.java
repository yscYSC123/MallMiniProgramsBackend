package com.javaclimb.controller;

import com.javaclimb.common.Result;
import com.javaclimb.entity.CartInfo;
import com.javaclimb.service.CartInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/cartInfo")
public class CartInfoController {

    @Resource
    private CartInfoService cartInfoService;

    /**
     * 添加购物车
     */
    @PostMapping
    public Result<CartInfo> add(@RequestBody CartInfo cartInfo){
        return Result.success(cartInfoService.add(cartInfo));
    }
}
