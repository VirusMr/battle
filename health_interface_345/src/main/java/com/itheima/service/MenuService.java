package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;

import java.util.List;

public interface MenuService {
    List<Menu> findAll();

    PageResult queryPage(QueryPageBean queryPageBean);

    void add(Menu menu);

    Menu findById(Integer id);

    void edit(Menu menu);

    void delById(Integer id);
}
