package com.lionxxw.spring.hibernate.dao;

import com.lionxxw.spring.hibernate.domain.Classes;

/**
 * <p>Description:  </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 上午9:15
 */
public interface ClassesDao {

    void saveClasses(Classes classes);

    Classes getClassesByCid(Long cid);
}
