package cn.imldy.mriai.console.plugin.handler;

import cn.imldy.mriai.console.plugin.bean.User;
import cn.imldy.mriai.console.plugin.service.UserService;
import cn.imldy.mriai.console.plugin.view.MuYuBaoCardView;
import cn.imldy.mriai.console.plugin.view.LifeBotView;
import cn.imldy.mriai.console.plugin.view.UserView;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.*;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author imldy
 * @date 2021/11/19 23:06
 **/
@Component
public class MyEventHandlers extends SimpleListenerHost {
    @Autowired
    private ApplicationContext applicationContext;
    @Resource
    private MuYuBaoCardView muYuBaoCardView;
    @Resource
    private UserView userView;
    @Resource
    private UserService userService;

    /**
     * 回复私聊信息
     *
     * @param event
     */
    public void replyPrivateChatMessage(MessageEvent event) {
        if (event.getBot().getId() == event.getSender().getId()) {
            // 禁止回复自己发送的消息，防止无限循环
            return;
        }
        MessageChain messageChain = event.getMessage();
        Contact subject = event.getSubject();
        String content = messageChain.contentToString();
        String[] fields = content.split(" ");
        String command = fields[0];
        if ("你好".equals(content)) {
            subject.sendMessage("你好 :)");
        } else if ("31".equals(command)) {
            if (fields.length >= 2) {
                String no = fields[1];
                subject.sendMessage(muYuBaoCardView.getMuYuBaoCardMessage(no));
            } else {
                String cardNo = userService.getSouthWaterCardNoByUserQQId(event.getSender().getId());
                if (cardNo != null) {
                    subject.sendMessage(muYuBaoCardView.getMuYuBaoCardMessage(cardNo));
                } else {
                    subject.sendMessage("请输入卡号，例如[31 999]，或者发送[绑定]获取绑定卡号的方法");
                }
            }
        } else if ("311".equals(command)) {
            if (fields.length >= 2) {
                String no = fields[1];
                subject.sendMessage(muYuBaoCardView.getMuYuBaoCardFormatMessage(no));
            } else {
                String cardNo = userService.getSouthWaterCardNoByUserQQId(event.getSender().getId());
                if (cardNo != null) {
                    subject.sendMessage(muYuBaoCardView.getMuYuBaoCardFormatMessage(cardNo));
                } else {
                    subject.sendMessage("请输入卡号，例如[311 999]，或者发送[绑定]获取绑定卡号的方法");
                }
            }
        } else if ("绑定".equals(command)) {
            if (fields.length >= 2) {
                String cardType = fields[1];
                if ("老校区水卡".equals(cardType)) {
                    if (fields.length >= 3) {
                        String cardNo = fields[2];
                        long qqId = event.getSender().getId();
                        User user = applicationContext.getBean(User.class);
                        user.setQqId(qqId);
                        user.setSouthWaterCardNo(cardNo);
                        Message message = userView.bindCard(user);
                        subject.sendMessage(message);
                    } else {
                        subject.sendMessage("参数数量不够，请输入要绑定的信息，例如：\n[绑定 老校区水卡 卡号] - 绑定老校区水卡");
                    }
                } else {
                    subject.sendMessage("参数数量不够，请输入要绑定的信息，例如：\n[绑定 老校区水卡 卡号] - 绑定老校区水卡");
                }
            } else {
                subject.sendMessage("无参数，请输入要绑定的信息，例如：\n[绑定 老校区水卡 卡号] - 绑定老校区水卡");
            }
        } else {
            subject.sendMessage(LifeBotView.getMenuMessage());
        }
    }

    @EventHandler
    public ListeningStatus friendListener(FriendMessageEvent event) throws IOException {
        // 好友消息
        this.replyPrivateChatMessage(event);
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus groupListener(GroupMessageEvent event) throws IOException {

        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus groupTempMessageEventListener(GroupTempMessageEvent event) {
        // 群临时会话消息
        this.replyPrivateChatMessage(event);
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus strangerMessageEventListener(StrangerMessageEvent event) {
        // 陌生人消息
        this.replyPrivateChatMessage(event);
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus newFriendRequestEventListener(NewFriendRequestEvent event) {
        // 一个账号请求添加机器人为好友
        // 接受加好友申请
        event.accept();
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus friendAddEventListener(FriendAddEvent event) {
        // 成功添加了一个新好友
        event.getFriend().sendMessage("我们目前是好友了");
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus bothEven(MessageEvent event) throws IOException {

        return ListeningStatus.LISTENING;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setMuYuBaoCardView(MuYuBaoCardView muYuBaoCardView) {
        this.muYuBaoCardView = muYuBaoCardView;
    }

}