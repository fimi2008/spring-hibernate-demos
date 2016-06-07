package com.lionxxw.hibernate.seesion;

import com.lionxxw.hibernate.session.onetomany.single.Classes;
import com.lionxxw.hibernate.session.onetomany.single.Student;
import com.lionxxw.hibernate.session.utils.HibernateUtils;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Description: 一对多的单向操作 </p>
 * 一般操作
 *  1、保存班级
 *  2、保存学生
 *  3、保存班级，保存学生
 *
 * 级联操作
 *  4、保存班级级联保存学生
 *  5、保存班级级联更新学生
 *  6、更新班级级联保存学生
 *  7、更新班级级联更新学生
 *  8、删除班级级联删除学生
 *  9、在班级有级联save-update的情况下，从关联得到学生，并且删除学生？
 *
 * 关系操作
 *  8、已经存在一个班级，新建一个学生，把该学生加入到该班级(建立关系操作)
 *  9、已经存在一个学生，新建一个班级，把该学生加入到该班级(建立关系操作)
 *  10、已经存在一个学生，已经存在一个班级，把该学生加入到该班级
 *  11、已经存在一个学生，把一个学生从一个班级转移到另外一个班级
 *  12、解除一个班级和所有学生之间的关系
 *  13、解除一个班级和所有的学生之间的关系,重新建立该班级和其他的学生之间的关系
 *
 * 级联和关系的混合：
 *  14、在删除班级的时候，解除班级和学生之间的关系
 *
 *
 * 总结:
 *      1、只能根据classes联系到student，但是通过student联系不到classes
 *      2、只能classes维护关系
 *      3、classes只要维护关系，就会发出维护关系的update语句
 *      4、一对多，用一的一方维护关系，效率比较低
 *      5、cascade与inverse的区别：
 *               cascade指的是对象与对象之间的关系
 *               inverse针对的是外键
 *
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/6 上午11:04
 */
public class OneToManySingleTest extends HibernateUtils {

    /**
     * 保存班级
     */
    @Test
    public void testSaveClasses(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = new Classes();
        classes.setName("测试班级");
        classes.setDescription("很牛");
        session.save(classes);
        transaction.commit();
    }

    /**
     * 保存学生
     */
    @Test
    public void testSaveStudent(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student();
        student.setName("小王");
        student.setDescription("一个小伙子");
        session.save(student);
        transaction.commit();
    }

    /**
     * 保存班级和学生
     */
    @Test
    public void testSaveClassesAndStudent(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = new Classes();
        classes.setName("测试2班");
        classes.setDescription("不错的班级");

        Student student = new Student();
        student.setName("小里");
        student.setDescription("小朋友一个");

        session.save(classes); // 持久化
        session.save(student); // 持久化
        transaction.commit();
    }

    /**
     * 级联
     * 在保存班级的同时级联保存学生
     * HQL实现步骤:
     * 1.保存班级
     * 2.保存学生
     * 3.更新学生表中cid字段
     */
    @Test
    public void testSaveClasses_Cascade_Student(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = new Classes();
        classes.setName("测试级联班");
        classes.setDescription("不错的班级");
        Student student = new Student();
        student.setName("级联小里");
        student.setDescription("小朋友一个");
        // 建立classes与student之间的关系
        Set<Student> students = new HashSet<Student>();
        students.add(student);
        classes.setStudents(students);

        session.save(classes);
        transaction.commit();
    }

    /**
     * 在保存班级的同时级联更新学生
     *
     * 说明：
     *    当事务提交的时候，执行了session.flush：
     *    1、检查session一级缓存中所有的持久化对象
     *    如果该对象在数据库中没有相应的记录，则发出insert语句
     *    如果该对象在数据库有相应的记录(有id值)，则和副本进行对比
     *    如果一样，则什么都不做，如果不一样，则发出update语句
     *    2、如果在Classes.hbm.xml文件中的set元素中有cascade选项，并且值为
     *    "save-update"，这个时候，hibernate内部再次会去检查classes的关联对象
     *    Set<Student>:students，在set集合中一个一个查找，如果该对象是临时状态
     *    的对象，则发出insert语句，如果是持久化状态的对象，则会对照副本
     *    来判断是否发出update语句
     *
     * HQl实现步骤:
     * 1.查询学生
     * 2.保存班级
     * 3.更新学生信息
     * 4.更新学生表中cid字段
     */
    @Test
    public void testSaveClasses_Cascade_Update_Student(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = new Classes();
        classes.setName("级联保存班级更新学生班");
        classes.setDescription("测试级联保存班级更新学生");
        Student student = (Student) session.get(Student.class, 1L);
        student.setDescription("来自级联保存班级更新学生");

        //建立classes与student之间的关系
        Set<Student> students = new HashSet<Student>();
        students.add(student);
        classes.setStudents(students);

        session.save(classes);
        transaction.commit();
    }

    /**
     * 在更新班级的时候级联更新学生
     */
    @Test
    public void testUpdateClasses_Cascade_Update_Student(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
       /* Classes classes = (Classes) session.get(Classes.class, 4L);
        classes.setName("更新班级级联更新学生");
        classes.setDescription("测试更新班级级联更新学生");
        Student student = (Student) session.get(Student.class, 2L);
        student.setDescription("来自更新班级级联更新学生操作");

        Set<Student> students = new HashSet<Student>();
        students.add(student);
        classes.setStudents(students);

        session.update(classes);*/
        Classes classes = (Classes)session.get(Classes.class, 1L);
        Set<Student> students = classes.getStudents();
        classes.setDescription("asfd");
        for (Student student : students) {
            student.setDescription("asfd");
        }
        transaction.commit();
    }

    /**
     * 删除某一个班级中的某一个学生
     *   把cid为1的班级中的sid为4的学生删除掉
     */
    @Test
    public void testDeleteStudent_FromClasses(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = (Classes)session.get(Classes.class, 1L);
        Set<Student> students = classes.getStudents();
        classes.setStudents(null);//在students集合提取出来以后，解除关系(不然会报错remove deleted object from associations)
        for (Student student : students) {
            if(student.getSid().longValue()==4){
                session.delete(student);
            }
        }
        transaction.commit();
    }

    /**
     * 已经存在一个班级，新建一个学生，让该学生加入到该班级
     *  cascade="save-update"
     *    是否对student进行插入操作
     *  inverse="false"
     *     决定classes是否更新student表中的相应的外键
     */
    @Test
    public void testUpdateClasses_Cascade_Save_Student_BuildR(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = (Classes)session.get(Classes.class, 1L);
        Student student = new Student();
        student.setName("王二麻子");

        //建立关系
        /**
         * hibernate内部会检查classes的关联对象students
         * 发现集合中多了一个对象，而且该对象是一个临时状态的对象，
         * 所以要发出insert语句
         */
        classes.getStudents().add(student);
        transaction.commit();
    }

    /**
     * 已经存在一个学生，新建一个班级，把该学生加入到该班级(建立关系操作)
     * Hibernate:
     *        update
     *        student
     *        set
     *        cid=?
     *        where
     *        sid=?
     *        发出维护关系的update
     *
     * 前提: Classes.hbm.xml 中students 配置 inverse="false"
     **/
    @Test
    public void testSaveClasses_BuildR(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, 1L);
        Classes classes = new Classes();
        classes.setName("新建一个班级");
        classes.setDescription("已经存在一个学生，新建一个班级，把该学生加入到该班级(建立关系操作)");
        Set<Student> students = new HashSet<Student>();
        students.add(student);
        classes.setStudents(students);
        session.save(classes);
        transaction.commit();
    }

    /**
     * 已经存在一个学生，已经存在一个班级，把该学生加入到该班级
     *
     * 前提: Classes.hbm.xml 中students 配置 inverse="false"
     */
    @Test
    public void testBuildR(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, 1L);
        Classes classes = (Classes) session.get(Classes.class, 1L);
        classes.getStudents().add(student);
        transaction.commit();
    }

    /**
     * 已经存在一个学生，把一个学生从一个班级转移到另外一个班级
     */
    @Test
    public void testTransform(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, 3L);
        Classes classes = (Classes) session.get(Classes.class, 1L);
        classes.getStudents().add(student);
        transaction.commit();
    }

    /**
     * 解除一个班级和所有学生之间的关系
     * Hibernate:
     *        update
     *        student
     *        set
     *        cid=null
     *        where
     *        cid=?
     */
    @Test
    public void testRemove(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = (Classes) session.get(Classes.class, 4L);
        classes.setStudents(null);
        transaction.commit();
    }

    /**
     * 解除一个班级和所有的学生之间的关系,重新建立该班级和其他的学生之间的关系
     */
    @Test
    public void testResetClasses(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = (Classes) session.get(Classes.class, 1L);
        Student student = (Student) session.get(Student.class, 2L);
        Set<Student> students = new HashSet<Student>();
        students.add(student);
        classes.setStudents(students);
        transaction.commit();
    }

    /**
     * 在删除班级的时候，解除班级和学生之间的关系
     */
    @Test
    public void testDeleteClasses(){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Classes classes = (Classes) session.get(Classes.class, 1L);
        session.delete(classes);
        transaction.commit();
    }
}
