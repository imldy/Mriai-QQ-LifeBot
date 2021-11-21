package cn.imldy.mriai.console.plugin;

import cn.imldy.mriai.console.plugin.handler.MyEventHandlers;
import cn.imldy.mriai.console.plugin.service.CardService;
import cn.imldy.mriai.console.plugin.service.MuYuBaoCardService;
import cn.imldy.mriai.console.plugin.service.UserService;
import cn.imldy.mriai.console.plugin.view.MuYuBaoCardView;
import cn.imldy.mriai.console.plugin.view.UserView;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author imldy
 * @date 2021/11/20 18:16
 **/
public class LifeBotMain {
    /**
     * 独立运行的 Mirai Bot
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String qqBotPropertiesPath = "config/qqbot.properties";
        Properties props = new Properties();
        props.load(new FileInputStream(qqBotPropertiesPath));
        String qqBotNoProperty = props.getProperty("qqbot.no");
        String qqBotPwdProperty = props.getProperty("qqbot.pwd");

        long qqBotNo = Long.valueOf(qqBotNoProperty);
        String qqBotPwd = qqBotPwdProperty;
        BotConfiguration botConfiguration = new BotConfiguration() {{
            fileBasedDeviceInfo(); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_WATCH); // 切换协议
        }};
        Bot bot = BotFactory.INSTANCE.newBot(qqBotNo, qqBotPwd, botConfiguration);
        bot.login();
        LifeBotMain.afterLogin(bot);
    }

    public static void afterLogin(Bot bot) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 给类设置静态属性
        MuYuBaoCardView.setApplicationContext(context);
        MuYuBaoCardService.setApplicationContext(context);
        UserView.setApplicationContext(context);
        UserService.setApplicationContext(context);
        CardService.setApplicationContext(context);
        // 获取事件处理器
        MyEventHandlers myEventHandlers = context.getBean(MyEventHandlers.class);
        myEventHandlers.setApplicationContext(context);
        // 设置事件处理类
        bot.getEventChannel().registerListenerHost(myEventHandlers);
    }
}
