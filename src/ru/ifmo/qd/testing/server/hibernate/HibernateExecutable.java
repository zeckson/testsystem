package ru.ifmo.qd.testing.server.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.ifmo.qd.testing.server.manager.Executable;
import ru.ifmo.qd.testing.server.manager.ICommand;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 04.04.11
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */
public abstract class HibernateExecutable implements Executable<Session> {

    abstract protected SessionFactory getSessionFactory();

    public Object executeCommand(ICommand<Session> command) {
        final Session session = getSessionFactory().openSession();
        Transaction tx = null;
        final Object res;

        try {
            tx = session.beginTransaction();
            res = command.execute(session);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        return res;
    }
}
