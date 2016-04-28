package com.lionxxw.spring.hibernate.service.impl;

import com.lionxxw.spring.hibernate.dao.ClassesDao;
import com.lionxxw.spring.hibernate.domain.Classes;
import com.lionxxw.spring.hibernate.service.ClassesService;

/**
 * <p>Description:  </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/4/28 上午9:20
 */
public class ClassesServiceImpl implements ClassesService {

    private ClassesDao classesDao;

    public void saveClasses(Classes classes) {
        classesDao.saveClasses(classes);
    }

    public Classes getClassesByCid(Long cid) {
        return classesDao.getClassesByCid(cid);
    }

    public void setClassesDao(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }
}
