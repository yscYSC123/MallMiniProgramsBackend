package com.javaclimb.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.common.Common;
import com.javaclimb.entity.CartInfo;
import com.javaclimb.entity.GoodsInfo;
import com.javaclimb.entity.UserInfo;
import com.javaclimb.exception.CustomException;
import com.javaclimb.mapper.CartInfoMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartInfoService {

    @Resource
    private CartInfoMapper cartInfoMapper;
    @Resource
    private GoodsInfoService goodsInfoService;

    /**
     * 加入购物车
     */
    public CartInfo add(CartInfo detailInfo){
        Long userId = detailInfo.getUserid();
        Long goodsId = detailInfo.getGoodsid();
        //先查询购物车是否存在该商品，没有就添加，有就更新
        Example example = new Example(CartInfo.class);
        example.createCriteria().andEqualTo("userid",userId).andEqualTo("goodsid",goodsId);
        List<CartInfo> infos = cartInfoMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(infos)){
            //新增
            detailInfo.setCreatetime(DateUtil.formatDateTime(new Date()));
            cartInfoMapper.insertSelective(detailInfo);
        }else {
            //更新
            CartInfo cartInfo = infos.get(0);
            cartInfo.setCount(cartInfo.getCount() + detailInfo.getCount());
            cartInfoMapper.updateByPrimaryKeySelective(cartInfo);
        }
        return detailInfo;
    }

    /**
     * 根据用户id获取购物车里的商品列表
     * @param userId
     * @return
     */
    public List<GoodsInfo> findAll(Long userId){
        List<CartInfo> cartInfoList = cartInfoMapper.findCartByUserId(userId);
        List<GoodsInfo> goodsList = new ArrayList<>();
        for (CartInfo cartInfo:cartInfoList){
            long goodsId = cartInfo.getGoodsid();
            GoodsInfo goodsInfo = goodsInfoService.findById(goodsId);
            if (goodsInfo != null){
                //这里的count是用户加入购物车商品的数量
                goodsInfo.setCount(cartInfo.getCount());
                //这里的id是购物车里商品的关系id
                goodsInfo.setId(cartInfo.getGoodsid());
                goodsList.add(goodsInfo);
            }
        }
        return goodsList;
    }

    /**
     * 删除某用户购物车的某个商品
     * @return
     */
    public void deleteGoods(Long userId,Long goodsId){
        cartInfoMapper.deleteGoods(userId, goodsId);
    }

    /**
     * 清空购物车
     * @param userId
     */
    public void empty(Long userId) {
        cartInfoMapper.deleteByUserId(userId);
    }

    /**
     * 分页查询购物车列表
     */
    public PageInfo<CartInfo> findPageDetails(Integer pageNum, Integer pageSize, HttpServletRequest request){
        UserInfo user = (UserInfo) request.getSession().getAttribute(Common.USER_INFO);
        if (user == null){
            throw new CustomException("1001","session已失效，请重新登录");
        }
        Integer level = user.getLevel();
        PageHelper.startPage(pageNum,pageSize);
        List<CartInfo> list;
        if (level == 1){
            list = cartInfoMapper.findAll();
        }else {
            list = cartInfoMapper.findCartByUserId(user.getId());
        }
        return PageInfo.of(list);
    }

    /**
     * 根据购物车id删除购物车
     */
    public void delete(Long id) {
        cartInfoMapper.deleteByPrimaryKey(id);
    }
}
