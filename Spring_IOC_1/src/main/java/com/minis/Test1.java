package com.minis;

import com.minis.beans.BeansException;
import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.context.ClassPathXmlApplicationContext0;
import com.minis.test.AService;

public class Test1 {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext("beans.xml");
        //通过 getBean 方法，获取注入到 singletons 里的这个类 AService
        AService aService = (AService)ctx.getBean("aservice");
        aService.sayHello();
    }
}
