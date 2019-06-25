package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Permission;

import java.util.List;
import java.util.Set;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface PermissionDao {

    public Set<Permission> findPermissionsByRoleId(Integer roleId);

    void add(Permission permission);

    Page<Permission> queryPage(String queryString);

    void edit(Permission permission);

    Permission findById(Integer id);

    int findByCheckItemId(Integer id);

    void delById(Integer id);

    List<Permission> findAll();
}
