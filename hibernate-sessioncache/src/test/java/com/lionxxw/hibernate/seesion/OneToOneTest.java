package com.lionxxw.hibernate.seesion;

import com.lionxxw.hibernate.session.onetoone.CourseOne;
import com.lionxxw.hibernate.session.onetoone.PersonOne;
import com.lionxxw.hibernate.session.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 一对一关联 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/8 下午2:40
 */
public class OneToOneTest extends HibernateUtils {

    /**
     * 保存person的同时,保存course
     */
    @Test
    public void testSavePersonAndCourse(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        PersonOne person = new PersonOne();
        person.setName("李鸿明");
        CourseOne courseOne = new CourseOne();
        courseOne.setName("java基础课程");
        Set<CourseOne> courseOnes = new HashSet<CourseOne>();
        courseOnes.add(courseOne); // 一对一,只能添加一个.由于有unique="true"标示
        person.setCourses(courseOnes);
        session.save(person);
        transaction.commit();
    }
}
