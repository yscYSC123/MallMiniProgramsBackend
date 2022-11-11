package com.javaclimb.controller;

import com.javaclimb.common.Result;
import com.javaclimb.service.CommentInfoService;
import com.javaclimb.service.OrderInfoService;
import com.javaclimb.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台统计
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private CommentInfoService commentInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 统计各种总数
     * @return
     */
    @GetMapping("/getTotal")
    public Result<Map<String,Object>> getTotal(){
        Map<String, Object> map = new HashMap<>();
        //获取用户总数
        map.put("totalUser",userInfoService.count());
        //获取评论总数
        map.put("totalComment",commentInfoService.count());
        //总交易额
        map.put("totalPrice",orderInfoService.totalPrice());
        //总销量
        map.put("totalShopping",orderInfoService.totalShopping());

        return Result.success(map);
    }

}
