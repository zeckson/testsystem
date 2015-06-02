package ru.ifmo.qd.testing.server.jpa;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.ifmo.qd.testing.server.manager.AuthorizationManager;
import ru.ifmo.qd.testing.server.manager.Executable;
import ru.ifmo.qd.testing.server.manager.ICommand;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 04.04.11
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public abstract class JPAExecutable implements Executable<EntityManager> {

    private final static Log LOG = LogFactory.getLog(JPAExecutable.class);

    abstract protected EntityManagerFactory getSessionFactory();

    public Object executeCommand(ICommand<EntityManager> command) {
        final EntityManager manager = getSessionFactory().createEntityManager();
        final EntityTransaction tx = manager.getTransaction();
        final Object result;

        try {
            if (LOG.isTraceEnabled()) {
                LOG.info("Start processing command");
            }
            tx.begin();
            result = command.execute(manager);
            tx.commit();
            if (LOG.isTraceEnabled()) {
                LOG.info("End processing command");
            }
        } catch (RuntimeException ex) {
            if (LOG.isErrorEnabled())
                LOG.error("Failed process command: " + command.toString(), ex);
            throw ex;
        } finally {
            if (tx.isActive()) tx.rollback();
            manager.close();
        }

        return result;
    }
}
