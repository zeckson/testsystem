package ru.ifmo.qd.testing.client.controller;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.HistoryListener;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 0:10:01
 * To change this template use File | Settings | File Templates.
 */

//TODO: now it's buggy after upgrade to GWT 2.0
public interface NavigationControllerListener<String> extends ValueChangeHandler{
    String[] getAvailableConstants();
}
