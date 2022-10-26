package com.javaclimb.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.common.ResultCode;
import com.javaclimb.entity.UserInfo;
import com.javaclimb.exception.CustomException;
import com.javaclimb.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关的Service
 */
@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 登录
     */
    public UserInfo login(String name,String password){
        //判断用户是否存在
        List<UserInfo> list = userInfoMapper.findByName(name);
        if (CollectionUtil.isEmpty(list)){
            throw new CustomException(ResultCode.USER_NO_EXIST_ERROR);
        }
        //判断密码是否正确
        if (!SecureUtil.md5(password).equals(list.get(0).getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return list.get(0);
    }

    /**
     * 重置密码
     */
    public UserInfo resetPassword(String name){
        List<UserInfo> list = userInfoMapper.findByName(name);
        if (CollectionUtil.isEmpty(list)){
            throw new CustomException(ResultCode.USER_NO_EXIST_ERROR);
        }
        list.get(0).setPassword(SecureUtil.md5("0000"));
        userInfoMapper.updateByPrimaryKeySelective(list.get(0));
        return list.get(0);
    }

    /**
     * 分页查询用户列表
     * 按用户名查询
     */
    public PageInfo<UserInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> list = userInfoMapper.findByName(name);
        return PageInfo.of(list);
    }

    /**
     * 新增用户
     */
    public UserInfo add(UserInfo userInfo){
        //判断用户是否已经存在
        List<UserInfo> list = userInfoMapper.findByName(userInfo.getName());
        if (CollectionUtil.isNotEmpty(list)){
            return list.get(0);
        }

        if (StrUtil.isBlank(userInfo.getPassword())){
            //默认密码0000
            userInfo.setPassword(SecureUtil.md5("0000"));
        }else {
            userInfo.setPassword(SecureUtil.md5(userInfo.getPassword()));
        }

        //设置新增用户都是买家
        userInfo.setLevel(3);
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }

    /**
     * 修改用户信息
     */
    public void update(UserInfo userInfo){
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 根据用户id获取用户
     */
    public UserInfo findById(Long id){
        return userInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id删除用户
     */
    public void delete(Long id){
        userInfoMapper.deleteByPrimaryKey(id);
    }

}
