package com.ruoyi.activiti.security.mapper;


import com.ruoyi.activiti.security.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysUserMapper {

    public List<String> selectUserNameByPostCodeAndDeptId(String postCode, Long deptId);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);
}
