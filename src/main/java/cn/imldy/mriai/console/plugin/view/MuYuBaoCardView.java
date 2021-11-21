package cn.imldy.mriai.console.plugin.view;

import cn.imldy.mriai.console.plugin.bean.MuYuBaoCard;
import cn.imldy.mriai.console.plugin.service.MuYuBaoCardService;
import net.mamoe.mirai.message.data.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author imldy
 * @date 2021/11/20 1:07
 **/
@Component
public class MuYuBaoCardView {
    private static ApplicationContext applicationContext;
    @Resource
    private MuYuBaoCardService muYuBaoCardService;

    public Message getMuYuBaoCardMessage(String no) {
        MuYuBaoCard muYuBaoCard = muYuBaoCardService.getMuYuBaoCardByNo(no);
        // 构建一个消息Message类子类PlainTextPlainText
        PlainText plainText = new PlainText(muYuBaoCard.toString());
        // 构建一个MessageChain，并往里面添加Message
        MessageChain chain = new MessageChainBuilder()
                .append(plainText)
                .build();
        return chain;
    }

    public Message getMuYuBaoCardFormatMessage(String no) {
        MuYuBaoCard muYuBaoCard = muYuBaoCardService.getMuYuBaoCardByNo(no);
        // 构建一个消息Message类子类PlainTextPlainText
        String text = String.format("卡号：%s" +
                        "\n卡主：%s" +
                        "\n卡主系别：%s" +
                        "\n余额：%s"
                , muYuBaoCard.getCardNo()
                , muYuBaoCard.getStaffName()
                , muYuBaoCard.getDepartmentName()
                , muYuBaoCard.getBalance()
        );
        PlainText plainText = new PlainText(text);
        // 构建一个MessageChain，并往里面添加Message
        MessageChain chain = new MessageChainBuilder()
                .append(plainText)
                .build();
        return chain;
    }

    public MuYuBaoCardView() {
        super();
    }

    public MuYuBaoCardView(ApplicationContext applicationContext) {
        super();
        MuYuBaoCardView.setApplicationContext(applicationContext);
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        MuYuBaoCardView.applicationContext = applicationContext;
    }

    public MuYuBaoCardService getMuYuBaoCardService() {
        return muYuBaoCardService;
    }

    public void setMuYuBaoCardService(MuYuBaoCardService muYuBaoCardService) {
        this.muYuBaoCardService = muYuBaoCardService;
    }
}
