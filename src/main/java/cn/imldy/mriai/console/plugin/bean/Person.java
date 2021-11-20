package cn.imldy.mriai.console.plugin.bean;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Person {
    private int id;
    private String name;
    @Resource(name = "dept")
    private Dept dept;
    private int deptId;
    private String phone;
    // 0为男，1为女
    private byte sex;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept=" + dept +
                ", deptId=" + deptId +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
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
