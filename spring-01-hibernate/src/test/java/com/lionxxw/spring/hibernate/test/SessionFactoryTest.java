package com.lionxxw.spring.hibernate.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Description:  </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 上午9:34
 */
public class SessionFactoryTest {

    @Test
    public void testSessionFactory(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        System.out.println(sessionFactory);
    }

    @Test
    public void testSessionFactory2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory2");
        System.out.println(sessionFactory);
    }
}
