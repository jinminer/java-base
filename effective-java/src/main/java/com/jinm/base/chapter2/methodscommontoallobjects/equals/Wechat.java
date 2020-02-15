package com.jinm.base.chapter2.methodscommontoallobjects.equals;

public class Wechat {

    public static void main(String[] args) {

        System.out.println(new Wechat().equals(new Wechat()));
        System.out.println(new Wechat().getClass() == new WechatPay().getClass());

    }

}

class WechatPay extends Wechat{

}
