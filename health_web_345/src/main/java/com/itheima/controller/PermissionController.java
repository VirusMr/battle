package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    @RequestMapping("/addPermission")
    public Result addPermission(@RequestBody Permission permission){

        try {
            permissionService.addPermission(permission);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "失败");
        }
    }

    @RequestMapping("/findByPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
        try {
            PageResult pageResult = permissionService.pageQuery(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());
            return  pageResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Permission permission){
        permissionService.edit(permission);
        return new Result(true, "成功");
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
       Permission permission = permissionService.findById(id);
        return new Result(true,"成功",permission);
    }

    @RequestMapping("/delById")
    public Result delById(Integer id){
        try {
            permissionService.delById(id);
            return new Result(true, "成功");
        }catch (RuntimeException e){
            return new Result(false, e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<Permission> permissionList = permissionService.findAll();
            return new Result(true,"成功",permissionList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");

        }
    }
}
