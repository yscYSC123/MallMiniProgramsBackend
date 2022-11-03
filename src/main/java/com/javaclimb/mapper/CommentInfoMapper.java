package com.javaclimb.mapper;

import com.javaclimb.entity.CommentInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentInfoMapper extends Mapper<CommentInfo> {

    /**
     * 根据内容模糊查询评价列表
     */
    List<CommentInfo> findByContent(@Param("name") String name);

    /**
     * 根据商品id获取评论列表
     */
    List<CommentInfo> findByGoodsId(@Param("goodsId") Long goodsId);
}