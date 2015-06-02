/*
package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.userLabel.client.Window;
import com.google.gwt.userLabel.client.rpc.AsyncCallback;
import com.google.gwt.userLabel.client.ui.*;
import com.google.gwt.core.client.GWT;
import ru.ifmo.qd.testing.client.GWTRemoteService;
import ru.ifmo.qd.testing.client.i18n.InternationalUserInterface;
import ru.ifmo.qd.testing.client.model.tests.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

*/
/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 29.11.2007
 * Time: 9:36:37
 * To change this template use File | Settings | File Templates.
 */
/*
public class EditQnGroupsWidget extends Composite {

    int ownerID = -1;
    private InternationalUserInterface userInterface = (InternationalUserInterface) GWT.create(InternationalUserInterface.class);

    private HorizontalPanel mainPanel;
    private VerticalPanel leftPanel = new VerticalPanel();
    private VerticalPanel rightPanel = new VerticalPanel();
    private Label loadingLabel = new Label(userInterface.waitLabel());
    private Label welcomeLabel = new Label(userInterface.availableTestLabel());
    private Button createNewGroup = new Button(userInterface.createNewGroupButtonLabel());
    private Tree qnGroupsTree = new Tree();
    private List questionGroups = new ArrayList();


    public EditQnGroupsWidget(int id) {
        ownerID = id;

        mainPanel = new HorizontalPanel();
        initWidget(mainPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        rightPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
        rightPanel.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
        leftPanel.add(welcomeLabel);
        leftPanel.add(loadingLabel);
        leftPanel.add(qnGroupsTree);

        createNewGroup.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                CreateQnGroupDialog qnGroupDialog = new CreateQnGroupDialog(ownerID);
                qnGroupDialog.show();
                qnGroupDialog.center();
                qnGroupDialog.addUpdateListener(new UpdateListener() {

                    public void updated(Object object) {
                        questionGroups.add(object);
                        repaintTree();
                    }
                }
                );
            }
        });
        loadQnGroups();
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(createNewGroup);
        Button refreshButton = new Button("Refresh", new ClickListener() {
            public void onClick(Widget sender) {
                loadQnGroups();
            }
        });
        horizontalPanel.add(refreshButton);
        leftPanel.add(createNewGroup);

    }

    private void loadQnGroups() {
        TestService.App.getInstance().getQnGroupsList(ownerID, new AsyncCallback() {

            public void onFailure(Throwable throwable) {
                Window.alert("Can't get questions list!");
            }

            public void onSuccess(Object object) {
                questionGroups = (ArrayList) object;
                repaintTree();
                leftPanel.remove(loadingLabel);
            }


        });
    }

    private void repaintTree() {
        qnGroupsTree.clear();
        for (Iterator iterator = questionGroups.iterator(); iterator.hasNext();) {
            QuestionGroup questionGroup = (QuestionGroup) iterator.next();
            QuestionGroupLeafWidget groupLeafWidget = new QuestionGroupLeafWidget();
            groupLeafWidget.addRefreshListener(new QuestionGroupLeafWidget.RefreshListener() {
                public void refresh() {
                    loadQnGroups();
                }
            });
            groupLeafWidget.setQuestionGroup(questionGroup);
            TreeItem treeItem = new TreeItem(groupLeafWidget);
            for (Iterator iter = questionGroup.getQuestions().iterator(); iter.hasNext();) {
                Question question = (Question) iter.next();
                if (question instanceof OpenQuestion) {
                    OpenQuestion openQuestion = (OpenQuestion) question;
                    QuestionLeafWidget questionLeafWidget = new QuestionLeafWidget();
                    questionLeafWidget.setQuestion(openQuestion);
                    treeItem.addItem(questionLeafWidget);
                } else if (question instanceof ClosedQuestion) {
                    ClosedQuestion closedQuestion = (ClosedQuestion) question;
                    QuestionLeafWidget questionLeafWidget = new QuestionLeafWidget();
                    questionLeafWidget.setQuestion(closedQuestion);
                    treeItem.addItem(questionLeafWidget);
                } else {
                    System.out.println("ufyaisudfyuasi");
                }

            }
            qnGroupsTree.addItem(treeItem);
        }
        qnGroupsTree.addTreeListener(new TreeListener() {
            public void onTreeItemSelected(TreeItem treeItem) {
                if (treeItem.isSelected() && treeItem.getWidget() instanceof EditWidgetProvider) {
                    EditWidgetProvider editWidgetProvider = (EditWidgetProvider) treeItem.getWidget();
                    rightPanel.clear();
                    rightPanel.add(editWidgetProvider.getEditWidget());
                }
            }

            public void onTreeItemStateChanged(TreeItem treeItem) {
                if (treeItem.getWidget() instanceof QuestionGroupLeafWidget && treeItem.getState()) {

                }
            }
        });
    }

    public void refreshTree() {
        loadQnGroups();
    }


    private class CreateQnGroupDialog extends DialogBox implements ClickListener {

        private QuestionGroup qnGroup = null;

        private VerticalPanel mainPanel = new VerticalPanel();
        private int id;

        private TextBox name = new TextBox();
        private TextBox description = new TextBox();
        private Button applyButton = new Button("OK", this);
        private HorizontalPanel tempPanel;
        private List updateListeners = new ArrayList();

        public CreateQnGroupDialog(int id) {
            this.id = id;
            setText("New Question Group");


            Button closeButton = new Button("Close", this);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label("Question Group Name: "));
            tempPanel.add(name);
            mainPanel.add(tempPanel);
            tempPanel = new HorizontalPanel();
            tempPanel.add(new Label("Short Description: "));
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

        private void createGroup() {

            TestService.App.getInstance().createQnGroup(id, name.getText(), description.getText(), new AsyncCallback() {

                public void onFailure(Throwable caught) {
                    Window.alert("Not created! " + caught.getMessage());
                }

                public void onSuccess(Object result) {
                    fireUpdate(result);
                }
            });

        }

        public void onClick(Widget sender) {
            if (sender == applyButton) {
                applyButton.setEnabled(false);
                createGroup();
            }
            hide();
        }

        public void addUpdateListener(UpdateListener listener) {
            updateListeners.add(listener);
        }

        private void fireUpdate(Object o) {
            for (Iterator iterator = updateListeners.iterator(); iterator.hasNext();) {
                UpdateListener updateListener = (UpdateListener) iterator.next();
                updateListener.updated(o);
            }
        }


    }
}

*/
