package ru.ifmo.qd.testing.server.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.server.manager.StatisticsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.12.2007
 * Time: 9:42:30
 * To change this template use File | Settings | File Templates.
 */
public class HibernateStatisticsManager extends HibernateExecutable implements StatisticsManager<Session> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactoryForAuthorize();

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void saveStatistic(AnsweringStatistic statistic) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        AnsweringStatistic answeringStatistic = statistic;
        try {
            tx = session.beginTransaction();
            session.persist(answeringStatistic);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    public List getStatistic() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        List answeringStatistic = new ArrayList();
        try {
            tx = session.beginTransaction();
            answeringStatistic = (ArrayList) session.createCriteria(AnsweringStatistic.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }

        return answeringStatistic;
    }

    public List<AnsweringStatistic> getStatisticForQuestion(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        List<AnsweringStatistic> answeringStatistic = new ArrayList();
        try {
            tx = session.beginTransaction();
            answeringStatistic = (ArrayList) session.createCriteria(AnsweringStatistic.class).add(Expression.eq("questionID", Integer.valueOf(id))).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }

        return answeringStatistic;
    }
}
