package ru.ifmo.qd.testing.server.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 04.04.11
 * Time: 11:30
 * To change this template use File | Settings | File Templates.
 */
public class JPAAuthManager extends JPAAuthorizationManager{

    @Override
    protected EntityManagerFactory getSessionFactory() {
        return TestJPAUtil.get();
    }
}
