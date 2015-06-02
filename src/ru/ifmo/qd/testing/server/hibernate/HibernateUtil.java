package ru.ifmo.qd.testing.server.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: ZecksonUser
 * Date: 21.03.2007
 * Time: 0:06:09
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactoryForTests;

    static {
        try {

            sessionFactoryForTests = new AnnotationConfiguration().configure("Testing_hibernate.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactoryForTests() {
        return sessionFactoryForTests;
    }

    private static final SessionFactory sessionFactoryForAuthorize;

    static {
        try {

            sessionFactoryForAuthorize = new AnnotationConfiguration().configure("Authorizing_hibernate.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactoryForAuthorize() {
        return sessionFactoryForAuthorize;
    }
}
