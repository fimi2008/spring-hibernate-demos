package com.lionxxw.hibernate.seesion;

import com.lionxxw.hibernate.session.onetomany.doubles.Classes2;
import com.lionxxw.hibernate.session.onetomany.doubles.Student2;
import com.lionxxw.hibernate.session.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Set;

/**
 * <p>Description: hibernate双向关联 </p>
 *
 * 总结:
 *    1、一般情况下，一对多，多的一方维护关系，效率比较高
 *    2、一对多，如果一的一方维护关系，实际上就是发出更新外键的update语句
 *    3、如果多的一方维护关系，实际上就是更新了student表的所有的字段
 *    4、一般情况下，Classes.hbm.xml文件中，针对set集合的invserse的值为true不维护关系
 *
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/7 下午2:43
 */
public class OneToManyDoubleTest extends HibernateUtils {

    /**
     * 在保存学生的时候,级联保存班级
     */
    @Test
    public void testSaveStudent_Cascade_SaveClasses(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student2 student = new Student2();
        student.setName("学生2");
        student.setDescription("学生2加载中");
        Classes2 classes = new Classes2();
        classes.setName("班级3");
        student.setClasses(classes);// 建立学生与班级之间的关系

        session.save(student);
        transaction.commit();
    }

    /**
     * 在更新学生的时候,保存班级
     */
    @Test
    public void testUpdateStudent_Cascade_SaveClasses(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student2 student = (Student2) session.get(Student2.class, 1L);
        Classes2 classes = new Classes2();
        classes.setName("班级2");
        student.setClasses(classes);// 建立学生与班级之间的关系

        transaction.commit();
    }

    /**
     * 已经存在一个班级,已经存在一个学生,让该学生加入到该班级
     */
    @Test
    public void testUpdateStudent_Cascade_UpdateClasses(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student2 student = (Student2) session.get(Student2.class, 1L);
        Classes2 classes = (Classes2) session.get(Classes2.class, 1L);
        student.setClasses(classes);// 建立学生与班级之间的关系
        transaction.commit();
    }

    /**
     * 将学生从一个班级转到另一个班级
     */
    @Test
    public void testTransform(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student2 student = (Student2) session.get(Student2.class, 2L);
        Classes2 classes = (Classes2) session.get(Classes2.class, 1L);
        student.setClasses(classes);
        transaction.commit();
    }

    /**
     * 解除一个班级和所有学生之间的关系 (不建议)
     */
    @Test
    public void testRemove(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes2 classes = (Classes2) session.get(Classes2.class, 1L);
        Set<Student2> students = classes.getStudents();
        for (Student2 student : students){
            student.setClasses(null);
        }
        transaction.commit();
    }
}
