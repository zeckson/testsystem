package ru.ifmo.qd.testing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.History;
import ru.ifmo.qd.testing.client.controller.AdminNavigationControllerListener;
import ru.ifmo.qd.testing.client.controller.HistoryDispatcher;
import ru.ifmo.qd.testing.client.controller.UserNavigationControllerListener;
import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.users.User;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 13:44:57
 * To change this template use File | Settings | File Templates.
 */
public class GWTEntryPoint implements EntryPoint {
    public void onModuleLoad() {        

        RootPanel.setVisible(RootPanel.get("01").getElement(), false);

        LoginService.App.getInstance().getLoggedInPerson(new AsyncCallback<Person>() {

            public void onFailure(Throwable caught) {
                HistoryDispatcher.changeNavigationControllerListener(HistoryDispatcher.DEFAULT_CONTROLLER);
                HistoryDispatcher.newItem("logon");
            }

            public void onSuccess(Person person) {
                if (person instanceof Admin) {
                    HistoryDispatcher.changeNavigationControllerListener(new AdminNavigationControllerListener(((Admin) person)));
                    HistoryDispatcher.newItem(History.getToken().equals("") ? "welcome" : History.getToken());
                } else if (person instanceof User) {
                    HistoryDispatcher.changeNavigationControllerListener(new UserNavigationControllerListener(person));
                    HistoryDispatcher.newItem(History.getToken().equals("") ? "welcome" : History.getToken());
                } else {
                    HistoryDispatcher.changeNavigationControllerListener(HistoryDispatcher.DEFAULT_CONTROLLER);
                    HistoryDispatcher.newItem(History.getToken().equals("") ? "logon" : History.getToken());
                }
            }
        });

//        RootPanel.get().onBrowserEvent();
    }
}
