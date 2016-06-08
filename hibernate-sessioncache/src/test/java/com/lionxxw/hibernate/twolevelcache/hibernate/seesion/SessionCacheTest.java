package com.lionxxw.hibernate.twolevelcache.hibernate.seesion;


import com.lionxxw.hibernate.twolevelcache.hibernate.session.domain.Person;
import com.lionxxw.hibernate.twolevelcache.hibernate.session.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * <p>Description: session的缓存(一级缓存) </p>
 * 1、一级缓存的生命周期和session的生命周期是一致的
 * 2、当执行session.close的时候，一级缓存就消失了
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

	/**		
	 * <p>Description: 对于持久化对象进行set操作,commit时会update于数据库中 </p>
	 *
	 * <class name="com.lionxxw.hibernate.session.domain.Person" table="person" dynamic-update="true">
	 * 实体配置文件中 添加dynamic-update="true" 即可实现,更新指定修改字段
	 *
	 * 也通过动态生成HQL语句
	 *
	 * 基于springframework中BeanUtils中copyProperties实现忽略指定不更新字段
	 *
	 * @author wangxiang
	 * @date 16/6/6 上午9:53
	 * @version 1.0
	 */
	@Test
	public void testUpdate(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = (Person) session.get(Person.class, 1L);
		person.setName("我爱我家22");
		transaction.commit();
		session.close();
	}

	/**		
	 * <p>Description: evict 将指定缓冲对象清楚 </p>
	 * 
	 * @author wangxiang
	 * @date 16/6/6 上午9:51
	 * @version 1.0
	 */
	@Test
	public void testEvict(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = (Person) session.get(Person.class, 1L);
		System.out.println(session.getStatistics().getEntityCount());// 1
		session.evict(person);//会把指定的缓冲对象进行清除
		System.out.println(session.getStatistics().getEntityCount());// 0
		transaction.commit();
		session.close();
	}

	/**
	 * <p>Description: clear 强制清除Session缓存</p>
	 *
	 * @author wangxiang
	 * @date 16/6/6 上午10:32
	 * @version 1.0
	 */
	@Test
	public void testClear(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Person person = (Person) session.get(Person.class, 1L);
		Person person2 = (Person) session.get(Person.class, 2L);
		System.out.println(session.getStatistics().getEntityCount());//2
		session.clear();
		System.out.println(session.getStatistics().getEntityCount());//0
		transaction.commit();
		session.close();
	}
}