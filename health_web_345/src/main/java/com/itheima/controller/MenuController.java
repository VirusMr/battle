package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.MenuService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    @RequestMapping("/findAll")
    public Result findAll(){
        List<Menu> menuList = menuService.findAll();
        return new Result(true,"成功", menuList);
    }

    @RequestMapping("/findByPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
        return menuService.queryPage(queryPageBean);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Menu menu){

        try {
            menuService.add(menu);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "失败");
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        Menu menu = menuService.findById(id);
        return new Result(true,"成功",menu);
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Menu menu){
        menuService.edit(menu);
        return new Result(true, "成功");
    }

    @RequestMapping("/delById")
    public Result delById(Integer id){
        try {
            menuService.delById(id);
            return new Result(true, "成功");
        }catch (RuntimeException e){
            return new Result(false, e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }
}
