package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.InternationalUserInterface;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 5:48:55
 * To change this template use File | Settings | File Templates.
 */
public class EditClosedQuestionWidget extends Composite {
    private InternationalUserInterface userInterface = (InternationalUserInterface) GWT.create(InternationalUserInterface.class);

    private VerticalPanel mainPanel = new VerticalPanel();
    private HorizontalPanel bufferPanel;
    private TextBox questionNameTextBox = new TextBox();

    private TextArea questionTextArea = new TextArea();
    //    private AnswerWidget answerTextArea = null;
    private TextBox weightTextBox = new TextBox();
    private TextBox timeTextBox = new TextBox();
    private Button uploadButton = new Button(userInterface.uploadButtonLabel());
    private List questionUpdateListeners = new ArrayList();

    private ClosedQuestion closedQuestion = null;

    public EditClosedQuestionWidget() {


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
//        bufferPanel.add(answerTextArea);
        mainPanel.add(bufferPanel);
        bufferPanel = new HorizontalPanel();
        bufferPanel.add(new HTML(userInterface.questionWeightLabel()));
        bufferPanel.add(weightTextBox);
        bufferPanel.add(new HTML(userInterface.questionTimeLabel()));
        bufferPanel.add(timeTextBox);
        mainPanel.add(bufferPanel);
        uploadButton.addClickListener(new ClickListener() {
            public void onClick(Widget widget) {
                ClosedQuestion question = getCurrentQuestion();
                TestService.App.getInstance().updateClosedQuestion(question, new AsyncCallback() {
                    public void onFailure(Throwable throwable) {
                        Window.alert("can't load to base!");
                    }

                    public void onSuccess(Object object) {
//                        AdminNavigationControllerListener.edit();
                        fireQuestionUpdated(closedQuestion);
                    }
                });
            }
        });

        mainPanel.add(uploadButton);
    }

    public void setCurrentQuestion(ClosedQuestion closedQuestion) {
        this.closedQuestion = closedQuestion;
        questionNameTextBox.setText(closedQuestion.getName());
        questionTextArea.setText(closedQuestion.getMainText());
//        answerTextArea.setText(closedQuestion.getAnswerText());
        weightTextBox.setText(String.valueOf(closedQuestion.getWeight()));
        timeTextBox.setText(String.valueOf(closedQuestion.getTime()));
    }


    public ClosedQuestion getCurrentQuestion() {
        closedQuestion.setName(questionNameTextBox.getText());
        closedQuestion.setMainText(questionTextArea.getText());
//        closedQuestion.setAnswerText(answerTextArea.getText());
        closedQuestion.setWeight(Integer.valueOf(weightTextBox.getText()));
        closedQuestion.setTime(Integer.valueOf(timeTextBox.getText()));
        return closedQuestion;
    }

    public void addQuestionUpdateListener(QuestionUpdateListener listener) {
        questionUpdateListeners.add(listener);
    }

    protected void fireQuestionUpdated(ClosedQuestion question) {
        for (Iterator i = questionUpdateListeners.iterator(); i.hasNext();) {
            QuestionUpdateListener questionUpdateListener = (QuestionUpdateListener) i.next();
            questionUpdateListener.questionUpdated(question);
        }
    }

    public static interface QuestionUpdateListener {
        void questionUpdated(ClosedQuestion question);
    }
}
