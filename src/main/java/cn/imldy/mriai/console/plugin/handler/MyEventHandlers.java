package cn.imldy.mriai.console.plugin.handler;

import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author imldy
 * @date 2021/11/19 23:06
 **/
@Component
public class MyEventHandlers extends SimpleListenerHost {
    @EventHandler
    public ListeningStatus friendListener(FriendMessageEvent event) throws IOException {
        MessageChain messageChain = event.getMessage();
        Friend subject = event.getSubject();
        String content = messageChain.contentToString();
        String[] fields = content.split(" ");
        if ("你好".equals(content)) {
            subject.sendMessage("你好 :)");
        } else if ("31".equals(fields[0])) {
            if (fields.length >= 2) {
                subject.sendMessage("[测试]\n卡号" + fields[1] + "\n余额：11");
            } else {
                subject.sendMessage("请输入卡号");
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
}