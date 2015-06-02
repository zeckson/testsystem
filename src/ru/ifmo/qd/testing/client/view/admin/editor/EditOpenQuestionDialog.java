package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 13.05.2008
 * Time: 16:11:20
 * To change this template use File | Settings | File Templates.
 */
class EditOpenQuestionDialog extends DialogBox implements ClickListener, AsyncCallback {

    private OpenQuestion openQuestion = null;

    private VerticalPanel mainPanel = new VerticalPanel();
    private int groupID;
    private TextBox name = new TextBox();
    private TextBox weight = new TextBox();
    private TextArea body = new TextArea();
    private TextArea answer = new TextArea();
    private TextBox time = new TextBox();


    private Button applyButton = new Button("OK", this);
    private HorizontalPanel tempPanel;
    private List updateListeners = new ArrayList();

    public EditOpenQuestionDialog(int groupID) {
        this.groupID = groupID;
        setText("Question");
        init();


        DockPanel dock = new DockPanel();
        dock.setSpacing(4);
        dock.add(mainPanel, DockPanel.CENTER);


        dock.setWidth("100%");
        setWidget(dock);

    }

    public EditOpenQuestionDialog(int ownerID, UpdateListener listener) {
        this(ownerID);
        addUpdateListener(listener);
    }

    private void init() {
        Button closeButton = new Button("Close", this);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label("Question Name: "));
        tempPanel.add(name);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label("Question Body: "));
        body.setWidth("200px");
        body.setHeight("100px");
        tempPanel.add(body);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label("Question Answer: "));
        answer.setWidth("200px");
        answer.setHeight("100px");
        tempPanel.add(answer);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(new Label("Weight: "));
        weight.setText("0");
        tempPanel.add(weight);
        tempPanel.add(new Label("Time: "));
        time.setText("0");
        tempPanel.add(time);
        mainPanel.add(tempPanel);
        tempPanel = new HorizontalPanel();
        tempPanel.add(closeButton);
        tempPanel.add(applyButton);
        mainPanel.add(tempPanel);
    }


    private void createQuestion() {
        TestService.App.getInstance().createOpenQuestion(groupID, name.getText(), body.getText(), answer.getText(), Integer.parseInt(weight.getText()), Integer.parseInt(time.getText()), this);
    }

    private void updateQueation() {
        TestService.App.getInstance().updateOpenQuestion(openQuestion, this);
    }

    public void onClick(Widget sender) {
        if (sender == applyButton) {
            applyButton.setEnabled(false);
            if (openQuestion == null) createQuestion();
            else updateQueation();
        }
        hide();
    }

    public void setOpenQuestion(OpenQuestion openQuestion) {
        this.openQuestion = openQuestion;
        clear();
        if (openQuestion != null) {
            name.setText(openQuestion.getName());
            body.setText(openQuestion.getMainText());
            answer.setText(openQuestion.getAnswerText());
            weight.setText(openQuestion.getWeight().toString());
            time.setText(openQuestion.getTime().toString());
        }
    }

    public void clear() {
        body.setText("");
        answer.setText("");
        time.setText("0");
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
