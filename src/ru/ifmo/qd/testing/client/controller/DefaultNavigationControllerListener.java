package ru.ifmo.qd.testing.client.controller;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import ru.ifmo.qd.testing.client.view.LoginScreenWidget;
import ru.ifmo.qd.testing.client.view.RegisterScreenWidget;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 1:16:38
 * To change this template use File | Settings | File Templates.
 */
public class DefaultNavigationControllerListener implements NavigationControllerListener {
    public static final String REGISTER_TOKEN = "register";
    public static final String LOGON_TOKEN = "logon";

    private final String[] constArray = {REGISTER_TOKEN, LOGON_TOKEN};
    private RegisterScreenWidget registerWidget;
    private LoginScreenWidget loginWidget;

    public DefaultNavigationControllerListener() {
        registerWidget = new RegisterScreenWidget();
        loginWidget = new LoginScreenWidget();
    }

    public void onValueChange(ValueChangeEvent e) {
        String historyToken = History.getToken();
        RootPanel rootPanel = RootPanel.get();
        if (REGISTER_TOKEN.equals(historyToken)) {
            rootPanel.clear();
            registerWidget.clear();
            rootPanel.add(registerWidget);
        } else if (LOGON_TOKEN.equals(historyToken)) {
            rootPanel.clear();
            rootPanel.add(loginWidget);
        }
    }

    public static void register() {
        HistoryDispatcher.newItem(REGISTER_TOKEN);
    }

    public static void logon() {        
        HistoryDispatcher.newItem(LOGON_TOKEN);
    }

    public String[] getAvailableConstants() {
        return constArray;
    }
}
