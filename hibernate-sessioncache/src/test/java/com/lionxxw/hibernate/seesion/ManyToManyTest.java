package com.lionxxw.hibernate.seesion;

import com.lionxxw.hibernate.session.manytomany.Course;
import com.lionxxw.hibernate.session.manytomany.StudentM;
import com.lionxxw.hibernate.session.onetomany.single.Student;
import com.lionxxw.hibernate.session.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 多对多对应 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/7 下午5:41
 */
public class ManyToManyTest extends HibernateUtils{

    /**
     * 保存课程的时候,级联保存学生
     */
    @Test
    public void testSaveCourse_Cascade_SaveStudent(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Course course = new Course();
        course.setName("js课程");
        course.setDescription("html+css+js集成练习");
        StudentM student = new StudentM();
        student.setName("小刘");
        student.setDescription("一个帅气的小伙子");
        Set<StudentM> students = new HashSet<StudentM>();
        students.add(student);
        course.setStudents(students);
        session.save(course);
        transaction.commit();
    }

    /**
     * 在更新课程的时候,级联更新学生
     */
    @Test
    public void testUpdateCourse_Cascade_Update_Student(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Course course = (Course) session.get(Course.class, 1L);
        Set<StudentM> students = course.getStudents();
        for (StudentM student : students){
            student.setDescription("我在进步中....");
        }
        transaction.commit();
    }

    /**
     * 解除课程和所有的学生之间的关系
     */
    @Test
    public void testRealseAllR(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Course course = (Course) session.get(Course.class, 1L);
        course.setStudents(null);
        transaction.commit();
    }

    /**
     * 学生从一个课程转到另一个课程
     */
    @Test
    public void testStudentFromCourseToCourse(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        StudentM student = (StudentM) session.get(StudentM.class, 2L);
        Course course = (Course) session.get(Course.class, 2L);
        Course course1 = (Course) session.get(Course.class, 1L);
        student.getCourses().remove(course);// 解除2Lcourse课程
        student.getCourses().add(course1);// 关联1Lcourse课程
        transaction.commit();
    }

    /**
     * 删除一个学生
     */
    @Test
    public void testDeleteStudent(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        StudentM student = (StudentM) session.get(StudentM.class, 1L);
        session.delete(student);
        transaction.commit();
    }
}