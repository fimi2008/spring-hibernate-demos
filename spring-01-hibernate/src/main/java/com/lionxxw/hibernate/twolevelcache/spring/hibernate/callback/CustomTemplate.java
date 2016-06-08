package com.lionxxw.hibernate.twolevelcache.spring.hibernate.callback;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 * <p>Description: 自定义hibernate模板 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 下午1:53
 */
public class CustomTemplate {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void doExecute(CustomHibernateCallBack action) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        action.doInHibernate(session);
        transaction.commit();
        session.close();
    }
}
