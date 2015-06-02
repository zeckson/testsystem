package ru.ifmo.qd.testing.client.view.user;

import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.12.2007
 * Time: 8:03:19
 * To change this template use File | Settings | File Templates.
 */
public class OpenQuestionPanel extends Composite {
    private OpenQuestion openQuestion;
    private VerticalPanel mainPanel = new VerticalPanel();
    private VerticalPanel innerPanel = new VerticalPanel();
    private DockPanel contentPanel = new DockPanel();
    private TextArea answerTextArea = new TextArea();
    private HTML questionBody;
    private Label questionLabel;

    public OpenQuestionPanel(OpenQuestion openQuestion) {
        initWidget(mainPanel);
        this.openQuestion = openQuestion;
        questionLabel = new HTML("<h1>" + openQuestion.getName() + "</h1>");
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        mainPanel.add(innerPanel);
        mainPanel.setCellHorizontalAlignment(innerPanel, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(innerPanel, VerticalPanel.ALIGN_MIDDLE);
        innerPanel.add(contentPanel);
        contentPanel.add(questionLabel, DockPanel.NORTH);
        VerticalPanel fakePanel = new VerticalPanel();
//        questionBody.setWordWrap(false);
        //TODO tune questionbody!
        questionBody = new HTML(openQuestion.getMainText());
        fakePanel.setSpacing(10);
        fakePanel.add(questionBody);
        answerTextArea.addStyleName("testing-openAnswerBox");
        fakePanel.add(answerTextArea);
        contentPanel.add(fakePanel, DockPanel.CENTER);
    }

    public AnsweringStatistic getStatistic() {
        AnsweringStatistic statistic = new AnsweringStatistic();
        statistic.setQuestionID(openQuestion.getId());      
        statistic.setAnswer(answerTextArea.getText());
        statistic.setDate((new Date()).getTime());
        statistic.setScore(0);
        if(statistic.getAnswer().equalsIgnoreCase(openQuestion.getAnswerText())) statistic.setScore(openQuestion.getWeight().doubleValue());
        return statistic;
    }
}
