package com.ruoyi.activiti.security.service.impl;


import com.ruoyi.activiti.security.service.ISysUserService;
import com.ruoyi.activiti.security.domain.SysUser;
import com.ruoyi.activiti.security.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public List<String> selectUserNameByPostCodeAndDeptId(String postCode, Long deptId) {
        return userMapper.selectUserNameByPostCodeAndDeptId(postCode, deptId);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
}
