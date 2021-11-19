package cn.imldy.mriai.console.plugin;

import cn.imldy.mriai.console.plugin.handler.MyEventHandlers;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import org.jetbrains.annotations.NotNull;

public final class LifeBot extends JavaPlugin {
    public static final LifeBot INSTANCE = new LifeBot();

    private LifeBot() {
        super(new JvmPluginDescriptionBuilder(
                        "cn.imldy.mriai.console.plugin.lifebot",
                        "1.0-SNAPSHOT"
                )
                        // 插件名
                        .name("LifeBot")
                        // 作者
                        .author("imldy")
                        // 描述
                        .info("一个学校生活机器人")
                        .build()
        );
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
        getLogger().info("Plugin loading……");
        super.onLoad($this$onLoad);
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
        long qqBotNo = 3116343117L;
        // 公共通道，一直监听事件
        GlobalEventChannel.INSTANCE.subscribeAlways(BotOnlineEvent.class, event -> {
            if (event.getBot().getId() == qqBotNo) {
                getLogger().info("目标QQ[" + qqBotNo + "]已登录，开始获取实例并绑定事件处理方法");
                Bot bot = Bot.getInstance(qqBotNo);
                EventChannel<BotEvent> eventChannel = bot.getEventChannel();
                eventChannel.registerListenerHost(new MyEventHandlers());
            }
        });
    }
}