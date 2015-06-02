package ru.ifmo.qd.testing.client.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.i18n.UserWidgetsConstants;
import ru.ifmo.qd.testing.client.model.tests.Subject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 22, 2010
 * Time: 11:54:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubjectChooserWidget extends Composite {
    private List subjects = null;
    private VerticalPanel mainPanel = new VerticalPanel();
    private VerticalPanel innerPanel = new VerticalPanel();
    private DockPanel contentPanel = new DockPanel();
    private DockPanel subjectsPanel = new DockPanel();
    private UserWidgetsConstants widgetsConstants = (UserWidgetsConstants) GWT.create(UserWidgetsConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);

    private OptionsSelectionListener<Subject> listener;

    public SubjectChooserWidget(OptionsSelectionListener<Subject> listener) {
        this.listener = listener;
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
        listener.collectOptions(new AsyncCallback<List<Subject>>() {
            public void onFailure(Throwable caught) {
                Window.alert("Failed! " + caught.getMessage());
            }

            public void onSuccess(List<Subject> result) {
                drawSubjectList(result);
            }
        });
    }

    public void drawSubjectList(List<Subject> subjects) {
        if (!(subjects == null || subjects.isEmpty())) {
            subjectsPanel.clear();
            FlexTable table = new FlexTable();
            int rows = -1;
            for (final Subject subject : subjects) {
                table.setHTML(++rows, 0, subject.getSubjectName());
                table.setHTML(rows, 1, "( " + subject.getDescription() + " )");
                Anchor anc = new Anchor("select");
                anc.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        notifyOptionSelected(subject);
                    }
                });
                table.setWidget(rows, 2, anc);
                table.setWidget(rows, 3, new HTML("<a href=\"gwtentrypoint/download?stat=subject&id="+subject.getId()+ "\">download</a>"));
            }
            subjectsPanel.add(table, DockPanel.CENTER);
        }
    }


    private void notifyOptionSelected(Subject o) {
        listener.optionSelected(o);

    }
}
