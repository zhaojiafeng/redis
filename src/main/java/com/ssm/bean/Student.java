package com.ssm.bean;

import java.io.Serializable;

/**
 * Created by dllo on 17/12/28.
 */
public class Student implements Serializable {
    private int id;
    private String name;

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
