package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.SysUser;
import com.itheima.service.SysUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private SysUserService sysUserService;

    @RequestMapping("/getUsername")
    public Result getUsername(HttpServletRequest request){
        //方法一（不建议）：getAttributeNames  获取session域中所有的属性名,遍历枚举类型， SPRING_SECURITY_CONTEXT
        //Enumeration attributeNames =  request.getSession().getAttributeNames();
        //方法二：
        //获取安全框架的上下文对象
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 获取认证对象
        Authentication authentication = securityContext.getAuthentication();
        //principal: 重要信息（User）
        Object principal = authentication.getPrincipal();
        //强制转换为User
        User user = (User) principal;
        //获取Username
        String username = user.getUsername();

        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, username);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Map<String, Object> map){
        JSONArray jsonArray = (JSONArray) map.get("roleIds");
        Integer[] roleIds = jsonArray.toArray(new Integer[]{});

        JSONObject jsonObject = (JSONObject) map.get("user");
        SysUser sysUser = jsonObject.toJavaObject(SysUser.class);

        try {
            sysUserService.add(sysUser,roleIds);
            return new Result(true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    @RequestMapping("/findByPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
        return sysUserService.queryPage(queryPageBean);
    }


    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            SysUser sysUser = sysUserService.findById(id);
            return new Result(true,"成功",sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    @RequestMapping("/findRoleIdsById")
    public Result findRoleIdsById(Integer id){
        try {
            List<Integer> roleIds = sysUserService.findRoleIdsById(id);
            return new Result(true,"成功",roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] roleIds, @RequestBody SysUser sysUser){
        try {
            sysUserService.edit(sysUser,roleIds);
            return new Result(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"失败");
        }
    }

    @RequestMapping("/delById")
    public Result delById(Integer id){
        try {
            sysUserService.delById(id);
            return new Result(true,"删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }


}
