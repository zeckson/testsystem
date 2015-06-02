package ru.ifmo.qd.testing.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.ifmo.qd.testing.client.UserService;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.model.tests.Subject;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.users.User;
import ru.ifmo.qd.testing.server.manager.TestManager;
import ru.ifmo.qd.testing.server.model.TestGenerator;
import ru.ifmo.qd.testing.server.model.AdvancedTest;
import ru.ifmo.qd.testing.server.hibernate.HibernateStatisticsManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateTestManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.04.2008
 * Time: 1:54:23
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl extends RemoteServiceServlet implements UserService {
    private static final String LOGGED_IN_PERSON = "loggedInPerson";
    private static final String STARTED_TEST = "startedTest";
    private static final String TEST_TYPE = "adoptive";

    public List<Subject> getAvalaibleTests() {
        User user = (User) getThreadLocalRequest().getSession().getAttribute(LOGGED_IN_PERSON);
        return getSubjectsList();  //TODO Change behavior!
    }

    public Question startTest(int id) {
        Subject testSubject = null;
        TestManager manager = new HibernateTestManager();
        for (Iterator iterator = manager.getSubjects().iterator(); iterator.hasNext();) {
            Subject subject = (Subject) iterator.next();
            if (subject.getId() == id) {
                testSubject = subject;
                break;
            }
        }
        try {
//            BasicTest basicTest = new BasicTest(testSubject);
            AdvancedTest advancedTest = new AdvancedTest(testSubject);
            getThreadLocalRequest().getSession().setAttribute(STARTED_TEST, advancedTest);
            return advancedTest.prev();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Question next(AnsweringStatistic statistic) {
        Question next = null;
//        Person person = (Person) getThreadLocalRequest().getSession().getAttribute(LOGGED_IN_PERSON);

        statistic.setPersonID(((Person) getThreadLocalRequest().getSession().getAttribute(LOGGED_IN_PERSON)).getId());
        TestGenerator generator = (TestGenerator) getThreadLocalRequest().getSession().getAttribute(STARTED_TEST);
        if (generator.hasNext()) {            
            next = generator.next(statistic.getScore());
        }
        getThreadLocalRequest().getSession().setAttribute(STARTED_TEST, generator);
        new HibernateStatisticsManager().saveStatistic(statistic);
        return next;
    }

    public List getSubjectsList() {
        TestManager manager = new HibernateTestManager();
        List list = manager.getSubjects();
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Subject subject = (Subject) iterator.next();
            subject.setBlocks(null);
        }
        return list;
    }

    public double getCurrentScores() {
        TestGenerator generator;
        if((generator = (TestGenerator) getThreadLocalRequest().getSession().getAttribute(STARTED_TEST))!= null){
            return generator.getScores();
        }
        return 0;
    }

}