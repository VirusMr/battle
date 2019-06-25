package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.RoleDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void add(Role role, Integer[] permissionIds, Integer[] menuIds) {
        roleDao.add(role);
        setRoleAndPermisson(role.getId(), permissionIds);
        setRoleAndMenu(role.getId(),menuIds);
    }

    @Override
    public PageResult queryPage(QueryPageBean queryPageBean) {
        //开始分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<Role> page = roleDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal() , page);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Integer> findPermissionIdsById(Integer id) {
        return roleDao.findPermissionIdsById(id);
    }

    @Override
    public List<Integer> findMenuIdsById(Integer id) {
        return roleDao.findMenuIdsById(id);
    }


    @Override
    public void edit(Role role, Integer[] permissionIds, Integer[] menuIds) {
        roleDao.edit(role);
        roleDao.deleteRoleAndPermisson(role.getId());
        roleDao.deleteRoleAndMenu(role.getId());
        this.setRoleAndPermisson(role.getId(),permissionIds);
        this.setRoleAndMenu(role.getId(),menuIds);
    }

    @Override
    public void delById(Integer id) {
        int count = roleDao.findUserAndRoleCountById(id);
        if(count > 0){
            throw new RuntimeException("该角色被关联，不能删除！！");
        }
        roleDao.deletePermissonAssociation(id);
        roleDao.deleteMenuAssociation(id);
        roleDao.delById(id);
    }


    private void setRoleAndPermisson(Integer roleId, Integer[] permissionIds) {
        if (roleId != null && permissionIds != null && permissionIds.length > 0) {
            for (Integer permissionId : permissionIds) {
                roleDao.insertRoleAndPermisson(roleId, permissionId);
            }
        }
    }

    private void setRoleAndMenu(Integer roleId, Integer[] menuIds) {
        if (roleId != null && menuIds != null && menuIds.length > 0) {
            for (Integer menuId : menuIds) {
                roleDao.insertRoleAndMenu(roleId, menuId);
            }
        }
    }
}
