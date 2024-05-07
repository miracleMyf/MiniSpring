package com.minis.beans;

public class BeanDefinition {
    private String id;  //用来给该类起别名
    private String className;

    //全参数的构造方法
    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
