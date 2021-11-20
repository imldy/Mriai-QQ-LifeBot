package cn.imldy.mriai.console.plugin.api;

import cn.imldy.mriai.console.plugin.bean.MuYuBaoCard;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;

/**
 * 沐浴宝中使用域名的接口
 */
@Repository
public class OpenApi {
    private String protocol;
    private String host;
    private int port;
    private OkHttpClient okHttpClient;

    /**
     * 根据卡号，获取卡片信息组成的卡片实例
     *
     * @param no 卡号
     * @return 沐浴宝卡片实例
     */
    public MuYuBaoCard getMuYuBaoCardByNo(String no) throws IOException {
        URL url = new URL(this.protocol, this.host, this.port, "/OpenApi/QueryCard");
        String cardText = this.postMuYuBaoCardNo(url.toString(), no);
        MuYuBaoCard muYuBaoCard = this.parseMuYuBaoCard(cardText);
        return muYuBaoCard;
    }

    public String postMuYuBaoCardNo(String url, String cardNo) {
        RequestBody body = new FormBody.Builder()
                .add("card_no", cardNo)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = this.okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

    /**
     * 从沐浴宝API返回的JSON文本中，解析出MuYuBaoCard类实例
     * @param s 沐浴宝API返回的JSON文本
     * @return MuYuBaoCard类实例
     */
    public MuYuBaoCard parseMuYuBaoCard(String s) {
        JSONObject resultObject = JSONObject.parseObject(s);
        Boolean result = resultObject.getBoolean("result");
        if (result) {
            MuYuBaoCard muYuBaoCard = resultObject.getObject("tag", MuYuBaoCard.class);
            return muYuBaoCard;
        }
        return null;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
}
