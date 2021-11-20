package cn.imldy.mriai.console.plugin.service;

import cn.imldy.mriai.console.plugin.bean.User;
import cn.imldy.mriai.console.plugin.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author imldy
 * @date 2021/11/20 22:34
 **/
@Component
public class UserService {
    private static ApplicationContext applicationContext;
    @Resource
    private UserMapper userMapper;

    public boolean bindCardByNo(User user) {
        boolean result = userMapper.setSouthWaterCardNoByQQId(user);
        return result;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        UserService.applicationContext = applicationContext;
    }
}
