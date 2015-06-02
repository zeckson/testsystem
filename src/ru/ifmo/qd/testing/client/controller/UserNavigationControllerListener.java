package ru.ifmo.qd.testing.client.controller;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.gwt.core.client.GWT;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.view.user.WelcomeWidget;
import ru.ifmo.qd.testing.client.view.user.QuestionWidget;
import ru.ifmo.qd.testing.client.view.user.EndTestScreenWidget;
import ru.ifmo.qd.testing.client.UserService;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;


/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 1:09:46
 * To change this template use File | Settings | File Templates.
 */
public class UserNavigationControllerListener implements NavigationControllerListener {
    private Person person;
    static final String WELCOME_TOKEN = "welcome";
    static final String START_TOKEN = "start_test=";

    private static final String END_TOKEN = "endTest";
    private static final String[] AVAILABLE_TOKENS = {WELCOME_TOKEN, START_TOKEN, END_TOKEN
    };
    private WelcomeWidget welcomeWidget;
    private QuestionWidget questionWidget;
    private EndTestScreenWidget endTestWidget;

    public UserNavigationControllerListener(Person person) {
        this.person = person;
        welcomeWidget = new WelcomeWidget();
        questionWidget = new QuestionWidget();
        endTestWidget = new EndTestScreenWidget();
    }

    public void onValueChange(ValueChangeEvent e) {
        String historyToken = History.getToken();
        RootPanel rootPanel = RootPanel.get();
        if (WELCOME_TOKEN.equals(historyToken)) {
            rootPanel.clear();
            rootPanel.add(welcomeWidget);
        } else if (historyToken.startsWith(START_TOKEN)) {
            int id = Integer.parseInt(historyToken.substring(START_TOKEN.length()));
            rootPanel.clear();
            startTest(id);
            rootPanel.add(questionWidget);
        } else if (historyToken.equals(END_TOKEN)) {
            rootPanel.clear();
            rootPanel.add(endTestWidget);
        }
    }

    private void startTest(int id) {
        UserService.App.getInstance().startTest(id, new AsyncCallback<Question>(){

            public void onFailure(Throwable caught) {
               AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);
                 Window.alert(alertConstants.loadTestFailed());
            }

            public void onSuccess(Question result) {
                questionWidget.setQuestion((Question) result);
            }
        });
    }


    public String[] getAvailableConstants() {
        return AVAILABLE_TOKENS;
    }
}
