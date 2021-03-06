package com.lionxxw.hibernate.twolevelcache.hibernate.session.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	public static SessionFactory sessionFactory;
	static{
		/**
		 * 加载了hibernate的配置文件
		 */
		Configuration configuration = new Configuration();
		/**
		 * 要求配置文件：
		 *     1、必须是classpath的根目录
		 *     2、文件的名称必须是hibernate.cfg.xml
		 */
		configuration.configure();
		//configuration.configure("");//参数为hibernate配置文件的路径及名称
		sessionFactory = configuration.buildSessionFactory();
	}
}
