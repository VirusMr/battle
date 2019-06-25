package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.UserPermissionDao;
import com.itheima.pojo.Menu;
import com.itheima.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 离殇丶
 * @date: Created in 2019/6/24 16:51
 * @description:
 * @version: 1.0
 */
@Service(interfaceClass = UserPermissionService.class)
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    UserPermissionDao userPermissionDao;

    @Override
    public List<Map<String, Object>> getMenuList(String username) {
        //创建返回数据集合
        List<Map<String,Object>> menuList = new ArrayList<>();
        //调用UserPermissionDao查询用户信息
        List<Menu> list = userPermissionDao.getMenuList(username);

        //创建一个菜单集合
        List<Menu> menuList1 = new ArrayList<>();
        //创建二级菜单集合
        List<Menu> menuList2 = new ArrayList<>();
        for(Menu menu : list){
            //设置一级菜单集合
            Integer parentMenuId = menu.getParentMenuId();
            if (parentMenuId == null){
                menuList1.add(menu);
            }else {
                menuList2.add(menu);
            }
        }
        for (Menu menu1 : menuList1){
            //创建一级菜单Map集合
            Map<String,Object> menuMap1 = new HashMap<>();
            List<Map<String,String>> OaT = new ArrayList<>();
            String path1 = menu1.getPath();
            menuMap1.put("path",path1);
            String name = menu1.getName();
            menuMap1.put("title",name);
            String icon = menu1.getIcon();
            menuMap1.put("icon",icon);
            for (Menu menu2 : menuList2) {
                int menuId1 = menu1.getId();
                int parentMenuId = menu2.getParentMenuId();
                if (menuId1 == parentMenuId){
                    //创造二级菜单集合
                    Map<String,String> menuMap2 = new HashMap<>();
                    String path2 = menu2.getPath();
                    menuMap2.put("path",path2);
                    String name1 = menu2.getName();
                    menuMap2.put("title",name1);
                    String lingUrl = "/pages/" + menu2.getLinkUrl();
                    menuMap2.put("linkUrl",lingUrl);
                    OaT.add(menuMap2);
                }
            }
            menuMap1.put("children", OaT);
            menuList.add(menuMap1);


        }
        System.out.println(menuList);
        return menuList;
    }
}
