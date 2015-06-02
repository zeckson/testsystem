package ru.ifmo.qd.testing.client.view.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.UserService;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.i18n.UserWidgetsConstants;
import ru.ifmo.qd.testing.client.model.tests.Subject;

import java.util.Iterator;
import java.util.List;

public class WelcomeWidget extends Composite {
    private List subjects = null;
    private VerticalPanel mainPanel = new VerticalPanel();
    private VerticalPanel innerPanel = new VerticalPanel();
    private DockPanel contentPanel = new DockPanel();
    private DockPanel subjectsPanel = new DockPanel();
    private UserWidgetsConstants widgetsConstants = (UserWidgetsConstants) GWT.create(UserWidgetsConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);

    public WelcomeWidget() {
        initWidget(mainPanel);
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        mainPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        mainPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
        mainPanel.add(innerPanel);
        mainPanel.addStyleName("test-panel");
        mainPanel.setCellHorizontalAlignment(innerPanel, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(innerPanel, HorizontalPanel.ALIGN_MIDDLE);
        innerPanel.add(contentPanel);
//        contentPanel.add(new Label(widgetsConstants.welcomeString() + person.getName()), DockPanel.NORTH);
        subjectsPanel.add(new Label(widgetsConstants.loadingSubjects()), DockPanel.CENTER);
        subjectsPanel.addStyleName("testing-subjectPanel");
        contentPanel.add(subjectsPanel, DockPanel.CENTER);
        loadSubjects();

    }


    public void drawSubjectList(List subjects) {
        subjectsPanel.clear();
        FlexTable table = new FlexTable();
        int rows = -1;
        for (Iterator iterator = subjects.iterator(); iterator.hasNext();) {
            Subject subject = (Subject) iterator.next();
            table.setHTML(++rows, 0, subject.getSubjectName());
            table.setHTML(rows, 1, "( " + subject.getDescription() + " )");
            table.setWidget(rows, 2, new Hyperlink(widgetsConstants.goForTest(), "start_test=" + subject.getId()));
        }
        subjectsPanel.add(table, DockPanel.CENTER);
    }


    private void loadSubjects() {
        UserService.App.getInstance().getAvalaibleTests(new AsyncCallback() {
            public void onFailure(Throwable caught) {
                Window.alert(alertConstants.loadSubjectsFailed());
            }

            public void onSuccess(Object result) {
                subjects = (List) result;
                drawSubjectList(subjects);
            }
        });
    }
}
