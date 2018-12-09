package org.example.spring.ioc;


import org.example.spring.ioc.bean.Entitlement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DemoFileSystemXmlApplicationContext {
    public static void main(String... args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("file:xml/demo-FileSystemXmlApplicationContext.xml");
        Entitlement ent=(Entitlement) context.getBean("entitlement");
        System.out.println(ent.getName());
    }
}
