package cn.imldy.mriai.console.plugin.view;

import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

/**
 * @author imldy
 * @date 2021/11/20 21:41
 **/
public class LifeBotView {
    public static Message getMenuMessage() {
        MessageChain messageChain = new MessageChainBuilder()
                .append("——菜单——\n")
                .append("[31 卡号] - 获取老校区水卡详细信息\n")
                .append("[311 卡号] - 获取老校区水卡格式化信息\n")
                .build();
        return messageChain;
    }
}
