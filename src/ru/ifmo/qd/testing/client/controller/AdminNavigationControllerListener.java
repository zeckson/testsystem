package ru.ifmo.qd.testing.client.controller;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.LoginService;
import ru.ifmo.qd.testing.client.model.tests.Subject;
import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.client.view.admin.AdminWelcomeWidget;
import ru.ifmo.qd.testing.client.view.admin.editor.*;
import ru.ifmo.qd.testing.client.view.admin.manager.RebuildWidget;
import ru.ifmo.qd.testing.client.view.admin.manager.ResultsWidget;
import ru.ifmo.qd.testing.client.view.common.OptionsSelectionListener;
import ru.ifmo.qd.testing.client.view.common.SubjectChooserWidget;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 1:09:28
 * To change this template use File | Settings | File Templates.
 */
public class AdminNavigationControllerListener implements NavigationControllerListener {

    private Admin admin;

    public static final String WELCOME_TOKEN = "welcome";
    public static final String REBALANCE_TOKEN = "rebalance";
    public static final String CREATE_SUBJECTS_TOKEN = "creator=";
    public static final String EDIT_SUBJECT_TOKEN = "edit_subject=";
    public static final String EDIT_BLOCK_TOKEN = "edit_block=";
    public static final String EDIT_PART_TOKEN = "edit_part=";
    public static final String EDIT_QN_GROUP_TOKEN = "edit_group=";
    public static final String VIEW_RESULTS = "view_results=";
    public static final String LOG_OFF = "logoff";

    private static final String[] AVAILABLE_CONSTANS = {CREATE_SUBJECTS_TOKEN,
            EDIT_SUBJECT_TOKEN,
            EDIT_BLOCK_TOKEN,
            EDIT_PART_TOKEN,
            EDIT_QN_GROUP_TOKEN,
            VIEW_RESULTS,
            LOG_OFF};


    public AdminNavigationControllerListener(Admin admin) {
        this.admin = admin;
    }

    public void onValueChange(ValueChangeEvent e) {
        String historyToken = History.getToken();
        final RootPanel rootPanel = RootPanel.get();
        if (historyToken.equalsIgnoreCase(WELCOME_TOKEN)) {
            rootPanel.clear();
            rootPanel.add(new AdminWelcomeWidget(admin));
        } else if (historyToken.startsWith(CREATE_SUBJECTS_TOKEN)) {
            int id = Integer.parseInt(historyToken.substring(CREATE_SUBJECTS_TOKEN.length()));
            rootPanel.clear();
            rootPanel.add(new CreateSubjectWidget(id));
        } else if (historyToken.startsWith(EDIT_SUBJECT_TOKEN)) {
            int testID = Integer.parseInt(historyToken.substring(EDIT_SUBJECT_TOKEN.length()));
            rootPanel.clear();
            rootPanel.add(new CreateBlockWidget(testID));
        } else if (historyToken.startsWith(EDIT_BLOCK_TOKEN)) {
            int blockID = Integer.parseInt(historyToken.substring(EDIT_BLOCK_TOKEN.length()));
            rootPanel.clear();
            rootPanel.add(new CreatePartWidget(blockID));
        } else if (historyToken.startsWith(EDIT_PART_TOKEN)) {
            int partID = Integer.parseInt(historyToken.substring(EDIT_PART_TOKEN.length()));
            rootPanel.clear();
            rootPanel.add(new CreateGroupWidget(partID));
        } else if (historyToken.startsWith(EDIT_QN_GROUP_TOKEN)) {
            int id = Integer.parseInt(historyToken.substring(EDIT_QN_GROUP_TOKEN.length()));
            rootPanel.clear();
            rootPanel.add(new CreateQuestionsWidget(id));
        } else if (historyToken.startsWith(REBALANCE_TOKEN)) {
            rootPanel.clear();
            rootPanel.add(new SubjectChooserWidget(new OptionsSelectionListener<Subject>() {
                public void collectOptions(AsyncCallback<List<Subject>> optionsCallback) {
                    TestService.App.getInstance().getSubjectsList(optionsCallback);
                }

                public void optionSelected(Subject subject) {
                    rootPanel.clear();
                    rootPanel.add(new RebuildWidget(subject.getId()));
                }
            }));
        } else if (historyToken.startsWith(VIEW_RESULTS)) {
            int id = Integer.parseInt(historyToken.substring(VIEW_RESULTS.length()));
            rootPanel.clear();
            rootPanel.add(new ResultsWidget(id));
        } else if (historyToken.equalsIgnoreCase(LOG_OFF)) {
            LoginService.App.getInstance().logoff(new AsyncCallback() {
                public void onFailure(Throwable caught) {
                    Window.alert("Log off failed!");
                }

                public void onSuccess(Object result) {
                    HistoryDispatcher.changeNavigationControllerListener(new DefaultNavigationControllerListener());
                    DefaultNavigationControllerListener.logon();
                }
            });
        } else {
            HistoryDispatcher.changeNavigationControllerListener(new DefaultNavigationControllerListener());
            History.newItem(DefaultNavigationControllerListener.LOGON_TOKEN);
        }
    }

    public static void createSubject(int id) {
        HistoryDispatcher.newItem(CREATE_SUBJECTS_TOKEN + id);
    }

    public static void welcomeScreen() {
        HistoryDispatcher.newItem(WELCOME_TOKEN);
    }

    public String[] getAvailableConstants() {
        return AVAILABLE_CONSTANS;
    }

}
