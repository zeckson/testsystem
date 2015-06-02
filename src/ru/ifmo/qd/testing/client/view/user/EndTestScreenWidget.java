package ru.ifmo.qd.testing.client.view.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.LoginService;
import ru.ifmo.qd.testing.client.controller.HistoryDispatcher;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.i18n.UserWidgetsConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 0:50:28
 * To change this template use File | Settings | File Templates.
 */
public class EndTestScreenWidget extends Composite {

    VerticalPanel main = new VerticalPanel();
    private UserWidgetsConstants userWidgetsConstants = (UserWidgetsConstants) GWT.create(UserWidgetsConstants.class);
    private Label complete = new HTML(userWidgetsConstants.endTestLabel());
    private Label logoffLabel = new HTML(userWidgetsConstants.testingResultsLabel());
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);

    public EndTestScreenWidget() {
        initWidget(main);
        main.add(complete);
        main.add(logoffLabel);
        Button button = new Button(userWidgetsConstants.endButton());
        button.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                LoginService.App.getInstance().logoff(new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        Window.alert(alertConstants.logoffOperationFailed());
                    }

                    public void onSuccess(Object result) {
                        HistoryDispatcher.changeNavigationControllerListener(HistoryDispatcher.DEFAULT_CONTROLLER);
                        HistoryDispatcher.newItem("logon");
                    }
                });
            }
        });
        main.add(button);

    }
}
