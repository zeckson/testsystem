package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.view.admin.editor.EditClosedQuestionWidget;
import ru.ifmo.qd.testing.client.view.admin.editor.EditOpenQuestionWidget;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 3:47:39
 * To change this template use File | Settings | File Templates.
 */
public class QuestionLeafWidget extends Composite implements EditWidgetProvider {
    private HorizontalPanel mainPanel = new HorizontalPanel();
    private Label label = new Label();
    private Question question = null;


    public QuestionLeafWidget() {
        mainPanel.add(label);
        initWidget(mainPanel);
    }

    public void setQuestion(Question question) {
        this.question = question;
        label.setText(question.getName() + "(" + question.getId() + ")");
    }

    public Widget getEditWidget() {
        if (question instanceof OpenQuestion) {
            EditOpenQuestionWidget editWidget = new EditOpenQuestionWidget();
            editWidget.setCurrentQuestion((OpenQuestion) question);
            editWidget.addQuestionUpdateListener(new EditOpenQuestionWidget.QuestionUpdateListener() {
                public void questionUpdated(OpenQuestion question) {
                    setQuestion(question);
                }
            });
            return editWidget;

        } else {
            EditClosedQuestionWidget editWidget = new EditClosedQuestionWidget();
            editWidget.setCurrentQuestion((ClosedQuestion) question);
            editWidget.addQuestionUpdateListener(new EditClosedQuestionWidget.QuestionUpdateListener() {
                public void questionUpdated(ClosedQuestion question) {
                    setQuestion(question);
                }
            });
            return editWidget;
        }

    }
}
