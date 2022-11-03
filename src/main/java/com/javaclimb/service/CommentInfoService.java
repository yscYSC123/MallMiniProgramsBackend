package com.javaclimb.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javaclimb.entity.CommentInfo;
import com.javaclimb.mapper.CommentInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品评价服务类
 */
@Service
public class CommentInfoService {

    @Resource
    private CommentInfoMapper commentInfoMapper;

    /**
     * 新增评价
     */
    public CommentInfo add(CommentInfo commentInfo){
        commentInfo.setCreatetime(DateUtil.formatDateTime(new Date()));
        commentInfoMapper.insertSelective(commentInfo);
        return commentInfo;
    }

    /**
     * 根据内容模糊查询评价列表
     */
    public PageInfo<CommentInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<CommentInfo> list = commentInfoMapper.findByContent(name);
        return PageInfo.of(list);
    }

    /**
     * 根据id删除一条评论
     */
    public void delete(Long id) {
        commentInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据商品id获取评论列表
     */
    public List<CommentInfo> findByGoodsId(Long goodsId){
        return commentInfoMapper.findByGoodsId(goodsId);
    }
}
