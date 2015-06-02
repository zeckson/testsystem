package ru.ifmo.qd.testing.server.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.users.User;
import ru.ifmo.qd.testing.server.manager.AuthorizationManager;
import ru.ifmo.qd.testing.server.manager.ICommand;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 22:14:45
 * To change this template use File | Settings | File Templates.
 */
public class HibernateAuthorizationManager extends HibernateExecutable implements AuthorizationManager<Session> {
    private static Log LOG = LogFactory.getLog(AuthorizationManager.class);

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactoryForAuthorize();

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Admin addAdmin(String loginName, String password, String name, String surename,
                          String info) {

        Admin admin = new Admin();
        admin.setName(name);
        admin.setSurename(surename);
        admin.setInfo(info);
        admin.setLoginName(loginName);
        admin.setPassword(password);
        admin.setAssociatedSubjects(new String());

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            if (LOG.isInfoEnabled())
                LOG.info("Creating admin: " + admin.getName());
            tx = session.beginTransaction();
            session.persist(admin);
            tx.commit();
            if (LOG.isInfoEnabled())
                LOG.info("Created admin: " + admin.getName());
        } catch (HibernateException he) {
            if (LOG.isErrorEnabled())
                LOG.error("Failed create admin: " + admin.getName(), he);
            if (tx != null) tx.rollback();
            admin = null;
            throw he;
        } finally {
            session.close();
        }

        return admin;
    }

    public User addUser(String loginName, String password, String name, String surename,
                        String info) {

        User user = new User();
        user.setName(name);
        user.setSurename(surename);
        user.setInfo(info);
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setAssociatedSubjects(new String());
        user.setGroup("null");
        user.setTutor("null");


        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            user = null;
            throw he;
        } finally {
            session.close();
        }

        return user;
    }

    public Person getPerson(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Person person = null;

        try {
            if (LOG.isInfoEnabled())
                LOG.info("Requesting user: " + login);
            tx = session.beginTransaction();
            person = (Person) session.createCriteria(Person.class).add(Restrictions.like("loginName", login)).add(Restrictions.like("password", password)).uniqueResult();
            tx.commit();
            if (LOG.isInfoEnabled())
                LOG.info("User fetched: " + login);
        } catch (HibernateException he) {
            if (LOG.isErrorEnabled())
                LOG.error("Request user: " + login + "failed", he);
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return person;
    }

    public void removePerson(int personId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(session.get(Person.class, personId));
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return;


    }

    public List<Person> getUsers() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Person> users = null;

        try {
            tx = session.beginTransaction();
            users = session.createCriteria(User.class).list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return users;
    }

    public User getUser(int userID) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        User user = null;

        try {
            tx = session.beginTransaction();
            user = (User) session.createCriteria(User.class).add(Expression.eq("id", userID)).uniqueResult();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return user;
    }

}
