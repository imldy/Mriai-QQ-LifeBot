package cn.imldy.mriai.console.plugin.view;

import cn.imldy.mriai.console.plugin.bean.Card;
import cn.imldy.mriai.console.plugin.service.CardService;
import net.mamoe.mirai.message.data.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author imldy
 * @date 2021/11/20 1:07
 **/
@Component
public class CardView {
    private static ApplicationContext applicationContext;
    @Resource
    private CardService cardService;

    public Message getCardMessage(String no) {
        Card card = cardService.getCardByNo(no);
        // 构建一个消息Message类子类PlainTextPlainText
        PlainText plainText = new PlainText(card.toString());
        // 构建一个MessageChain，并往里面添加Message
        MessageChain chain = new MessageChainBuilder()
                .append(plainText)
                .build();
        return chain;
    }

    public Message getCardFormatMessage(String no) {
        Card card = cardService.getCardByNo(no);
        // 构建一个消息Message类子类PlainTextPlainText
        String text = String.format("卡号：%s" +
                        "\n卡主：%s" +
                        "\n卡主系别：%s" +
                        "\n余额：%s"
                , card.getNo()
                , card.getOwner().getName()
                , card.getOwner().getDept().getName()
                , card.getBalance()
        );
        PlainText plainText = new PlainText(text);
        // 构建一个MessageChain，并往里面添加Message
        MessageChain chain = new MessageChainBuilder()
                .append(plainText)
                .build();
        return chain;
    }

    public CardView() {
        super();
    }

    public CardView(ApplicationContext applicationContext) {
        super();
        CardView.setApplicationContext(applicationContext);
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        CardView.applicationContext = applicationContext;
    }

    public CardService getCardService() {
        return cardService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }
}
