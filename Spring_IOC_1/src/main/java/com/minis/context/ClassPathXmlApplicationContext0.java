package com.minis.context;

import com.minis.beans.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//该类的作用:按照一定的规则将这个 XML 文件的内容解析出来，获取 Bean 的配置信息
//目前的 ClassPathXmlApplicationContext 兼具了 BeanFactory 的功能，
//它通过 singletons 和 beanDefinitions 初步实现了 Bean 的管理
public class ClassPathXmlApplicationContext0 {
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    //构造器获取外部配置，解析出Bean的定义，形成内存映像
    public ClassPathXmlApplicationContext0(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    //readXml()方法，通过传入的文件路径，也就是XML文件的全路径名，来获取XML内的信息
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath =
                    this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            //根元素
            Element rootElement = document.getRootElement();
            //对配置文件中的每一个<bean>，进行处理
            for (Element element : (List<Element>) rootElement.elements()) {
                //获取Bean的基本配置信息
                String beanID = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                //用这些配置信息构建 BeanDefinition 对象
                BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
                //将Bean的定义存放到beanDefinitions
                beanDefinitions.add(beanDefinition);  //BeanDefinitions存储是一个类的全名
            }
        } catch (Exception e) {
            System.out.println("error: " + this.getClass().getName());
        }
    }

    //根据读取到的信息实例化 Bean，所以我们现在需要将类名转换成一个具体的类
    //利用反射创建Bean实例，并存储在Map集合singletons中
    // (我们可以通过 Java 里的反射机制，也就是 Class.forName 将一个类的名字转化成一个实际存在的类)
    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                //singletons 这个 Map 里，构建了 ID 与实际类的映射关系
                singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
            } catch (Exception e) {
                System.out.println("error: " + this.getClass().getName());
            }
        }
    }

    //这是对外的一个方法，让外部程序从容器中获取Bean实例，会逐步演化成核心方法
    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }
}
