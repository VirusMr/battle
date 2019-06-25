package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.SysUser;

import java.util.List;

public interface SysUserService {
    void add(SysUser sysUser, Integer[] roleIds);

    PageResult queryPage(QueryPageBean queryPageBean);

    SysUser findById(Integer id);

    List<Integer> findRoleIdsById(Integer id);

    void edit(SysUser sysUser, Integer[] roleIds);

    void delById(Integer id);
}
