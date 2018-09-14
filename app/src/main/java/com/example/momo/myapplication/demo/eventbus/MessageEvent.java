package com.example.momo.myapplication.demo.eventbus;

public class MessageEvent {


    public String name;

    public MessageEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "name='" + name + '\'' +
                '}';
    }
}
