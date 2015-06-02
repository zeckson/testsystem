package ru.ifmo.qd.testing;

import junit.framework.TestCase;
import org.hibernate.Session;
import ru.ifmo.qd.testing.client.model.tests.*;
import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.server.hibernate.HibernateAuthorizationManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateTestManager;
import ru.ifmo.qd.testing.server.manager.ICommand;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 14:47:17
 * To change this template use File | Settings | File Templates.
 */
public class MappingsTest extends TestCase {

    Subject subject = new Subject();

    /* public void testOpenQuestion() {
        Test myTest;
        TestManager manager = new TestManager();

        myTest = manager.createSubject("My first test");

        for (int i = 0; i < 10; i++) {
            manager.addOpenQuestion(myTest, "question num: " + i, "How many " + i + " * " + i, String.valueOf(i * i), 0, 0);
        }

        Test myTest2;
        TestManager newTestsManager = new TestManager();
        myTest2 = newTestsManager.getTest(myTest.getId());

        assertEquals(myTest.getName(), myTest2.getName());
        assertTrue(assertEquals(myTest, myTest2));

//        newTestsManager.removeQuestion(myTest2, (Question) myTest2.getQuestionsList().get(3));
//        newTestsManager.removeQuestion(myTest, (OpenQuestion) myTest.getQuestionsList().get(3));

        assertTrue(assertEquals(myTest, myTest2));


    }

    public void testClosedQuestion() {
        Test test;
        TestManager manager = new TestManager();

        test = manager.createSubject("My Closed Test");
        for (int i = 0; i < 10; i++) {
            ClosedQuestion closedQuestion = manager.addClosedQuestion(test, "question num: " + i, "How many " + i + " * " + i, 0, 0);
            for (int j = 0; j < 4; j++) {
                manager.addAnswerToClosedQuestion(closedQuestion, String.valueOf(i * i * j));
            }

        }

        Test myTest2;
        myTest2 = manager.getTest(test.getId());

        assertEquals(test.getName(), myTest2.getName());
        assertTrue(assertEquals(test, myTest2));
        manager.removeAnswer((ClosedQuestion) test.getQuestionsList().get(2), (ClosedAnswer) ((ClosedQuestion) test.getQuestionsList().get(2)).getAnswersSet().iterator().next());

        assertTrue(assertEquals(test, myTest2));

    }*/

    public void testAdminManage() {

        (new HibernateAuthorizationManager()).addAdmin("zeckson", "ifmo.ru", null, null, null);
        (new HibernateAuthorizationManager()).executeCommand(new ICommand<Session>() {
            public Object execute(Session sessionManager) {
                Admin admin = new Admin();
                admin.setName("zeckson");
                admin.setSurename(null);
                admin.setInfo(null);
                admin.setLoginName("zeckson");
                admin.setPassword("ifmo.ru");
                admin.setAssociatedSubjects(new String());
                sessionManager.persist(admin);
                return null;
            }
        });

    }

    public void testCreateSubject() {
        new HibernateTestManager().createSubject("subj", "subj", "subj");
    }

    /* public void testRemoveSubject(){

            new TestManager().removeSubject();
        }
    */

    /*  boolean assertEquals(Test first, Test second) {
            int i = 0;
            for (Iterator iterator = first.getQuestionsList().iterator(); iterator.hasNext();) {
                Question openQuestion = (Question) iterator.next();
                for (Iterator iterator1 = second.getQuestionsList().iterator(); iterator1.hasNext();) {
                    Question question = (Question) iterator1.next();
                    if (question.getName().equalsIgnoreCase(openQuestion.getName())) {
                        i++;
                        break;
                    }
                }
            }
            if (i == first.getQuestionsList().size()) return true;
            return false;
        }
    */

}
