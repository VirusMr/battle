package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> findAll();

    Page<CheckGroup> findByCondition(QueryPageBean queryPageBean);

    void add(Menu menu);

    Menu findById(Integer id);

    void edit(Menu menu);

    int findRoleAndMenu(Integer id);

    void delById(Integer id);
}
