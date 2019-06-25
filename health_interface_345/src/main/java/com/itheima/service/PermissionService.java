package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Permission;

import java.util.List;

public interface PermissionService {
    void addPermission(Permission permission);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void edit(Permission permission);

    Permission findById(Integer id);

    void delById(Integer id);

    List<Permission> findAll();
}
