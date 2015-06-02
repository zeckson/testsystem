package ru.ifmo.qd.testing.client.controller;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.12.2007
 * Time: 0:35:48
 * To change this template use File | Settings | File Templates.
 */
public class HistoryDispatcher {
    private static List tokens = new ArrayList();

    public static final DefaultNavigationControllerListener DEFAULT_CONTROLLER = new DefaultNavigationControllerListener();
    private static HandlerRegistration currentController;

    public static void changeNavigationControllerListener(NavigationControllerListener controllerListener) {
        if (currentController != null) {
            currentController.removeHandler();
        }
        currentController = History.addValueChangeHandler(controllerListener);
    }

    public static void newItem(String historyToken) {
        History.newItem(historyToken);
    }
}
