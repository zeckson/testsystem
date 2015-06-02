package ru.ifmo.qd.testing.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.LoginService;
import ru.ifmo.qd.testing.client.controller.AdminNavigationControllerListener;
import ru.ifmo.qd.testing.client.controller.DefaultNavigationControllerListener;
import ru.ifmo.qd.testing.client.controller.HistoryDispatcher;
import ru.ifmo.qd.testing.client.controller.UserNavigationControllerListener;
import ru.ifmo.qd.testing.client.i18n.InternationalUserInterface;
import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.users.User;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 0:12:11
 * To change this template use File | Settings | File Templates.
 */
public class LoginScreenWidget extends Composite {

    private HorizontalPanel horizontalPanel;
    private VerticalPanel verticalPanel;
    private TextBox loginTextBox;
    private PasswordTextBox passwordTextBox = new PasswordTextBox();
    private Button loginButton = new Button("Войти");
    private InternationalUserInterface userInterface = (InternationalUserInterface) GWT.create(InternationalUserInterface.class);
    private Hyperlink registerHyperlink = new Hyperlink("Регистрация", "register");


    public LoginScreenWidget() {

        passwordTextBox.addFocusHandler(new FocusHandler() {
            public void onFocus(FocusEvent event) {
                passwordTextBox.selectAll();
            }

        });

        passwordTextBox.addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    loginButton.click();
                }
                if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
                    DefaultNavigationControllerListener.register();
                }
            }
        });

        horizontalPanel = new HorizontalPanel();
        horizontalPanel.setWidth("100%");
        horizontalPanel.setHeight("100%");
        verticalPanel = new VerticalPanel();
        verticalPanel.setWidth("100%");
        verticalPanel.setHeight("100");
        loginTextBox = new TextBox();
        verticalPanel.add(loginTextBox);
        verticalPanel.add(passwordTextBox);
        verticalPanel.add(loginButton);
//        verticalPanel.add(registerHyperlink);
        verticalPanel.setCellHorizontalAlignment(loginTextBox, VerticalPanel.ALIGN_CENTER);
        verticalPanel.setCellHorizontalAlignment(passwordTextBox, VerticalPanel.ALIGN_CENTER);
        verticalPanel.setCellHorizontalAlignment(loginButton, VerticalPanel.ALIGN_CENTER);
        //verticalPanel.setCellHorizontalAlignment(registerHyperlink, VerticalPanel.ALIGN_CENTER);
        loginButton.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                loginButton.setEnabled(false);
                register();
            }
        });
        horizontalPanel.add(verticalPanel);
        horizontalPanel.setCellVerticalAlignment(verticalPanel, VerticalPanel.ALIGN_MIDDLE);

        initWidget(horizontalPanel);

    }

    private void register() {

        LoginService.App.getInstance().getPerson(loginTextBox.getText(), passwordTextBox.getText(), new AsyncCallback<Person>() {
            public void onFailure(Throwable caught) {
                Window.alert("Error! " + caught.getMessage());
                loginButton.setEnabled(true);
            }

            public void onSuccess(Person result) {
                if (result instanceof Admin) {
                    HistoryDispatcher.changeNavigationControllerListener(new AdminNavigationControllerListener((Admin) result));
                } else if (result instanceof User) {
                    HistoryDispatcher.changeNavigationControllerListener(new UserNavigationControllerListener(result));
                } else {
                    Window.alert("No such user!");
                }
                HistoryDispatcher.newItem("welcome");
                loginButton.setEnabled(true);
            }
        });
    }

}
