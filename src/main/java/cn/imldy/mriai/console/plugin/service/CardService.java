package cn.imldy.mriai.console.plugin.service;

import cn.imldy.mriai.console.plugin.api.OpenApi;
import cn.imldy.mriai.console.plugin.bean.Card;
import cn.imldy.mriai.console.plugin.bean.Dept;
import cn.imldy.mriai.console.plugin.bean.MuYuBaoCard;
import cn.imldy.mriai.console.plugin.bean.Person;
import cn.imldy.mriai.console.plugin.mapper.CardMapper;
import cn.imldy.mriai.console.plugin.mapper.DeptMapper;
import cn.imldy.mriai.console.plugin.mapper.PersonMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author imldy
 * @date 2021/11/20 1:07
 **/
public class CardService {
    private static ApplicationContext applicationContext;

    /**
     * 根据卡号，获取Card实例
     *
     * @param no 卡号
     * @return Card实例
     */
    public Card getCardByNo(String no) {
        ApplicationContext context = applicationContext;
        OpenApi openApi = context.getBean("openApi", OpenApi.class);
        boolean storage = true;
        Dept dept = context.getBean(Dept.class);
        Person person = context.getBean(Person.class);
        Card card = context.getBean(Card.class);
        PersonMapper personMapper = context.getBean(PersonMapper.class);
        DeptMapper deptMapper = context.getBean(DeptMapper.class);
        CardMapper cardMapper = context.getBean(CardMapper.class);
        try {
            MuYuBaoCard muYuBaoCard = openApi.getMuYuBaoCardByNo(no);
            // 从沐浴宝卡片信息中，解析出三个类，Dept、Person、Card
            dept.setName(muYuBaoCard.getDepartmentName());
            if (storage) {
                if (!deptMapper.isExistByName(dept.getName())) {
                    // 保存dept数据到数据包，因id为自增，同时设置返回给定的id，所以dept对象id属性为数据库给的id
                    int row = deptMapper.addDept(dept);
                } else {
                    // 如果已经存在，则根据姓名， 获取对象
                    dept = deptMapper.getDeptByName(dept.getName());
                }
            } else {
                // 如果不需要保存，可能数据库中有此数据，也可能没此数据
            }
            person.setDeptId(dept.getId());
            person.setName(muYuBaoCard.getStaffName());
            person.setDept(dept);
            person.setPhone(muYuBaoCard.getPhone());
            person.setSex(muYuBaoCard.getSex());
            if (storage) {
                // 如果需要保存，
                if (!personMapper.isExist(person)) {
                    // 如果需要保存，且数据库中无此数据
                    // 保存person数据到数据包，因id为自增，同时设置返回给定的id，所以dept对象id属性为数据库给的id
                    int row = personMapper.addPerson(person);
                } else {
                    // 如果数据库中有此数据，根据没有id的本数据，获取有id的数据
                    person = personMapper.getPersonByNamePhone(person);
                }
            }
            card.setOwnerId(person.getId());
            card.setNo(String.valueOf(muYuBaoCard.getCardNo()));
            card.setOwner(person);
            card.setStatus(muYuBaoCard.getStatus());
            card.setLastRechargeDate(muYuBaoCard.getLastRechargeDate());
            card.setLastRechargeValue(muYuBaoCard.getLastRechargeValue());
            card.setBalance(muYuBaoCard.getBalance());
            card.setPreStore(muYuBaoCard.getPreStore());
            if (storage) {
                // 如果保存
                if (!cardMapper.isExistByNo(card.getNo())) {
                    // 如果不存在
                    boolean addResult = cardMapper.addCard(card);
                } else {
                    // 如果存在
                    boolean updateResult = cardMapper.updateCard(card);
                }
            }
        } catch (IOException ioException) {
            ioException.fillInStackTrace();
        }
        return card;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        CardService.applicationContext = applicationContext;
    }
}
