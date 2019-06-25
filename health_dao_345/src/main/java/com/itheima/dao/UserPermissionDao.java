package com.itheima.dao;

import com.itheima.pojo.Menu;


import java.util.List;

/**
 * @author: 离殇丶
 * @date: Created in 2019/6/24 14:46
 * @description:
 * @version: 1.0
 */

public interface UserPermissionDao {
    List<Menu> getMenuList(String username);
}
