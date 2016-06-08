package com.lionxxw.hibernate.twolevelcache.hibernate.seesion;

import com.lionxxw.hibernate.twolevelcache.hibernate.session.domain.Person;
import com.lionxxw.hibernate.twolevelcache.hibernate.session.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * <p>Description: 从当前sessionfactory中获取当前session </p>
 * 前提配置:<property name="current_session_context_class">thread</property>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/6 上午10:49
 */
public class CreateSessionTest extends HibernateUtils {

    @Test
    public void testOpentSession(){
        Session session = sessionFactory.openSession();
        Person person = (Person) session.get(Person.class, 1L);
        session.close();
    }

    /**		
     * <p>Description: 获取当前线程的session </p>
     * 
     * @author wangxiang
     * @date 16/6/6 上午10:54
     * @version 1.0
     */
    @Test
    public void testGetCurrentSession(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Person person = (Person) session.get(Person.class, 1L);
        transaction.commit();
    }
}