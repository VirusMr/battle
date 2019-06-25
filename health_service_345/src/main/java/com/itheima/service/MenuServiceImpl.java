package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //开始分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> page = menuDao.findByCondition(queryPageBean);
        return new PageResult(page.getTotal() , page);
    }

    @Override
    public void add(Menu menu) {
        menuDao.add(menu);
    }

    @Override
    public Menu findById(Integer id) {
        return menuDao.findById(id);
    }

    @Override
    public void edit(Menu menu) {
         menuDao.edit(menu);
    }

    @Override
    public void delById(Integer id) {
        //1. 判断检查项是否被检查组关联
        int count = menuDao.findRoleAndMenu(id);
        if(count > 0){
            //2. 如果关联， 不能删除
            throw new RuntimeException("该菜单被角色关联，不能删除！！");
        }
        else{
            //3. 如果不关联，可以删除
            menuDao.delById(id);
        }
    }
}
