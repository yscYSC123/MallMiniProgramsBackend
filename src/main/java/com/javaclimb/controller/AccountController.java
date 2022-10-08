package com.javaclimb.controller;

import cn.hutool.core.util.StrUtil;
import com.javaclimb.common.Common;
import com.javaclimb.common.Result;
import com.javaclimb.common.ResultCode;
import com.javaclimb.entity.UserInfo;
import com.javaclimb.exception.CustomException;
import com.javaclimb.service.UserInfoService;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录，退出相关的控制器
 */
@RestController
public class AccountController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<UserInfo> login(@RequestBody UserInfo userInfo, HttpServletRequest request){
        if (StrUtil.isBlank(userInfo.getName())||StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        //从数据库查询账号密码是否正确
        UserInfo login = userInfoService.login(userInfo.getName(), userInfo.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(Common.USER_INFO,login);
        session.setMaxInactiveInterval(60*60*24);
        return Result.success(login);
    }

    /**
     * 重置密码 0000
     */
    @PostMapping("/resetPassword")
    public Result<UserInfo> restPassword(@RequestBody UserInfo userInfo){
        return Result.success(userInfoService.resetPassword(userInfo.getName()));
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request){
        request.getSession().setAttribute(Common.USER_INFO,null);
        return Result.success();
    }

    /**
     * 小程序端用户注册
     */
    @PostMapping("/register")
    public Result<UserInfo> register(@RequestBody UserInfo userInfo,HttpServletRequest request){
        if (StrUtil.isBlank(userInfo.getName()) || StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        UserInfo register = userInfoService.add(userInfo);
        HttpSession session = request.getSession();
        session.setAttribute(Common.USER_INFO,register);
        session.setMaxInactiveInterval(60*60*24);
        return Result.success(register);
    }

    /**
     * 判断用户是否已登录
     */
    @GetMapping("/auth")
    public Result getAuth(HttpServletRequest request){
        Object user = request.getSession().getAttribute(Common.USER_INFO);
        if (user == null){
            return Result.error("401","未登录");
        }
        return Result.success(user);
    }

}
