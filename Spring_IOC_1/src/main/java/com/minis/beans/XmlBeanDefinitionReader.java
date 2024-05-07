package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

//将 ClassPathXmlResource 解析好的 XML 转换成我们需要的 BeanDefinition
//并加载到 BeanFactory 中
public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    //loadBeanDefinitions 方法会把解析的 XML 内容转换成 BeanDefinition，并加载到 BeanFactory 中。
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            this.beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
