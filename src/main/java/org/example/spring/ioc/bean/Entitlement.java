package org.example.spring.ioc.bean;

public class Entitlement {
    private String name;
    private int time;
    public Entitlement(String name,int time){
        this.name=name;
        this.time=time;
    }
    public String getName() {
        return name;
    }
    public int getTime() {
        return time;
    }
}