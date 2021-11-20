package cn.imldy.mriai.console.plugin.bean;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class MuYuBaoCard {
    // 编号
    private int cardNo;
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
    // 卡所属员工姓名
    private String staffName;
    // 卡所属员工部门名
    private String departmentName;
    // 卡所属员工手机号
    private String phone;
    // 卡所属员工性别
    private byte sex;

    @Override
    public String toString() {
        return "MuYuBaoCard{" +
                "cardNo=" + cardNo +
                ", status=" + status +
                ", lastRechargeDate=" + lastRechargeDate +
                ", lastRechargeValue=" + lastRechargeValue +
                ", balance=" + balance +
                ", preStore=" + preStore +
                ", staffName='" + staffName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                '}';
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }
}
