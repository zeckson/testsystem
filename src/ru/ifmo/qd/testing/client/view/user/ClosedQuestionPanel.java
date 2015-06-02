package ru.ifmo.qd.testing.client.view.user;

import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.ClosedAnswer;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.12.2007
 * Time: 8:06:31
 * To change this template use File | Settings | File Templates.
 */
public class ClosedQuestionPanel extends Composite {


    private ClosedQuestion closedQuestion;
    private List answers = new ArrayList();
    private VerticalPanel mainPanel = new VerticalPanel();
    private VerticalPanel innerPanel = new VerticalPanel();
    private DockPanel contentPanel = new DockPanel();
    private FlexTable answerFlexTable = new FlexTable();
    private HTML questionBody;
    private Label questionLabel;

    public ClosedQuestionPanel(ClosedQuestion closedQuestion) {

        initWidget(mainPanel);
        this.closedQuestion = closedQuestion;
        questionLabel = new HTML("<h1>" + closedQuestion.getName() + "</h1>");
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        mainPanel.add(innerPanel);
        mainPanel.setCellHorizontalAlignment(innerPanel, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(innerPanel, VerticalPanel.ALIGN_MIDDLE);
        innerPanel.add(contentPanel);
        contentPanel.add(questionLabel, DockPanel.NORTH);
        VerticalPanel fakePanel = new VerticalPanel();

        //TODO tune questionbody!
        questionBody = new HTML(closedQuestion.getMainText());
        fakePanel.setSpacing(10);
        fakePanel.add(questionBody);
//        answerFlexTable.addStyleName("testing-closeAnswerFlexTable");
        fakePanel.add(answerFlexTable);
        contentPanel.add(fakePanel, DockPanel.CENTER);
        drawAnswerFlexTable();

    }

    private void drawAnswerFlexTable() {
        int rows = 0;
        for (Iterator iterator = closedQuestion.getAnswersSet().iterator(); iterator.hasNext();) {
            ClosedAnswer closedAnswer = (ClosedAnswer) iterator.next();
            AnswerWidget answerWidget = new AnswerWidget(closedAnswer);
            answers.add(answerWidget);
            answerFlexTable.setWidget(rows++, 0, answerWidget);
        }
    }

    public AnsweringStatistic getStatistic() {
        int counter = 0;
        AnsweringStatistic statistic = new AnsweringStatistic();
        statistic.setQuestionID(closedQuestion.getId());
        statistic.setScore(closedQuestion.getWeight().doubleValue());
        StringBuffer buffer = new StringBuffer();
        for (Iterator iterator = answers.iterator(); iterator.hasNext();) {
            AnswerWidget answerWidget = (AnswerWidget) iterator.next();
            ClosedAnswer answer = answerWidget.getAnswer();
            if (answerWidget.isChecked()) {
                if(!answer.isRight())statistic.setScore(0);
                buffer.append(answer.getName() + ";");
                counter++;
            }
        }
        if(counter == 0) statistic.setScore(0);
        statistic.setAnswer(buffer.toString());
        statistic.setDate((new Date()).getTime());
        statistic.setAnswerTime(0);
        return statistic;
    }
}
