package com.minis;

import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.test.AService;

public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new
                ClassPathXmlApplicationContext("beans.xml");
        AService aService = (AService)ctx.getBean("aservice");
        aService.sayHello();
    }
}
