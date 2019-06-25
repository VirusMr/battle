package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public void addPermission(Permission permission) {
        permissionDao.add(permission);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Permission> page =  permissionDao.queryPage(queryString);

        return new PageResult(page.getTotal() , page.getResult());
    }

    @Override
    public void edit(Permission permission) {
        permissionDao.edit(permission);
    }

    @Override
    public Permission findById(Integer id) {
        return permissionDao.findById(id);
    }

    @Override
    public void delById(Integer id) {
        //1. 判断检查项是否被检查组关联
        int count = permissionDao.findByCheckItemId(id);
        if(count > 0){
            //2. 如果关联， 不能删除
            throw new RuntimeException("该检查项被检查组关联，不能删除！！");
        }
        else{
            //3. 如果不关联，可以删除
            permissionDao.delById(id);
        }
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }


}
