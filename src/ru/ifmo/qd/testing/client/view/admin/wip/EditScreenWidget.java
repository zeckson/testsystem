/*
package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.core.client.GWT;
import com.google.gwt.userLabel.client.Window;
import com.google.gwt.userLabel.client.rpc.AsyncCallback;
import com.google.gwt.userLabel.client.ui.*;
import ru.ifmo.qd.testing.client.GWTRemoteService;
import ru.ifmo.qd.testing.client.i18n.InternationalUserInterface;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;
import ru.ifmo.qd.testing.client.model.tests.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

*/
/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 0:31:55
 * To change this template use File | Settings | File Templates.
 */
/*
public class EditScreenWidget extends Composite {
    private InternationalUserInterface userInterface = (InternationalUserInterface) GWT.create(InternationalUserInterface.class);

    private HorizontalPanel mainPanel;
    private VerticalPanel leftPanel = new VerticalPanel();
    private VerticalPanel rightPanel = new VerticalPanel();
    private Label loadingLabel = new Label(userInterface.waitLabel());
    private Label welcomeLabel = new Label(userInterface.availableTestLabel());
    private Button createNewTest = new Button(userInterface.createNewTestButtonLabel());
    private Tree testTree = new Tree();
    private List testsList;
    private EditOpenQuestionWidget openQuestionWidget = new EditOpenQuestionWidget();


    public EditScreenWidget() {

        mainPanel = new HorizontalPanel();
        initWidget(mainPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        openQuestionWidget.setVisible(false);
        rightPanel.add(openQuestionWidget);

        leftPanel.add(welcomeLabel);
        leftPanel.add(loadingLabel);
        leftPanel.add(testTree);

        createNewTest.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                Window.alert("Not implemented yet!");
            }
        });
        loadTests();
        leftPanel.add(createNewTest);

    }

    private void loadTests() {
        TestService.App.getInstance().getTestsList(new AsyncCallback() {

            public void onFailure(Throwable throwable) {
                Window.alert("Can't get questions list!");
            }

            public void onSuccess(Object object) {
                testsList = (ArrayList) object;
                fillTree((ArrayList) testsList);
                leftPanel.refresh(loadingLabel);
            }

            private void fillTree(ArrayList testsList) {
                for (Iterator iterator = testsList.iterator(); iterator.hasNext();) {
                    Test programmingTest = (Test) iterator.next();
                    TreeItem treeItem = new TreeItem(new TestLeafWidget(programmingTest));
                    for (Iterator iter = programmingTest.getQuestionsList().iterator(); iter.hasNext();) {
                        Question question = (Question) iter.next();
                        if (question instanceof OpenQuestion) {
                            OpenQuestion openQuestion = (OpenQuestion) question;
                            QuestionLeafWidget questionLeafWidget = new QuestionLeafWidget();
                            questionLeafWidget.setQuestion(openQuestion);
                            treeItem.addItem(questionLeafWidget);
                        }else if(question instanceof ClosedQuestion) {
                            ClosedQuestion closedQuestion = (ClosedQuestion) question;
                            QuestionLeafWidget questionLeafWidget = new QuestionLeafWidget();
                            questionLeafWidget.setQuestion(closedQuestion);
                            treeItem.addItem(questionLeafWidget);
                        }else{
                            System.out.println("ufyaisudfyuasi");
                        }

                    }
                    testTree.addItem(treeItem);
                }
                testTree.addTreeListener(new TreeListener() {
                    public void onTreeItemSelected(TreeItem treeItem) {
                        if (treeItem.isSelected() && treeItem.getWidget() instanceof EditWidgetProvider) {
                            EditWidgetProvider editWidgetProvider = (EditWidgetProvider) treeItem.getWidget();
                            rightPanel.clear();
                            rightPanel.add(editWidgetProvider.getEditWidget());
                        }
                    }

                    public void onTreeItemStateChanged(TreeItem treeItem) {
                        if (treeItem.getWidget() instanceof TestLeafWidget) {

                        }
                    }
                });
            }
        });
    }

    public void refreshTree() {
        loadTests();
    }
    
}
*/
