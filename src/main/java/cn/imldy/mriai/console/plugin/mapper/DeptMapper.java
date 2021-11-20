package cn.imldy.mriai.console.plugin.mapper;

import cn.imldy.mriai.console.plugin.bean.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author imldy
 * @date 2021/11/20 11:49
 **/
@Mapper
public interface DeptMapper {
    int addDept(Dept dept);
    Dept getDeptById(int id);
    Dept getDeptByName(String name);
    boolean isExistByName(Dept dept);
    boolean isExistById(Dept dept);
}
