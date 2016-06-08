package com.lionxxw.hibernate.twolevelcache.spring.hibernate.service;

import com.lionxxw.hibernate.twolevelcache.spring.hibernate.domain.Classes;

/**
 * <p>Description:  </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 上午9:19
 */
public interface ClassesService {
    void saveClasses(Classes classes);

    Classes getClassesByCid(Long cid);
}
