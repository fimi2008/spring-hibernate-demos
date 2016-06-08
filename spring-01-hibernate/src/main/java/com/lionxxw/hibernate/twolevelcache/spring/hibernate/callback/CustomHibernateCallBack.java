package com.lionxxw.hibernate.twolevelcache.spring.hibernate.callback;

import org.hibernate.Session;

/**
 * <p>Description: 自定义hibernate回调 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 下午1:59
 */
public interface CustomHibernateCallBack {
    Object doInHibernate(Session session);
}
