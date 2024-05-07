package com.minis.core;
import java.util.Iterator;

//外部的配置信息都当成 Resource（资源）来进行抽象
//有了这个接口我们后面可以从数据库还有 Web 网络上面拿信息
public interface Resource extends Iterator<Object> {
}
