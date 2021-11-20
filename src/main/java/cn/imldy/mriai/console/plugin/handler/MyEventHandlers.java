package cn.imldy.mriai.console.plugin.handler;

import cn.imldy.mriai.console.plugin.view.CardView;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
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
    private ApplicationContext applicationContext;
    @Resource
    private CardView cardView;

    @EventHandler
    public ListeningStatus friendListener(FriendMessageEvent event) {
        MessageChain messageChain = event.getMessage();
        Friend subject = event.getSubject();
        String content = messageChain.contentToString();
        String[] fields = content.split(" ");
        if ("你好".equals(content)) {
            subject.sendMessage("你好 :)");
        } else if ("31".equals(fields[0])) {
            if (fields.length >= 2) {
                String no = fields[1];
                subject.sendMessage(cardView.getCardMessage(no));
            } else {
                subject.sendMessage("请输入卡号，例如[31 999]");
            }
        } else if ("311".equals(fields[0])) {
            if (fields.length >= 2) {
                String no = fields[1];
                subject.sendMessage(cardView.getCardFormatMessage(no));
            } else {
                subject.sendMessage("请输入卡号，例如[311 999]");
            }
        }
        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus groupListener(GroupMessageEvent event) throws IOException {

        return ListeningStatus.LISTENING;
    }

    @EventHandler
    public ListeningStatus bothEven(MessageEvent event) throws IOException {

        return ListeningStatus.LISTENING;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }
}