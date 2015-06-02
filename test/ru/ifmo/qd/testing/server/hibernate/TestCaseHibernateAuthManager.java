package ru.ifmo.qd.testing.server.hibernate;

import org.hibernate.SessionFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 22.01.11
 * Time: 21:04
 * To change this template use File | Settings | File Templates.
 */
public class TestCaseHibernateAuthManager extends HibernateAuthorizationManager {
    @Override
    protected SessionFactory getSessionFactory() {
        return TestHibernateUtil.getSessionFactoryForTests();
    }
}
