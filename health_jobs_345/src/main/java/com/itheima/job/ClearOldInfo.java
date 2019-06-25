package com.itheima.job;

import com.itheima.dao.OrderSettingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：徐子强
 * @date ：Created in 2019/6/24
 * @description ：
 * @version: 1.0
 */
@Service
@Transactional
public class ClearOldInfo {

    @Autowired
    OrderSettingDao orderSettingDao;

    public void clearOldInfo(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date currentDate = new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
        //获取上月时间
        Date date = calendar.getTime();
        String time = simpleDateFormat.format(date);
        orderSettingDao.clearOldInfo(time);
    }
}
