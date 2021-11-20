package cn.imldy.mriai.console.plugin.mapper;

import cn.imldy.mriai.console.plugin.bean.Person;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author imldy
 * @date 2021/11/20 12:20
 **/
@Mapper
public interface PersonMapper {
    int addPerson(Person person);
    Person getPersonById(int id);
    boolean isExist(Person person);
    boolean isExistById(Person person);
}
