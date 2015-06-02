package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.GWT;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 13.05.2008
 * Time: 23:56:09
 * To change this template use File | Settings | File Templates.
 */
class EditClosedQuestionDialog extends DialogBox implements ClickListener, AsyncCallback {
    private static AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private ClosedQuestion closedQuestion = null;

    private VerticalPanel mainPanel = new VerticalPanel();
    private int id;
    private TextBox name = new TextBox();
    private TextBox weight = new TextBox();
    private TextArea body = new TextArea();
    private AnswersWidget answer = new AnswersWidget();
    private TextBox time = new TextBox();


    private Button applyButton = new Button("OK", this);
    private HorizontalPanel tempPanel;
    private List updateListeners = new ArrayList();

    public EditClosedQuestionDialog(int id) {
        this.id = id;
        setText(constants.newClosedQuestionLabel());
        init();


        DockPanel dock = new DockPanel();
        dock.setSpacing(4);
        dock.add(mainPanel, DockPanel.CENTER);


        dock.setWidth("100%");
        setWidget(dock);

    }

    public EditClosedQuestionDialog(int ownerID, UpdateListener listener) {
        this(ownerID);
        addUpdateListener(listener);
    }

    private void init() {
        Button closeButton = new Button("Close", this);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label(constants.nameLabel() + ": "));
        tempPanel.add(name);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label(constants.questionBodyLabel() + ": "));
        tempPanel.add(body);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label(constants.answersLabel() + ": "));
        tempPanel.add(answer);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label(constants.weightLabel() + ": "));
        weight.setText("0");
        tempPanel.add(weight);
        tempPanel.add(new Label(constants.timeLabel()));
        time.setText("0");
        tempPanel.add(time);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(closeButton);
        tempPanel.add(applyButton);
        mainPanel.add(tempPanel);
    }

    public void setClosedQuestion(ClosedQuestion closedQuestion) {
        this.closedQuestion = closedQuestion;
        clear();
        if (closedQuestion != null) {
            name.setText(closedQuestion.getName());
            body.setText(closedQuestion.getMainText());
            answer.setAnswers(closedQuestion.getAnswersSet());
            weight.setText(closedQuestion.getWeight().toString());
            time.setText(closedQuestion.getTime().toString());
        }
    }

    public void clear() {
        body.setText("");
        answer.setAnswers(null);
        time.setText("0");
    }

    private void createQuestion() {
        TestService.App.getInstance().createClosedQuestion(id, name.getText(), body.getText(), answer.getAnswers(), Integer.parseInt(weight.getText()), Integer.parseInt(time.getText()), this);
    }

    private void updateQuestion() {
        TestService.App.getInstance().updateClosedQuestion(closedQuestion, this);
    }

    public void onClick(Widget sender) {
        if (sender == applyButton) {
            applyButton.setEnabled(false);
            if (closedQuestion != null) updateQuestion();
            else createQuestion();
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
        applyButton.setEnabled(true);
    }


}
