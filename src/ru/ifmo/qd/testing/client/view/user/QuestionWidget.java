package ru.ifmo.qd.testing.client.view.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.UserService;
import ru.ifmo.qd.testing.client.controller.HistoryDispatcher;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.i18n.UserWidgetsConstants;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;
import ru.ifmo.qd.testing.client.model.tests.Question;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.12.2007
 * Time: 7:56:23
 * To change this template use File | Settings | File Templates.
 */
public class QuestionWidget extends Composite implements ClickListener {
    private UserWidgetsConstants constants = (UserWidgetsConstants) GWT.create(UserWidgetsConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);
    private HorizontalPanel mainPanel = new HorizontalPanel();
    private VerticalPanel innerPanel = new VerticalPanel();
    private DockPanel fakePanel = new DockPanel();
    private Question question;
    private Composite questionPanel;
    private DockPanel buttonsPanel = new DockPanel();
    private Button nextButton = new Button(constants.nextButton(), this);
    private Button backButton = new Button(constants.prevButton(), this);
    private Label timerLabel = new Label(constants.timeToEnd());
    private Label timer = new HTML("00 : 00");
    private QuestionTimer questionTimer;


    public QuestionWidget() {
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        initWidget(mainPanel);
        mainPanel.add(innerPanel);
        mainPanel.setCellHorizontalAlignment(innerPanel, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(innerPanel, VerticalPanel.ALIGN_MIDDLE);
        innerPanel.add(fakePanel);
        backButton.setEnabled(false);
        buttonsPanel.add(backButton, DockPanel.WEST);
        HorizontalPanel tempPanel = new HorizontalPanel();
        tempPanel.add(timerLabel);
        tempPanel.add(timer);
        buttonsPanel.add(tempPanel, DockPanel.CENTER);
        buttonsPanel.add(nextButton, DockPanel.EAST);
        buttonsPanel.setWidth("100%");
        innerPanel.add(buttonsPanel);


    }

    public void setQuestion(Question question) {
        fakePanel.clear();
        this.question = question;
        if (question instanceof ClosedQuestion) {
            questionPanel = new ClosedQuestionPanel((ClosedQuestion) question);
        } else if (question instanceof OpenQuestion) {
            questionPanel = new OpenQuestionPanel((OpenQuestion) question);
        } else {

        }
        questionTimer = new QuestionTimer(question.getTime().intValue());
        if (question.getTime().intValue() > 0) {

            questionTimer.scheduleRepeating(1000);
        }

        fakePanel.add(questionPanel, DockPanel.CENTER);
        nextButton.setEnabled(true);
    }

    public void onClick(Widget sender) {
        if (sender == nextButton) {
            nextButton.setEnabled(false);
            questionTimer.cancel();
            if (questionPanel instanceof OpenQuestionPanel) {
                AnsweringStatistic statistic = ((OpenQuestionPanel) questionPanel).getStatistic();
                statistic.setAnswerTime(question.getTime().intValue() - questionTimer.getTime());
                sendToServer(statistic);
            } else if (questionPanel instanceof ClosedQuestionPanel) {
                AnsweringStatistic statistic = ((ClosedQuestionPanel) questionPanel).getStatistic();
                statistic.setAnswerTime(question.getTime().intValue() - questionTimer.getTime());
                sendToServer(statistic);
            } else {
                nextButton.setEnabled(true);
            }
        }
    }

    private void sendToServer(AnsweringStatistic statistic) {
        UserService.App.getInstance().next(statistic, new AsyncCallback() {

            public void onFailure(Throwable caught) {
                Window.alert(alertConstants.sendStatisticFailed());
                nextButton.setEnabled(true);
            }

            public void onSuccess(Object result) {
                if (result == null) {
                    HistoryDispatcher.newItem("endTest");
                    return;
                }
                setQuestion((Question) result);

            }
        });
    }

    private class QuestionTimer extends Timer {
        private int time;

        public QuestionTimer(int t) {

            timer.setStyleName("timer");

            if (--t < 10) {
                timer.setText(t / 60 + ":0" + t % 60);
            } else {
                timer.setText(t / 60 + ":" + t % 60);
            }
            this.time = t;
            if (t <= 0) {
                timer.setText(constants.unlimitedLabel());
                this.time = 3600;
            }

        }

        public void run() {
            if (--time < 10) {
                timer.setText(time / 60 + ":0" + time % 60);
                if (time % 2 == 0)
                    timer.setStyleName("timer");
                else
                    timer.setStyleName("timer-alarm");
            } else {
                timer.setText(time / 60 + ":" + time % 60);
            }

            if (time < 1) {
                nextButton.click();
            }

        }

        public int getTime() {
            return time;
        }
    }
}
