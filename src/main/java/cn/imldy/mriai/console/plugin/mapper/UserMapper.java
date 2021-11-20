package cn.imldy.mriai.console.plugin.mapper;

import cn.imldy.mriai.console.plugin.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author imldy
 * @date 2021/11/20 22:28
 **/
@Mapper
public interface UserMapper {
    boolean setSouthWaterCardNoByQQId(User user);

    String getSouthWaterCardNoByUserQQId(String qqId);
}
