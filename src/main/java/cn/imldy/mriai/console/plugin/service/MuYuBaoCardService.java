package cn.imldy.mriai.console.plugin.service;

import cn.imldy.mriai.console.plugin.api.OpenApi;
import cn.imldy.mriai.console.plugin.bean.MuYuBaoCard;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author imldy
 * @date 2021/11/20 1:07
 **/
@Service
public class MuYuBaoCardService {
    private static ApplicationContext applicationContext;
    @Resource
    private CardService cardService;

    /**
     * 根据卡号，获取Card实例
     *
     * @param no 卡号
     * @return MuYuBaoCard实例
     */
    public MuYuBaoCard getMuYuBaoCardByNo(String no) {
        OpenApi openApi = applicationContext.getBean("openApi", OpenApi.class);
        boolean storage = true;
        MuYuBaoCard muYuBaoCard = null;
        try {
            muYuBaoCard = openApi.getMuYuBaoCardByNo(no);
            if (storage) {
                try {
                    cardService.storageCardInfo(muYuBaoCard);
                } catch (NullPointerException e) {
                    System.out.println("保存错误：" + e.fillInStackTrace());
                }
            } else {
                // 如果不需要保存，可能数据库中有此数据，也可能没此数据
            }
        } catch (IOException ioException) {
            System.out.println("保存错误：" + ioException.fillInStackTrace());
        }
        return muYuBaoCard;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        MuYuBaoCardService.applicationContext = applicationContext;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }
}
