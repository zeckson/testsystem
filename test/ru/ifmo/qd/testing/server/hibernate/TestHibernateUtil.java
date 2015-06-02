package ru.ifmo.qd.testing.server.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 22.01.11
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class TestHibernateUtil {
    private static final SessionFactory sessionFactoryForTests;

        static {
            try {

                sessionFactoryForTests = new AnnotationConfiguration().configure("database_hibernate.xml")
                        .buildSessionFactory();
            } catch (Throwable ex) {

                System.err.println("Initial SessionFactory creation failed. " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactoryForTests() {
            return sessionFactoryForTests;
        }

}
