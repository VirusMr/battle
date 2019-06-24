package com.itheima.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.service.OrderSettingService;

/**
 * @author ：徐子强
 * @date ：Created in 2019/6/24
 * @description ：
 * @version: 1.0
 */
public class ClearOldInfo {

    @Reference
    OrderSettingService orderSettingService;

    public void clearOldInfo(){
        orderSettingService.ClearOldInfo();
    }
}
