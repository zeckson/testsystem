package ru.ifmo.qd.testing.client.view.admin.manager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.model.tests.ClosedAnswer;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;
import ru.ifmo.qd.testing.client.model.tests.Question;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.12.2007
 * Time: 18:33:30
 * To change this template use File | Settings | File Templates.
 */
public class StatisticPanel extends Composite {
    private AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);
    
    private Question question;
    private HorizontalPanel mainPanel = new HorizontalPanel();
    private VerticalPanel innerPanel = new VerticalPanel();
    private DockPanel contentPanel = new DockPanel();
    private HorizontalPanel tempPanel = new HorizontalPanel();
    private HTML questionBody = new HTML();
    private VerticalPanel rightAnswerPanel = new VerticalPanel();
    private HTML questionLabel = new HTML("<h1>Loading...</h1>");
    private int id;

    public StatisticPanel() {
        initWidget(mainPanel);
        mainPanel.setVisible(false);
        mainPanel.addStyleName("test-panel");
        mainPanel.add(questionLabel);
        mainPanel.setCellHorizontalAlignment(questionLabel, HorizontalPanel.ALIGN_LEFT);
        mainPanel.setCellVerticalAlignment(questionLabel, VerticalPanel.ALIGN_TOP);
        mainPanel.add(innerPanel);
        mainPanel.setCellHorizontalAlignment(innerPanel, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(innerPanel, VerticalPanel.ALIGN_MIDDLE);
        innerPanel.add(contentPanel);
        VerticalPanel fakePanel = new VerticalPanel();
        questionBody.setWordWrap(true);
        //TODO tune questionbody!
        fakePanel.setSpacing(4);
        fakePanel.add(questionBody);
        fakePanel.add(rightAnswerPanel);
        fakePanel.add(tempPanel);
        contentPanel.add(fakePanel, DockPanel.CENTER);
    }


    public void setQuestionID(int questionID) {
        id = questionID;
        loadQuestion();
    }

    private void loadQuestion() {
        TestService.App.getInstance().getQuestion(id, new AsyncCallback() {
            public void onFailure(Throwable caught) {
                Window.alert(alertConstants.loadQuestionFailed());
            }

            public void onSuccess(Object result) {
                question = (Question) result;
                drawQuestion();
            }
        });
    }

    private void drawQuestion() {
        questionLabel.setHTML("<h2>" + question.getName() + "</h2>");
        questionBody.setHTML(constants.questionTextLabel() + question.getMainText());
        VerticalPanel answersPanel = new VerticalPanel();
        if(question instanceof ClosedQuestion){
            Set answers = ((ClosedQuestion) question).getAnswersSet();
            for (Iterator iterator = answers.iterator(); iterator.hasNext();) {
                ClosedAnswer closedAnswer = (ClosedAnswer) iterator.next();
                if(closedAnswer.isRight()){
                    answersPanel.add(new HTML(closedAnswer.getName()));
                }
            }
        }if(question instanceof OpenQuestion){
            answersPanel.add(new HTML(((OpenQuestion) question).getAnswerText()));            
        }
        HorizontalPanel panel = new HorizontalPanel();
        HTML answerLabel = new HTML(constants.rightAnswerLabel());
        panel.add(answerLabel);
        panel.setCellHorizontalAlignment(answerLabel, HorizontalPanel.ALIGN_LEFT);
        panel.setCellVerticalAlignment(answerLabel, VerticalPanel.ALIGN_TOP);
        panel.add(answersPanel);
        panel.setCellHorizontalAlignment(answersPanel, HorizontalPanel.ALIGN_LEFT);
        panel.setCellVerticalAlignment(answersPanel, VerticalPanel.ALIGN_TOP);
        rightAnswerPanel.add(panel);
        mainPanel.setVisible(true);
    }

    public void setAnswers(String answer) {
        VerticalPanel answerVerticalPanel = new VerticalPanel();
        int lastI = -1;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == ';') {
                answerVerticalPanel.add(new HTML(answer.substring(lastI + 1, i)));
                lastI = i;
            }
            if (i == answer.length() - 1 && lastI == -1) {
                answerVerticalPanel.add(new HTML(answer));
            }
        }        
        Label currentAnswerLabel = new Label(constants.userAnswerLabel());
        tempPanel.add(currentAnswerLabel);
        tempPanel.setCellHorizontalAlignment(currentAnswerLabel, HorizontalPanel.ALIGN_LEFT);
        tempPanel.setCellVerticalAlignment(currentAnswerLabel, VerticalPanel.ALIGN_TOP);
        tempPanel.add(answerVerticalPanel);
        tempPanel.setCellHorizontalAlignment(answerVerticalPanel, HorizontalPanel.ALIGN_LEFT);
        tempPanel.setCellVerticalAlignment(answerVerticalPanel, VerticalPanel.ALIGN_TOP);
    }


}
