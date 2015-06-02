package ru.ifmo.qd.testing.server.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.users.User;
import ru.ifmo.qd.testing.server.manager.AuthorizationManager;
import ru.ifmo.qd.testing.server.manager.ICommand;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 22:14:45
 * To change this template use File | Settings | File Templates.
 */
public class JPAAuthorizationManager extends JPAExecutable implements AuthorizationManager<EntityManager> {
    private final static Log LOG = LogFactory.getLog(AuthorizationManager.class);

    private EntityManagerFactory sessionFactory = EMF.get();

    protected EntityManagerFactory getSessionFactory() {
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

        EntityManager manager = sessionFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            if (LOG.isInfoEnabled())
                LOG.info("Creating admin: " + admin.getName());
            tx.begin();
            manager.persist(admin);
            tx.commit();
            if (LOG.isInfoEnabled())
                LOG.info("Created admin: " + admin.getName());
        } catch (RuntimeException ex) {
            if (LOG.isErrorEnabled())
                LOG.error("Failed create admin: " + admin.getName(), ex);
            admin = null;
            throw ex;
        } finally {
            if (tx.isActive()) tx.rollback();
            manager.close();
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
        user.setAssociatedSubjects("");
        user.setGroup("null");
        user.setTutor("null");

        EntityManager manager = sessionFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            manager.persist(user);
            tx.commit();
        } catch (RuntimeException e) {
            user = null;
            throw e;
        } finally {
            if (tx.isActive()) tx.rollback();
            manager.close();
        }

        return user;
    }

    public Person getPerson(String login, String password) {
        EntityManager manager = sessionFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        Person person = null;

        try {
            tx.begin();
            Query query = manager.createQuery("select it from Person it where it.loginName = :login");
            query.setParameter("login", login);
            person = (Person) query.getSingleResult();
            //TODO: modify it with hashes
            if (!(person.getPassword().equals(password))) {
                throw new SecurityException("Wrong password for user.");
            }
            tx.commit();
        } finally {
            if (tx.isActive()) tx.rollback();
            manager.close();
        }

        return person;
    }

    public void removePerson(int personId) {
        EntityManager manager = sessionFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            manager.remove(manager.find(Person.class, personId));
            tx.commit();
        } finally {
            if (tx.isActive()) tx.rollback();
            manager.close();
        }
    }

    public List<Person> getUsers() {
        EntityManager manager = sessionFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        List<Person> users = null;

        try {
            tx.begin();
            users = manager.createQuery("from User it").getResultList();
            tx.commit();
        } finally {
            if (tx.isActive()) tx.rollback();
            manager.close();
        }

        return users;
    }

    public User getUser(int userID) {
        EntityManager manager = sessionFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        User user = null;

        try {
            tx.begin();
            user = manager.find(User.class, userID);
            tx.commit();
        } finally {
            if (tx.isActive()) tx.rollback();
            manager.close();
        }

        return user;
    }

}