package ru.ifmo.qd.testing.server.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 08.05.11
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public class TestJPAUtil {

    private static final EntityManagerFactory emfInstance =
            Persistence.createEntityManagerFactory("AutorityDb");

    private TestJPAUtil() {
    }

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
