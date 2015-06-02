package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.GWT;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;
import ru.ifmo.qd.testing.client.model.tests.QuestionGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 30.11.2007
 * Time: 7:55:42
 * To change this template use File | Settings | File Templates.
 */
public class CreateGroupWidget extends Composite {
    private static AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private int ownerID = -1;
    private VerticalPanel mainPanel = new VerticalPanel();
    private Label title = new HTML(constants.groupsTitle());
    private List groups = new ArrayList();
    private Button createButton = new Button(constants.newInstanceLabel());
    private DockPanel tableWrapper = new DockPanel();


    public CreateGroupWidget(int id) {
        ownerID = id;
        initWidget(mainPanel);
        mainPanel.add(title);
        mainPanel.add(tableWrapper);
        createButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                CreateGroupDialog groupDialog = new CreateGroupDialog(ownerID);
                groupDialog.show();
                groupDialog.center();
                groupDialog.addUpdateListener(new UpdateListener() {

                    public void updated(Object object) {
                        groups.add(object);
                        rebuildGroupsTable();
                    }
                }

                );

            }
        });
        mainPanel.add(createButton);
        loadParts(ownerID);
    }

    private void loadParts(int id) {
        TestService.App.getInstance().getQnGroupsList(id, new AsyncCallback() {

            public void onFailure(Throwable throwable) {
                Window.alert("Can't get list!");
            }

            public void onSuccess(Object object) {
                if (object == null) return;
                groups = (ArrayList) object;
                rebuildGroupsTable();
            }
        });
    }

    private Hyperlink addLink(QuestionGroup part) {
        return new Hyperlink(constants.editButton(), "edit_group=" + part.getId());

    }


    private void rebuildGroupsTable() {
        tableWrapper.clear();
        FlexTable table = new FlexTable();
        int rows = -1;
        table.setHTML(++rows, 0, "ID");
        table.setHTML(rows, 1, constants.nameLabel());
        table.setHTML(rows, 2, constants.descriptionLabel());
        table.setHTML(rows, 3, "");
        table.setHTML(rows, 4, "");
        for (Iterator iter = groups.iterator(); iter.hasNext();) {
            QuestionGroup group = (QuestionGroup) iter.next();
            table.setHTML(++rows, 0, String.valueOf(group.getId()));
            table.setHTML(rows, 1, group.getName());
            table.setHTML(rows, 2, group.getDescription());
            table.setWidget(rows, 3, addLink(group));
            table.setWidget(rows, 4, new GroupDeleteButton(group));
        }
        tableWrapper.add(table, DockPanel.CENTER);
    }


    private class GroupDeleteButton extends Button {

        private QuestionGroup part;

        public GroupDeleteButton(QuestionGroup part) {
            super(constants.deleteButton());
            this.part = part;
            addClickListener(clickListener());
        }

        private ClickListener clickListener() {
            return new ClickListener() {

                public void onClick(Widget sender) {
                    setEnabled(false);
                    TestService.App.getInstance().removeQnGroup(part.getId(), new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error connecting with Database");
                        }

                        public void onSuccess(Object result) {
                            groups.remove(part);
                            rebuildGroupsTable();
                        }


                    });
                }
            };

        }

    }

    private class CreateGroupDialog extends DialogBox implements ClickListener {

        private VerticalPanel mainPanel = new VerticalPanel();

        private QuestionGroup part = null;
        private int id;

        private TextBox name = new TextBox();
        private TextBox description = new TextBox();
        private Button applyButton = new Button("OK", this);
        private HorizontalPanel tempPanel;
        private List updateListeners = new ArrayList();

        public CreateGroupDialog(int id) {
            this.id = id;
            setText(constants.newInstanceLabel());


            Button closeButton = new Button(constants.closeButton(), this);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label(constants.nameLabel() + ": "));
            tempPanel.add(name);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label(constants.descriptionLabel() + ": "));
            tempPanel.add(description);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();

            tempPanel = new HorizontalPanel();
            tempPanel.add(closeButton);
            tempPanel.add(applyButton);
            mainPanel.add(tempPanel);


            DockPanel dock = new DockPanel();
            dock.setSpacing(4);
            dock.add(mainPanel, DockPanel.CENTER);


            dock.setWidth("100%");
            setWidget(dock);

        }

        private void createPart() {

            TestService.App.getInstance().createQnGroup(id, name.getText(), description.getText(), new AsyncCallback() {

                public void onFailure(Throwable caught) {
                    Window.alert("Not created! " + caught.getMessage());
                }

                public void onSuccess(Object result) {
                    fireUpdate((QuestionGroup) result);
                }
            });

        }

        public void onClick(Widget sender) {
            if (sender == applyButton) {
                applyButton.setEnabled(false);
                createPart();
            }
            hide();
        }

        public void addUpdateListener(UpdateListener listener) {
            updateListeners.add(listener);
        }

        private void fireUpdate(QuestionGroup part) {
            for (Iterator iterator = updateListeners.iterator(); iterator.hasNext();) {
                UpdateListener updateListener = (UpdateListener) iterator.next();
                updateListener.updated(part);
            }
        }


    }
}
