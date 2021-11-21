package cn.imldy.mriai.console.plugin.service;

import cn.imldy.mriai.console.plugin.bean.Card;
import cn.imldy.mriai.console.plugin.bean.Dept;
import cn.imldy.mriai.console.plugin.bean.MuYuBaoCard;
import cn.imldy.mriai.console.plugin.bean.Person;
import cn.imldy.mriai.console.plugin.mapper.CardMapper;
import cn.imldy.mriai.console.plugin.mapper.DeptMapper;
import cn.imldy.mriai.console.plugin.mapper.PersonMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author imldy
 * @date 2021/11/22 0:14
 **/
@Service
public class CardService {
    private static ApplicationContext applicationContext;
    @Resource
    private PersonMapper personMapper;
    @Resource
    private DeptMapper deptMapper;
    @Resource
    private CardMapper cardMapper;

    public boolean storageCardInfo(MuYuBaoCard muYuBaoCard) {
        // 获取三各类的实例，用于存储数据并于数据库交互
        Dept dept = applicationContext.getBean(Dept.class);
        Person person = applicationContext.getBean(Person.class);
        Card card = applicationContext.getBean(Card.class);

        // 从沐浴宝卡片信息中，解析出Dept的信息
        dept.setName(muYuBaoCard.getDepartmentName());
        // 判断是否需要保存Dept信息
        if (!deptMapper.isExistByName(dept.getName())) {
            // 保存dept数据到数据包，因id为自增，同时设置返回给定的id，所以dept对象id属性为数据库给的id
            int row = deptMapper.addDept(dept);
        } else {
            // 如果已经存在，则根据姓名， 获取对象
            dept = deptMapper.getDeptByName(dept.getName());
        }
        // 得到数据库返回的Dept id
        person.setDeptId(dept.getId());

        // 从沐浴宝卡片信息中，解析出Person的信息
        person.setName(muYuBaoCard.getStaffName());
        person.setDept(dept);
        person.setPhone(muYuBaoCard.getPhone());
        person.setSex(muYuBaoCard.getSex());
        // 判断是否需要保存Person信息
        if (!personMapper.isExist(person)) {
            // 如果需要保存，且数据库中无此数据
            // 保存person数据到数据包，因id为自增，同时设置返回给定的id，所以dept对象id属性为数据库给的id
            int row = personMapper.addPerson(person);
        } else {
            // 如果数据库中有此数据，根据没有id的本数据，获取有id的数据，但是此对象没有dept的引用
            person = personMapper.getPersonByNamePhone(person);
            // 再次引用dept
            person.setDept(dept);
        }
        // 得到数据库返回的Person id
        card.setOwnerId(person.getId());

        // 从沐浴宝卡片信息中，解析出Card的信息
        card.setNo(String.valueOf(muYuBaoCard.getCardNo()));
        card.setOwner(person);
        card.setStatus(muYuBaoCard.getStatus());
        card.setLastRechargeDate(muYuBaoCard.getLastRechargeDate());
        card.setLastRechargeValue(muYuBaoCard.getLastRechargeValue());
        card.setBalance(muYuBaoCard.getBalance());
        card.setPreStore(muYuBaoCard.getPreStore());

        // 判断是否需要保存Card信息
        if (!cardMapper.isExistByNo(card.getNo())) {
            // 如果不存在
            boolean addResult = cardMapper.addCard(card);
        } else {
            // 如果存在
            boolean updateResult = cardMapper.updateCard(card);
        }
        return true;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        CardService.applicationContext = applicationContext;
    }
}
