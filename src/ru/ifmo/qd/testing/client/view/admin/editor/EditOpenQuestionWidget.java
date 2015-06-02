package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.InternationalUserInterface;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 3:16:09
 * To change this template use File | Settings | File Templates.
 */
public class EditOpenQuestionWidget extends Composite {
    private InternationalUserInterface userInterface = (InternationalUserInterface) GWT.create(InternationalUserInterface.class);

    private VerticalPanel mainPanel = new VerticalPanel();
    private HorizontalPanel bufferPanel;
    private TextBox questionNameTextBox = new TextBox();

    private TextArea questionTextArea = new TextArea();
    private TextArea answerTextArea = new TextArea();
    private TextBox weightTextBox = new TextBox();
    private TextBox timeTextBox = new TextBox();
    private Button uploadButton = new Button(userInterface.uploadButtonLabel());
    private List questionUpdateListeners = new ArrayList();

    private OpenQuestion openQuestion = null;

    public EditOpenQuestionWidget() {

        initWidget(mainPanel);
        bufferPanel = new HorizontalPanel();
        bufferPanel.add(new HTML(userInterface.nameLabel()));
        bufferPanel.add(questionNameTextBox);
        mainPanel.add(bufferPanel);
        bufferPanel = new HorizontalPanel();
        bufferPanel.add(new HTML(userInterface.questionBodyLabel()));
        bufferPanel.add(questionTextArea);
        mainPanel.add(bufferPanel);
        bufferPanel = new HorizontalPanel();
        bufferPanel.add(new HTML(userInterface.questionAnswerLabel()));
        bufferPanel.add(answerTextArea);
        mainPanel.add(bufferPanel);
        bufferPanel = new HorizontalPanel();
        bufferPanel.add(new HTML(userInterface.questionWeightLabel()));
        bufferPanel.add(weightTextBox);
        bufferPanel.add(new HTML(userInterface.questionTimeLabel()));
        bufferPanel.add(timeTextBox);
        mainPanel.add(bufferPanel);
        uploadButton.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                OpenQuestion question = getCurrentQuestion();
                TestService.App.getInstance().updateOpenQuestion(question, new AsyncCallback() {
                    public void onFailure(Throwable throwable) {
                        Window.alert("can't load to base!");
                    }

                    public void onSuccess(Object object) {
//                        AdminNavigationControllerListener.edit();
                        fireQuestionUpdated(openQuestion);
                    }
                });
            }
        });

        mainPanel.add(uploadButton);
    }

    public void setCurrentQuestion(OpenQuestion openQuestion) {
        this.openQuestion = openQuestion;
        questionNameTextBox.setText(openQuestion.getName());
        questionTextArea.setText(openQuestion.getMainText());
        answerTextArea.setText(openQuestion.getAnswerText());
        weightTextBox.setText(String.valueOf(openQuestion.getWeight()));
        timeTextBox.setText(String.valueOf(openQuestion.getTime()));
    }


    public OpenQuestion getCurrentQuestion() {
        openQuestion.setName(questionNameTextBox.getText());
        openQuestion.setMainText(questionTextArea.getText());
        openQuestion.setAnswerText(answerTextArea.getText());
        openQuestion.setWeight(Integer.valueOf(weightTextBox.getText()));
        openQuestion.setTime(Integer.valueOf(timeTextBox.getText()));
        return openQuestion;
    }

    public void addQuestionUpdateListener(QuestionUpdateListener listener) {
        questionUpdateListeners.add(listener);
    }

    protected void fireQuestionUpdated(OpenQuestion question) {
        for (Iterator i = questionUpdateListeners.iterator(); i.hasNext();) {
            QuestionUpdateListener questionUpdateListener = (QuestionUpdateListener) i.next();
            questionUpdateListener.questionUpdated(question);
        }
    }

    public static interface QuestionUpdateListener {
        void questionUpdated(OpenQuestion question);
    }
}
