package com.lionxxw.hibernate.twolevelcache.spring.hibernate.test;

import com.lionxxw.hibernate.twolevelcache.spring.hibernate.callback.CustomClassesDao;
import com.lionxxw.hibernate.twolevelcache.spring.hibernate.domain.Classes;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Description: 自定义hibernate回调测试 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 下午2:03
 */
public class CallBackTest {

    @Test
    public void testClassesDao(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomClassesDao customClassesDao = (CustomClassesDao) context.getBean("customClassesDao");
        Classes classes = new Classes();
        classes.setName("回调");
        classes.setDescription("回调测试");
        customClassesDao.save(classes);
    }
}
