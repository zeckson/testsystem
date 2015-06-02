package ru.ifmo.qd.testing.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.LoginService;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.i18n.RegistrationConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 0:55:56
 * To change this template use File | Settings | File Templates.
 */
public class RegisterScreenWidget extends Composite {
    private static RegistrationConstants constants = (RegistrationConstants) GWT.create(RegistrationConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);
    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable flexTable = new FlexTable();
    private Label registerLabel = new HTML("<h1>" + constants.registrationForm() + "</h1>");
    private TextBox login = new TextBox();
    private PasswordTextBox password = new PasswordTextBox();
    private TextBox name = new TextBox();
    private TextBox surename = new TextBox();
    private ListBox info = new ListBox();
    private Button applyButton = new Button(constants.applyButton());
    private Button cancelButton = new Button(constants.cancelButton());


    public RegisterScreenWidget() {

        VerticalPanel fakePanel = new VerticalPanel();
        fakePanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        fakePanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
        fakePanel.setWidth("100%");
        fakePanel.setHeight("100%");
        initWidget(fakePanel);
        fakePanel.add(mainPanel);
        mainPanel.add(registerLabel);
        flexTable.setHTML(0, 0, constants.loginField());
        flexTable.setWidget(0, 1, login);
        flexTable.setHTML(1, 0, constants.passwordField());
        flexTable.setWidget(1, 1, password);
        flexTable.setHTML(2, 0, constants.nameField());
        flexTable.setWidget(2, 1, name);
        flexTable.setHTML(3, 0, constants.surenameField());
        flexTable.setWidget(3, 1, surename);
        flexTable.setHTML(4, 0, constants.roleSelect());
        info.addItem("admin");
        info.addItem("user");
        info.setItemSelected(1, true);
        flexTable.setWidget(4, 1, info);
        mainPanel.add(flexTable);
        HorizontalPanel myPanel = new HorizontalPanel();
        myPanel.add(cancelButton);
        myPanel.add(applyButton);
        mainPanel.add(myPanel);
        cancelButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                History.back();
            }
        });
        applyButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                applyButton.setEnabled(false);
                LoginService.App.getInstance().createPerson(
                        login.getText(),
                        password.getText(),
                        name.getText(),
                        surename.getText(), info.getItemText(info.getSelectedIndex()), new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        Window.alert(alertConstants.creatingRoleFailes());
                    }

                    public void onSuccess(Object result) {
                        History.back();
                    }

                });

            }


        }


        );

    }

    public void clear() {
        login.setText("");
        password.setText("");
        name.setText("");
        surename.setText("");
        applyButton.setEnabled(true);
    }
}
