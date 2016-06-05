package com.lionxxw.hibernate.seesion;


import com.lionxxw.hibernate.session.domain.Person;
import com.lionxxw.hibernate.session.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * <p>Description: session的缓存(一级缓存) </p>
 *
 * @author wangxiang
 * @date 16/6/5 下午10:10
 * @version 1.0
 */
public class SessionCacheTest extends HibernateUtils {

	@Test
	public void testGet(){
		Session session = sessionFactory.openSession();
		Person person = (Person) session.get(Person.class, 1L);// 发出sql语句
		person = (Person) session.get(Person.class, 1L); // 没有打印sql语句,该对象来自于缓存
		session.close();
	}

	/**
	 * <p>Description: 统计机制 </p>
	 *
	 * @author wangxiang
	 * @date 16/6/5 下午10:59
	 * @version 1.0
	 */
	@Test
	public void testStatistics(){
		Session session = sessionFactory.openSession();
		Person person = (Person) session.get(Person.class, 1L);// 发出sql语句
		System.out.println(session.getStatistics().getEntityCount());//计算hibernate的一级缓存中的对象的个数
		session.close();
	}

	/**
	 * <p>Description: save方法 </p>
	 * 		在底层save或者update操作执行的是一个方法,会根据是不是临时对象转化过来的判断执行update方法还是save方法
	 * @param
	 * @return
	 * @author wangxiang
	 * @date 16/6/5 下午11:08
	 * @version 1.0
	 */
	@Test
	public void testSave(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = new Person();
		person.setName("hello");
		session.save(person);// session.save方法把对象放入到了一级缓存中
		System.out.println(session.getStatistics().getEntityCount());//输出的为1
		transaction.commit();
		session.close();
	}
}
