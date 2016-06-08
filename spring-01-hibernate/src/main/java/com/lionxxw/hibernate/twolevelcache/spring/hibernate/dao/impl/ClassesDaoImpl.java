package com.lionxxw.hibernate.twolevelcache.spring.hibernate.dao.impl;

import com.lionxxw.hibernate.twolevelcache.spring.hibernate.domain.Classes;
import com.lionxxw.hibernate.twolevelcache.spring.hibernate.dao.ClassesDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;

/**
 * <p>Description:  </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 上午9:16
 */
public class ClassesDaoImpl extends HibernateDaoSupport implements ClassesDao{

    public void saveClasses(Classes classes) {
        this.getHibernateTemplate().save(classes);
    }

    public Classes getClassesByCid(final Long cid) {
        //return (Classes) this.getHibernateTemplate().find("from Classes where cid = ?",cid).get(0);
        return (Classes) this.getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from Classes where cid = :cid");
                query.setParameter("cid", cid);

                return query.uniqueResult();
            }
        });
    }
}