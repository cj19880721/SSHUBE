package com.sshube.it.service.impl;

import org.hibernate.Session;

import com.sshube.hibernate.util.HibernateSessionFactory;
import com.sshube.it.service.itf.UserADUIItf;
import com.sshube.spring.bean.User;

public class UserADUImpl implements UserADUIItf {
    private org.hibernate.SessionFactory sessionFactory;

    public org.hibernate.SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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

    private Session getSession() {
        if (this.sessionFactory != null) {
            return this.sessionFactory.getCurrentSession();
        }
        return null;
    }

    @Override
    public void addUserVO(User user) {
        Session session = this.getSession();
        if (null != session) {
            session.save(user);
        }
    }

    @Override
    public void delUserVO(User user) {
        Session session = this.getSession();
        if (null != session) {
            session.delete(user);
        }
    }

    @Override
    public void updateUserVO(User user) {
        Session session = this.getSession();
        if (null != session) {
            session.update(user);
        }
    }
}
