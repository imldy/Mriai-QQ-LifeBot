package cn.imldy.mriai.console.plugin.view;

import cn.imldy.mriai.console.plugin.bean.User;
import cn.imldy.mriai.console.plugin.service.UserService;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author imldy
 * @date 2021/11/20 22:23
 **/
@Component
public class UserView {
    private static ApplicationContext applicationContext;
    @Resource
    private UserService userService;

    public Message bindCard(User user) {
        boolean result = userService.bindCardByNo(user);
        MessageChainBuilder messageChainBuilder = new MessageChainBuilder();
        MessageChain messageChain = null;
        if (result) {
            messageChain = messageChainBuilder.append("绑定成功").build();
        } else {
            messageChain = messageChainBuilder.append("绑定失败").build();
        }
        return messageChain;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        UserView.applicationContext = applicationContext;
    }
}
