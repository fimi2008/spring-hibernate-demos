package com.lionxxw.spring.hibernate.test;

import com.lionxxw.spring.hibernate.domain.Classes;
import com.lionxxw.spring.hibernate.service.ClassesService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Description:  </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 上午11:29
 */
public class ClassesTest {

    @Test
    public void testSaveClasses(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassesService classesService = (ClassesService) context.getBean("classesService");
        Classes classes = new Classes();
        classes.setName("测试");
        classes.setDescription("测试内容");
        classesService.saveClasses(classes);
    }

    @Test
    public void testGetClassesByCid(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassesService classesService = (ClassesService) context.getBean("classesService");
        Classes classes = classesService.getClassesByCid(1L);
        System.out.println(classes.getName() + ";" +classes.getDescription());
    }
}
