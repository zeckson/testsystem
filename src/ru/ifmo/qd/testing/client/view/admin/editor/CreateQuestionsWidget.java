package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CreateQuestionsWidget extends Composite implements UpdateListener {
    private int ownerID = -1;
    private static AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private VerticalPanel mainPanel = new VerticalPanel();
    private Label title = new HTML(constants.questionCreatorTitle());
    private List questions = new ArrayList();
    private Button createOpenQuestionButton = new Button(constants.createOpenQuestionLabel());
    private Button createClosedQuestionButton = new Button(constants.createClosedQuestionLabel());
    private DockPanel tableWrapper = new DockPanel();
    private EditOpenQuestionDialog openQuestionDialog = null;
    private EditClosedQuestionDialog closedQuestionDialog = null;

    public CreateQuestionsWidget(int id) {
        ownerID = id;
        openQuestionDialog = new EditOpenQuestionDialog(ownerID, this);
        closedQuestionDialog = new EditClosedQuestionDialog(ownerID, this);
        initWidget(mainPanel);
        mainPanel.add(title);
        mainPanel.add(tableWrapper);
        HorizontalPanel temp = new HorizontalPanel();
        createOpenQuestionButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                openQuestionDialog.setOpenQuestion(null);
                openQuestionDialog.show();
                openQuestionDialog.center();
            }
        });
        temp.add(createOpenQuestionButton);
        createClosedQuestionButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                closedQuestionDialog.setClosedQuestion(null);
                closedQuestionDialog.show();
                closedQuestionDialog.center();
            }
        });
        temp.add(createClosedQuestionButton);
        mainPanel.add(temp);
        loadQuestions(ownerID);
    }

    private void loadQuestions(int id) {
        TestService.App.getInstance().getQuestionsList(id, new AsyncCallback() {

            public void onFailure(Throwable throwable) {
                Window.alert("Can't get list!");
            }

            public void onSuccess(Object object) {
                if (object == null) return;
                questions = (ArrayList) object;
                rebuildQuestionsTable();
            }
        });
    }


    private void rebuildQuestionsTable() {
        tableWrapper.clear();
        FlexTable table = new FlexTable();
        int rows = -1;
        table.setHTML(++rows, 0, "ID");
        table.setHTML(rows, 1, constants.nameLabel());
        table.setHTML(rows, 2, constants.typeLabel());
        table.setHTML(rows, 3, constants.weightLabel());
        table.setHTML(rows, 4, "");
        table.setHTML(rows, 5, "");
        for (Iterator iter = questions.iterator(); iter.hasNext();) {
            Question question = (Question) iter.next();
            table.setHTML(++rows, 0, String.valueOf(question.getId()));
            table.setHTML(rows, 1, question.getName());
            table.setHTML(rows, 2, GWT.getTypeName(question).substring(38).equalsIgnoreCase("open") ? constants.openQuestionLabel() : constants.closedQuestionLabel());
            table.setHTML(rows, 3, question.getWeight().toString());
            EditQuestionButton editQuestionButton = new EditQuestionButton(question, this);           
            table.setWidget(rows, 4, editQuestionButton);
            table.setWidget(rows, 5, new QuestionDeleteButton(question));
        }
        tableWrapper.add(table, DockPanel.CENTER);
    }

    public EditOpenQuestionDialog getOpenQuestionDialog() {
        return openQuestionDialog;
    }

    public EditClosedQuestionDialog getClosedQuestionDialog() {
        return closedQuestionDialog;
    }

    public void updated(Object object) {
        questions.add(object);
        rebuildQuestionsTable();
    }

    private class QuestionDeleteButton extends Button {

        private Question question;

        public QuestionDeleteButton(Question question) {
            super(constants.deleteButton());
            this.question = question;
            addClickListener(clickListener());
        }

        private ClickListener clickListener() {
            return new ClickListener() {

                public void onClick(Widget sender) {
                    setEnabled(false);
                    TestService.App.getInstance().removeQuestion(question.getId(), new AsyncCallback() {

                        public void onFailure(Throwable caught) {
                            Window.alert("Error connecting with Database");
                        }

                        public void onSuccess(Object result) {
                            questions.remove(question);
                            rebuildQuestionsTable();
                        }


                    });
                }
            };

        }

    }


}


