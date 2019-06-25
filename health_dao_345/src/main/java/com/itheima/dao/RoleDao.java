package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface RoleDao {

    public Set<Role> findRoleListByUserId(Integer userId);

    List<Role> findAll();

    void add(Role role);

    void insertRoleAndPermisson(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    void insertRoleAndMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    Page<Role> findByCondition(String queryString);

    Role findById(Integer id);

    List<Integer> findPermissionIdsById(Integer id);

    List<Integer> findMenuIdsById(Integer id);

    void edit(Role role);

    void deleteRoleAndPermisson(Integer id);

    void deleteRoleAndMenu(Integer id);

    int findUserAndRoleCountById(Integer id);

    void deletePermissonAssociation(Integer id);

    void deleteMenuAssociation(Integer id);

    void delById(Integer id);
}
