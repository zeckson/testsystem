package ru.ifmo.qd.testing.server.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 04.10.2010
 * Time: 1:46:40
 * To change this template use File | Settings | File Templates.
 */
public class EMF {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("AutorityDb");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}
