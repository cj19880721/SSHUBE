package com.sshube.hibernate.test.impl;

import org.hibernate.Session;

import com.sshube.hibernate.test.itf.HibernateTestItf;
import com.sshube.hibernate.util.HibernateSessionFactory;
import com.sshube.spring.bean.User;

public class HibernateTestImpl implements HibernateTestItf {

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactory.getSession();
        try {
            // 手动事务
            session.getTransaction().begin();
            session.save(user);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.err.print(e);
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new RuntimeException();
        }
        finally {
            HibernateSessionFactory.closeSession();
        }
    }
}
