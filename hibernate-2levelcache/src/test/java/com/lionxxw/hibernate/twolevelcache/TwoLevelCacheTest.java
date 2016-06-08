package com.lionxxw.hibernate.twolevelcache;

import com.lionxxw.hibernate.twolevelcache.domain.Person;
import com.lionxxw.hibernate.twolevelcache.utils.HibernateUtils;
import org.hibernate.classic.Session;
import org.junit.Test;

/**
 * <p>Description: 二级缓存测试 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/8 下午4:45
 */
public class TwoLevelCacheTest extends HibernateUtils {

    @Test
    public void testGet(){
        Session session = sessionFactory.openSession();
        Person person = (Person) session.get(Person.class, 1L);
        session.close();
        session = sessionFactory.openSession();
        person = (Person) session.get(Person.class, 1L);
        session.close();
    }
}
