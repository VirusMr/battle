package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.UserDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = SysUserService.class)
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(SysUser sysUser, Integer[] roleIds) {
        userDao.add(sysUser);
        setSysUserAndRole(sysUser.getId(),roleIds);

    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //开始分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<SysUser> page = userDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal() , page);
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public List<Integer> findRoleIdsById(Integer id) {
        return userDao.findRoleIdsById(id);
    }

    @Override
    public void edit(SysUser sysUser, Integer[] roleIds) {
        userDao.edit(sysUser);
        userDao.deleteAssociation(sysUser.getId());
        this.setSysUserAndRole(sysUser.getId(),roleIds);
    }

    @Override
    public void delById(Integer id) {
        userDao.deleteAssociation(id);
        userDao.delById(id);
    }

    private void setSysUserAndRole(Integer userId, Integer[] roleIds){
        if(userId != null && roleIds != null && roleIds.length > 0){
            for (Integer roleId : roleIds) {
                userDao.insert(userId,roleId);
            }
        }
    }
}
