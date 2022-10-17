package com.javaclimb.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.javaclimb.entity.CartInfo;
import com.javaclimb.mapper.CartInfoMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CartInfoService {

    @Resource
    private CartInfoMapper cartInfoMapper;

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
}
