package ru.ifmo.qd.testing.client;

import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

import java.util.List;

import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.model.tests.Subject;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.04.2008
 * Time: 1:54:23
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath("UserService")
public interface UserService extends RemoteService {
    List<Subject> getAvalaibleTests();

    Question startTest(int id);

    Question next(AnsweringStatistic statistic);
    /**
     * Utility/Convenience class.
     * Use UserService.App.getInstance() to access static instance of UserServiceAsync
     */
    public static class App {
        private static UserServiceAsync ourInstance = null;

        public static synchronized UserServiceAsync getInstance() {
            if (ourInstance == null) {
                ourInstance = (UserServiceAsync) GWT.create(UserService.class);
            }
            return ourInstance;
        }
    }
}
