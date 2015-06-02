/*
package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.userLabel.client.Window;
import com.google.gwt.userLabel.client.rpc.AsyncCallback;
import com.google.gwt.userLabel.client.ui.*;
import ru.ifmo.qd.testing.client.GWTRemoteService;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.model.tests.QuestionGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


*/
/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 30.11.2007
 * Time: 0:47:45
 * To change this template use File | Settings | File Templates.
 */
/*
public class EditQuestionGroupWidget extends Composite {
    private QuestionGroup questionGroup = null;
    private VerticalPanel mainPanel = new VerticalPanel();
    private HorizontalPanel tempPanel;
    private Label title;
    private TextBox name = new TextBox();
    private TextBox description = new TextBox();
    private QuestionsListBox questionsListBox;
    private List updateListeners = new ArrayList();

    public EditQuestionGroupWidget(QuestionGroup questionGroup) {
        this.questionGroup = questionGroup;
        initWidget(mainPanel);
        title = new HTML("<h1>Question goup " + questionGroup.getName() + "</h1>");
        mainPanel.add(title);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label("Group Name: "));
        tempPanel.add(name);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label("Group Description: "));
        tempPanel.add(description);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label("Questions: "));
        questionsListBox = new QuestionsListBox(questionGroup.getQuestions());
        tempPanel.add(questionsListBox);
        mainPanel.add(tempPanel);
        mainPanel.add(new QuestionGroupDeleteButton(questionGroup));
    }

    private class QuestionGroupDeleteButton extends Button {

        private QuestionGroup questionGroup;

        public QuestionGroupDeleteButton(QuestionGroup questionGroup) {
            super("delete");
            this.questionGroup = questionGroup;
            addClickListener(clickListener());
        }

        private ClickListener clickListener() {
            return new ClickListener() {

                public void onClick(Widget sender) {
                    setEnabled(false);
                    TestService.App.getInstance().removeQnGroup(questionGroup.getId(), new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error connecting with Database");
                        }

                        public void onSuccess(Object result) {
                            fireUpdate(result);
                        }
                    });
                }
            };

        }
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


    private class QuestionsListBox extends Composite {
        private List questions;
        private VerticalPanel myPanel = new VerticalPanel();
        private ListBox questionsListBox = new ListBox();
        private Button addButton = new Button("addQuestion");
        private Button removeButton = new Button("removeQuestion");

        public QuestionsListBox(List questions) {
            this.questions = questions;
            initWidget(myPanel);
            myPanel.add(questionsListBox);
            HorizontalPanel temp = new HorizontalPanel();
            addButton.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    CreateQuestionDialog questionDialog = new CreateQuestionDialog(questionGroup.getId());
                    questionDialog.show();
                    questionDialog.center();
                    questionDialog.addUpdateListener(new UpdateListener() {
                        public void updated(Object object) {
                            questionGroup.getQuestions().add(object);
                        }
                    });
                }
            });
            temp.add(addButton);
            removeButton.setEnabled(false);
            removeButton.addClickListener(new ClickListener() {
                public void onClick(Widget sender) {
                    removeButton.setEnabled(false);
*/
/*TestService.App.getInstance().removeQ(questionGroup.getId(), new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error connecting with Database");
                        }

                        public void onSuccess(Object result) {
                            fireUpdate(result);
                        }
                    });
                }*/
/*
*/
/*
                }
            });
            temp.add(removeButton);
            myPanel.add(temp);

        }

        private class CreateQuestionDialog extends DialogBox implements ClickListener, AsyncCallback {

            private Question question = null;
            private final String[] TYPE_CONSTANTS = {"Open", "Closed"};

            private VerticalPanel mainPanel = new VerticalPanel();
            private int id;

            private TextBox name = new TextBox();
            private ListBox type = new ListBox(false);
            private TextBox weight = new TextBox();

            {
                for (int i = 0; i < TYPE_CONSTANTS.length; i++) {
                    type.addItem(TYPE_CONSTANTS[i]);
                }
            }

            private Button applyButton = new Button("OK", this);
            private HorizontalPanel tempPanel;
            private List updateListeners = new ArrayList();

            public CreateQuestionDialog(int id) {
                this.id = id;
                setText("Create Question");
                init();

                DockPanel dock = new DockPanel();
                dock.setSpacing(4);
                dock.add(mainPanel, DockPanel.CENTER);


                dock.setWidth("100%");
                setWidget(dock);

            }

            private void init() {
                Button closeButton = new Button("Close", this);
                tempPanel = new HorizontalPanel();
                tempPanel.add(new Label("Question Name: "));
                tempPanel.add(name);
                mainPanel.add(tempPanel);
                tempPanel = new HorizontalPanel();
                tempPanel.add(new Label("Type: "));
                tempPanel.add(type);
                mainPanel.add(tempPanel);
                tempPanel = new HorizontalPanel();
                tempPanel.add(new Label("Weight: "));
                weight.setText("0");
                tempPanel.add(weight);
                mainPanel.add(tempPanel);
                tempPanel = new HorizontalPanel();
                tempPanel.add(closeButton);
                tempPanel.add(applyButton);
                mainPanel.add(tempPanel);
            }


            private void createQuestion() {
                if (TYPE_CONSTANTS[type.getSelectedIndex()].equalsIgnoreCase("open")) {
                    TestService.App.getInstance().createOpenQuestion(id, name.getText(), body.getText(), answer.getText(), Integer.parseInt(weight.getText()), Integer.parseInt(time.getText()), this);
                } else if (TYPE_CONSTANTS[type.getSelectedIndex()].equalsIgnoreCase("closed")) {
                    TestService.App.getInstance().createClosedQuestion(id, name.getText(), body.getText(), answer.getAnswers(), Integer.parseInt(weight.getText()), Integer.parseInt(time.getText()), this);
                } else {
                    Window.alert("Unknown type!");
                }


            }

            public void onClick(Widget sender) {
                if (sender == applyButton) {
                    applyButton.setEnabled(false);
                    createQuestion();
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

            public void onFailure(Throwable caught) {
                Window.alert("Can't create question!");
            }

            public void onSuccess(Object result) {
                fireUpdate(result);
            }
        }
    }
}

*/
/*
*/
