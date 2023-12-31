package com.ruoyi.activiti.security.service.impl;

import com.ruoyi.activiti.security.service.ISysPostService;
import com.ruoyi.activiti.security.mapper.SysPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 岗位信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysPostServiceImpl implements ISysPostService {
    @Autowired
    private SysPostMapper postMapper;

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public Set<String> selectPostCodeByUserId(Long userId) {
        return postMapper.selectPostCodeByUserId(userId);
    }


}
