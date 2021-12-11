package cn.imldy.mriai.console.plugin.service;

import cn.imldy.mriai.console.plugin.bean.User;
import cn.imldy.mriai.console.plugin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author imldy
 * @date 2021/11/20 22:34
 **/
@Service
public class UserService {
    @Autowired
    private ApplicationContext applicationContext;
    @Resource
    private UserMapper userMapper;

    public boolean bindCardByNo(User user) {
        boolean exist = userMapper.isExistByQQId(user);
        boolean result;
        if (exist) {
            result = userMapper.setSouthWaterCardNoByQQId(user);
        } else {
            result = userMapper.addUser(user);
        }
        return result;
    }

    public String getSouthWaterCardNoByUserQQId(long qqId) {
        String southWaterCardNo = userMapper.getSouthWaterCardNoByUserQQId(String.valueOf(qqId));
        return southWaterCardNo;
    }
}
