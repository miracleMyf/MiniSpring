package com.minis.beans;

//该接口拥有两个特性
//一是获取一个 Bean（getBean），二是注册一个 BeanDefinition（registerBeanDefinition）
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
