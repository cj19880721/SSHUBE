package com.sshube.hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class HibernateSessionFactory {
    // sessionFactory中用到的线程池
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    // sessionFactory 创建session的工厂
    private static org.hibernate.SessionFactory sessionFactory;

    private static Configuration configuration = new Configuration();

    private static ServiceRegistry serviceRegistry;

    // 类加载时初始化sessionFactory
    static {
        try {
            HibernateSessionFactory.configuration.configure();
            HibernateSessionFactory.serviceRegistry =
                    new ServiceRegistryBuilder().applySettings(HibernateSessionFactory.configuration.getProperties())
                            .buildServiceRegistry();
            // Hibernate4中创建sessionFactory的方法
            HibernateSessionFactory.sessionFactory =
                    HibernateSessionFactory.configuration.buildSessionFactory(HibernateSessionFactory.serviceRegistry);
        }
        catch (Exception e) {
            System.err.print(e);
            e.printStackTrace();
        }
    }

    private HibernateSessionFactory() {
    }

    public static Session getSession() throws HibernateException {
        Session session = HibernateSessionFactory.threadLocal.get();
        // 若线程池为空，或session打开失败
        if (null == session || !session.isOpen()) {
            if (null == HibernateSessionFactory.sessionFactory) {
                // 若干sessionFactory是空，则重新创建
                HibernateSessionFactory.rebuildSessionFactory();
            }
            session =
                    null != HibernateSessionFactory.sessionFactory ? HibernateSessionFactory.sessionFactory
                            .openSession() : null;
            // 把session放到线程池中，下次再拿
            HibernateSessionFactory.threadLocal.set(session);
        }

        return session;
    }

    public static void rebuildSessionFactory() {
        try {
            HibernateSessionFactory.configuration.configure();
            HibernateSessionFactory.serviceRegistry =
                    new ServiceRegistryBuilder().applySettings(HibernateSessionFactory.configuration.getProperties())
                            .buildServiceRegistry();
            // Hibernate4中创建sessionFactory的方法
            HibernateSessionFactory.sessionFactory =
                    HibernateSessionFactory.configuration.buildSessionFactory(HibernateSessionFactory.serviceRegistry);
        }
        catch (Exception e) {
            System.err.print(e);
            e.printStackTrace();
        }
    }

    public static void closeSession() throws HibernateException {
        Session session = HibernateSessionFactory.threadLocal.get();
        HibernateSessionFactory.threadLocal.set(null);
        if (null != session) {
            session.close();
        }
    }

    /**
     * 提供一个公共接口让外界获得这个单例sessionFactory
     * 
     * @return
     */
    public static org.hibernate.SessionFactory getSessionFactory() {
        return HibernateSessionFactory.sessionFactory;
    }

    public static Configuration getConfiguration() {
        return HibernateSessionFactory.configuration;
    }
}
