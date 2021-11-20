package cn.imldy.mriai.console.plugin.bean;

import org.springframework.stereotype.Component;

/**
 * @author imldy
 * @date 2021/11/20 1:02
 **/
@Component
public class Dept {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
