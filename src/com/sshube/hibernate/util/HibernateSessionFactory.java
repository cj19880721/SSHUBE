package com.sshube.hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class HibernateSessionFactory {
    // sessionFactory���õ����̳߳�
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    // sessionFactory ����session�Ĺ���
    private static org.hibernate.SessionFactory sessionFactory;

    private static Configuration configuration = new Configuration();

    private static ServiceRegistry serviceRegistry;

    // �����ʱ��ʼ��sessionFactory
    static {
        try {
            HibernateSessionFactory.configuration.configure();
            HibernateSessionFactory.serviceRegistry =
                    new ServiceRegistryBuilder().applySettings(HibernateSessionFactory.configuration.getProperties())
                            .buildServiceRegistry();
            // Hibernate4�д���sessionFactory�ķ���
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
        // ���̳߳�Ϊ�գ���session��ʧ��
        if (null == session || !session.isOpen()) {
            if (null == HibernateSessionFactory.sessionFactory) {
                // ����sessionFactory�ǿգ������´���
                HibernateSessionFactory.rebuildSessionFactory();
            }
            session =
                    null != HibernateSessionFactory.sessionFactory ? HibernateSessionFactory.sessionFactory
                            .openSession() : null;
            // ��session�ŵ��̳߳��У��´�����
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
            // Hibernate4�д���sessionFactory�ķ���
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
     * �ṩһ�������ӿ���������������sessionFactory
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
