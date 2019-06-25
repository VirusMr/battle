package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.UserPermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: 离殇丶
 * @date: Created in 2019/6/24 14:47
 * @description:
 * @version: 1.0
 */
@RestController
@RequestMapping("/userpermission")
public class UserPermissionController {

    @Reference
    UserPermissionService userPermissionService;

    @RequestMapping("/getMenuList")
    public Result getMenuList(HttpServletRequest request){
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

        if(username == null || "".equals(username)){
            return new Result(false,MessageConstant.GET_MENU_FAIL);
        }
        //返回一个list集合
        List<Map<String,Object>> menuList = userPermissionService.getMenuList(username);
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,menuList);
    }

}
