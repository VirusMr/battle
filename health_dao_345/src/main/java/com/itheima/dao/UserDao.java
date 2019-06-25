package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
public interface UserDao {
    SysUser findByUsername(String username);

    void add(SysUser sysUser);

    void insert(@Param("userId")Integer userId, @Param("roleId")Integer roleId);

    Page<SysUser> findByCondition(String queryString);

    SysUser findById(Integer id);

    List<Integer> findRoleIdsById(Integer id);

    void edit(SysUser sysUser);

    void deleteAssociation(Integer id);

    void delById(Integer id);
}
