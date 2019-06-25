package com.itheima.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    @RequestMapping("/add")
    public Result add(@RequestBody Map<String, Object> map) {
        //从map集合中获取jsonArray
        JSONArray jsonArray = (JSONArray) map.get("permissionIds");
        //把jsonArray反序列化为整数类型的数组
        Integer[] permissionIds = jsonArray.toArray(new Integer[]{});


        JSONArray jsonArray2 = (JSONArray) map.get("menuIds");
        Integer[] menuIds = jsonArray2.toArray(new Integer[]{});


        //从map集合中获取json对象
        JSONObject jsonObject = (JSONObject) map.get("role");
        //把json对象反序列化为检查组
        Role role = jsonObject.toJavaObject(Role.class);

        try {
            roleService.add(role, permissionIds, menuIds);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Role> roleList = roleService.findAll();
        return new Result(true, "成功", roleList);
    }


    @RequestMapping("/findByPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
        return roleService.queryPage(queryPageBean);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
           Role role = roleService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,role);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findPermissionIdsById")
    public Result findPermissionIdsById(Integer id){
        try {
            List<Integer> permissionIds = roleService.findPermissionIdsById(id);
            return new Result(true,"成功",permissionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    @RequestMapping("/findMenuIdsById")
    public Result findMenuIdsById(Integer id){
        try {
            List<Integer> menuIds = roleService.findMenuIdsById(id);
            return new Result(true,"成功",menuIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody Map<String, Object> map){
            //从map集合中获取jsonArray
            JSONArray jsonArray = (JSONArray) map.get("permissionIds");
            //把jsonArray反序列化为整数类型的数组
            Integer[] permissionIds = jsonArray.toArray(new Integer[]{});


            JSONArray jsonArray2 = (JSONArray) map.get("menuIds");
            Integer[] menuIds = jsonArray2.toArray(new Integer[]{});


            //从map集合中获取json对象
            JSONObject jsonObject = (JSONObject) map.get("role");
            //把json对象反序列化为检查组
            Role role = jsonObject.toJavaObject(Role.class);

        try {
            roleService.edit(role,permissionIds,menuIds);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    @RequestMapping("/delById")
    public Result delById(Integer id){
        try {
            roleService.delById(id);
            return new Result(true,"成功");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new Result(false,e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }
}
