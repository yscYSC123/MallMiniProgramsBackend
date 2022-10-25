package com.javaclimb.controller;

import com.javaclimb.common.Result;
import com.javaclimb.entity.CartInfo;
import com.javaclimb.entity.GoodsInfo;
import com.javaclimb.service.CartInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 查询某用户的购物车（不分页）
     */
    @GetMapping
    public Result<List<GoodsInfo>> findAll(@RequestParam Long userId){
        return Result.success(cartInfoService.findAll(userId));
    }

    /**
     * 删除某用户购物车的某个商品
     */
    @DeleteMapping("/goods/{userId}/{goodsId}")
    public Result deleteGoods(@PathVariable Long userId,@PathVariable Long goodsId){
        cartInfoService.deleteGoods(userId, goodsId);
        return Result.success();
    }
}
