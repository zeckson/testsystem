package ru.ifmo.qd.testing.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.ifmo.qd.testing.client.model.users.Person;

public interface LoginServiceAsync {

    void getPerson(String login, String password, AsyncCallback<Person> async);

    void getLoggedInPerson(AsyncCallback<Person> async);

    void createPerson(String text, String text1, String text2, String text3, String text4, AsyncCallback async);

    void getUsersList(AsyncCallback async);

    void logoff(AsyncCallback async);
}
