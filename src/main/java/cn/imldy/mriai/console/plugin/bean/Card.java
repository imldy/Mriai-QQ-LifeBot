package cn.imldy.mriai.console.plugin.bean;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Date;

@Component
public class Card {
    // 编号
    private String no;
    // 所有者
    @Resource(name = "person")
    private Person owner;
    private int ownerId;
    // 状态
    private byte status;
    // 最近充值时间
    private Date lastRechargeDate;
    // 最近充值金额
    private float lastRechargeValue;
    // 余额
    private float balance;
    // 预充值金额
    private float preStore;

    @Override
    public String toString() {
        return "Card{" +
                "no='" + no + '\'' +
                ", owner=" + owner +
                ", ownerId=" + ownerId +
                ", status=" + status +
                ", lastRechargeDate=" + lastRechargeDate +
                ", lastRechargeValue=" + lastRechargeValue +
                ", balance=" + balance +
                ", preStore=" + preStore +
                '}';
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getLastRechargeDate() {
        return lastRechargeDate;
    }

    public void setLastRechargeDate(Date lastRechargeDate) {
        this.lastRechargeDate = lastRechargeDate;
    }

    public float getLastRechargeValue() {
        return lastRechargeValue;
    }

    public void setLastRechargeValue(float lastRechargeValue) {
        this.lastRechargeValue = lastRechargeValue;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getPreStore() {
        return preStore;
    }

    public void setPreStore(float preStore) {
        this.preStore = preStore;
    }
}
