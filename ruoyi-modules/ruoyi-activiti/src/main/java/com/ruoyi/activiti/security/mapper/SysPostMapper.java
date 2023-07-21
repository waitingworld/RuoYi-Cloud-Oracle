package com.ruoyi.activiti.security.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 岗位信息 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysPostMapper {
    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    public Set<String> selectPostCodeByUserId(Long userId);


}
