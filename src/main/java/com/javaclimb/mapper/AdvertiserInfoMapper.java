package com.javaclimb.mapper;

import com.javaclimb.entity.AdvertiserInfo;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公告相关的Mapper
 */
@Repository
public interface AdvertiserInfoMapper extends Mapper<AdvertiserInfo> {
    //根据公告标题模糊查询
    List<AdvertiserInfo> findByName(@Param("name")String name);
}