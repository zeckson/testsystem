package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.GWT;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;
import ru.ifmo.qd.testing.client.model.tests.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 25.11.2007
 * Time: 18:34:32
 * To change this template use File | Settings | File Templates.
 */
public class CreateSubjectWidget extends Composite {
    private static AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private VerticalPanel mainPanel = new VerticalPanel();
    private Label title = new HTML(constants.subjectsTitleLabel());
    private List<Subject> subjects = new ArrayList<Subject>();
    private CreateSubjectDialog subjectDialog;
    private Button createButton = new Button(constants.createButton());
    private DockPanel tableWrapper = new DockPanel();

    public CreateSubjectWidget() {
        initWidget(mainPanel);
        mainPanel.add(title);
        mainPanel.add(tableWrapper);
        createButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                (subjectDialog = new CreateSubjectDialog()).show();
                subjectDialog.center();
                subjectDialog.addUpdateListener(new UpdateListener() {

                    public void updated(Object object) {
                        subjects.add((Subject) object);
                        rebuildSubjectsTable();
                    }
                });

            }
        });
        mainPanel.add(createButton);
        loadSubjects();


    }

    public CreateSubjectWidget(int id) {
        this();
    }

    private void loadSubjects() {
        TestService.App.getInstance().getSubjectsList(new AsyncCallback<List<Subject>>() {

            public void onFailure(Throwable throwable) {
                Window.alert("Can't get questions list!");
            }

            public void onSuccess(List<Subject> object) {
                if (object == null) return;
                subjects = object;
                rebuildSubjectsTable();
            }
        });
    }

    private Hyperlink addLink(Subject subject) {
        return new Hyperlink(constants.editButton(), "edit_subject=" + subject.getId());

    }


    private void rebuildSubjectsTable() {
        tableWrapper.clear();
        FlexTable table = new FlexTable();
        int rows = -1;
        table.setHTML(++rows, 0, "ID");
        table.setHTML(rows, 1, constants.nameLabel());
        table.setHTML(rows, 2, constants.descriptionLabel());
        table.setHTML(rows, 3, ""/*constants.editButton()*/);
        table.setHTML(rows, 4, ""/*constants.deleteButton()*/);
        for (Subject subject : subjects) {
            table.setHTML(++rows, 0, String.valueOf(subject.getId()));
            table.setHTML(rows, 1, subject.getSubjectName());
            table.setHTML(rows, 2, subject.getDescription());
            table.setWidget(rows, 3, addLink(subject));
            table.setWidget(rows, 4, new SubjectDeleteButton(subject));
        }
        tableWrapper.add(table, DockPanel.CENTER);
    }


    private class SubjectDeleteButton extends Button {

        private Subject subject;

        public SubjectDeleteButton(Subject subject) {
            super(constants.deleteButton());
            this.subject = subject;
            addClickListener(clickListener());

        }

        private ClickListener clickListener() {
            return new ClickListener() {

                public void onClick(Widget sender) {
                    setEnabled(false);
                    TestService.App.getInstance().removeSubject(subject, new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error connecting with Database");
                        }

                        public void onSuccess(Object result) {
                            subjects.remove(subject);
                            rebuildSubjectsTable();
                        }


                    });
                }
            };

        }

    }

    /**
     * Created by IntelliJ IDEA.
     * User: Zeckson
     * Date: 25.11.2007
     * Time: 19:36:08
     * To change this template use File | Settings | File Templates.
     */
    public static class CreateSubjectDialog extends DialogBox implements ClickListener {

        private List updateListeners = new ArrayList();
        private VerticalPanel mainPanel = new VerticalPanel();

        private Subject subject = null;

        private TextBox name = new TextBox();
        private TextBox description = new TextBox();
        private TextBox authorName = new TextBox();
        private Button applyButton = new Button("OK", this);
        private HorizontalPanel tempPanel;

        public CreateSubjectDialog() {
            setText(constants.newInstanceLabel());

            Button closeButton = new Button(constants.closeButton(), this);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label(constants.nameLabel()+ ": "));
            tempPanel.add(name);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label(constants.descriptionLabel()+ ": "));
            tempPanel.add(description);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label(constants.authorName() + ": "));
            tempPanel.add(authorName);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();
            tempPanel.add(closeButton);
            tempPanel.add(applyButton);
            mainPanel.add(tempPanel);


            DockPanel dock = new DockPanel();
            dock.setSpacing(4);
            dock.add(mainPanel, DockPanel.CENTER);

            //dock.setCellHorizontalAlignment(closeButton, DockPanel.ALIGN_RIGHT);
            dock.setWidth("100%");
            setWidget(dock);

        }

        private void createSubject() {

            TestService.App.getInstance().createSubject(name.getText(), description.getText(), authorName.getText(), new AsyncCallback() {

                public void onFailure(Throwable caught) {
                    Window.alert("Subject not created!");
                    return;
                }

                public void onSuccess(Object result) {
                    fireUpdate((Subject) result);
                }
            });

        }

        public void onClick(Widget sender) {
            if (sender == applyButton) {
                applyButton.setEnabled(false);
                createSubject();
            }
            hide();
        }

        public void addUpdateListener(UpdateListener updateListener) {
            updateListeners.add(updateListener);
        }

        private void fireUpdate(Subject subject) {
            for (Iterator iterator = updateListeners.iterator(); iterator.hasNext();) {
                UpdateListener updateListener = (UpdateListener) iterator.next();
                updateListener.updated(subject);
            }
        }

    }
}
