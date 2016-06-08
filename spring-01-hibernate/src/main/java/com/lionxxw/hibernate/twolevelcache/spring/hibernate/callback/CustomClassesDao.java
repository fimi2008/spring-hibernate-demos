package com.lionxxw.hibernate.twolevelcache.spring.hibernate.callback;

import com.lionxxw.hibernate.twolevelcache.spring.hibernate.domain.Classes;
import org.hibernate.Session;

/**
 * <p>Description: 继承于自定义的hibernate模板 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 下午2:01
 */
public class CustomClassesDao extends CustomTemplate {

    public void save(final Classes classes){
        this.doExecute(new CustomHibernateCallBack() {
            public Object doInHibernate(Session session) {
                session.save(classes);
                return null;
            }
        });
    }
}
