package ru.ifmo.qd.testing.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.ifmo.qd.testing.client.model.users.Person;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 31.03.2008
 * Time: 23:12:24
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService {
    Person getPerson(String login, String password);

    Person getLoggedInPerson();

    void createPerson(String text, String text1, String text2, String text3, String text4);

    List<Person> getUsersList();

    void logoff();

    /**
     * Utility/Convenience class.
     * Use TestService.App.getInstance() to access static instance of GWTRemoteServiceAsync
     */
    public static class App {
        private static LoginServiceAsync ourInstance = null;

        public static synchronized LoginServiceAsync getInstance() {
            if (ourInstance == null) {
                ourInstance = (LoginServiceAsync) GWT.create(LoginService.class);
            }
            return ourInstance;
        }
    }
}
