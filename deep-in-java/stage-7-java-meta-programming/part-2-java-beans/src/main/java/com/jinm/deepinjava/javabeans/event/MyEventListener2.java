package com.jinm.deepinjava.javabeans.event;

public class MyEventListener2 implements ApplicationEventListener<MyEvent> {

    @Override
    public void onEvent(MyEvent event) {
        System.err.println("MyEventListener2 处理事件 : " + event);
    }
}
