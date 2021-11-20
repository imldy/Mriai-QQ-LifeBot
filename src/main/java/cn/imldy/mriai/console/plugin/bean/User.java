package cn.imldy.mriai.console.plugin.bean;

import org.springframework.stereotype.Component;

/**
 * @author imldy
 * @date 2021/11/20 22:25
 **/
@Component
public class User {
    private int id;
    private long qqId;
    private String southWaterCardNo;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", qqId=" + qqId +
                ", southWaterCardNo='" + southWaterCardNo + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getQqId() {
        return qqId;
    }

    public void setQqId(long qqId) {
        this.qqId = qqId;
    }

    public String getSouthWaterCardNo() {
        return southWaterCardNo;
    }

    public void setSouthWaterCardNo(String southWaterCardNo) {
        this.southWaterCardNo = southWaterCardNo;
    }
}
