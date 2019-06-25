package com.itheima.service;

import com.itheima.pojo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author: 离殇丶
 * @date: Created in 2019/6/24 14:46
 * @description:
 * @version: 1.0
 */

public interface UserPermissionService {

    List<Map<String, Object>> getMenuList(String username);
}
