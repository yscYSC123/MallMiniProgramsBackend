package com.javaclimb.service;

import com.javaclimb.entity.NxSystemFileInfo;
import com.javaclimb.mapper.NxSystemFileInfoMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * 文件相关的Service
 */
@Service
public class NxSystemFileInfoService {

    @Resource
    private NxSystemFileInfoMapper nxSystemFileInfoMapper;

    /**
     * 新增文件
     */
    public NxSystemFileInfo add(NxSystemFileInfo nxSystemFileInfo){
        nxSystemFileInfoMapper.insertSelective(nxSystemFileInfo);
        return nxSystemFileInfo;
    }

    /**
     * 修改文件信息
     */
    public void update(NxSystemFileInfo userInfo){
        nxSystemFileInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 根据id删除文件
     */
    public void delete(Long id){
        nxSystemFileInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id获取文件
     */
    public NxSystemFileInfo findById(Long id){
        return nxSystemFileInfoMapper.selectByPrimaryKey(id);
    }

}