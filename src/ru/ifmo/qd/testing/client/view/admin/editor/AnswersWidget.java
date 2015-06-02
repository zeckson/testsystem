package ru.ifmo.qd.testing.client.view.admin.editor;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.GWT;
import ru.ifmo.qd.testing.client.model.tests.Answer;
import ru.ifmo.qd.testing.client.model.tests.ClosedAnswer;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;

import java.util.*;

public class AnswersWidget extends Composite implements ClickListener {
    private static AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private Set<AnswerLeafWidget> answersSet = new HashSet<AnswerLeafWidget>();

    private VerticalPanel buffer = new VerticalPanel();
    private Button addButton = new Button(constants.addAnswerLabel(), this);


    public AnswersWidget() {
        VerticalPanel panel = new VerticalPanel();
        initWidget((panel));
        panel.add(addButton);
        panel.add(buffer);
        onClick(addButton);
    }

    public void onClick(Widget sender) {
        if (sender == addButton) {
            AnswerLeafWidget answerLeafWidget = new AnswerLeafWidget();
            answersSet.add(answerLeafWidget);
            buffer.add(answerLeafWidget);
        }
    }

    public Set<Answer> getAnswers() {
        Set<Answer> answers = new HashSet<Answer>();
        for (Object anAnswersSet : answersSet) {
            AnswerLeafWidget answerLeafWidget = (AnswerLeafWidget) anAnswersSet;
            ClosedAnswer closedAnswer = new ClosedAnswer();
            closedAnswer.setName(answerLeafWidget.answer.getText());
            closedAnswer.setRight(answerLeafWidget.checkBox.isChecked());
            answers.add(closedAnswer);
        }
        return answers;
    }

    public void setAnswers(Set answers) {
        answersSet = new HashSet<AnswerLeafWidget>();
        buffer.clear();
        if (answers != null) {
            for (Object answer : answers) {
                ClosedAnswer closedAnswer = (ClosedAnswer) answer;
                AnswerLeafWidget answerLeafWidget = new AnswerLeafWidget(closedAnswer);
                buffer.add(answerLeafWidget);
            }
        }
    }

    private class AnswerLeafWidget extends Composite implements ClickListener {

        HorizontalPanel myPanel = new HorizontalPanel();
        CheckBox checkBox = new CheckBox();
        private TextBox answer = new TextBox();
        private Button removeButton = new Button(constants.removeAnswerLabel(), this);

        public AnswerLeafWidget() {
            initWidget(myPanel);
            myPanel.add(checkBox);
            myPanel.add(answer);
            myPanel.add(removeButton);
        }

        public AnswerLeafWidget(ClosedAnswer closedAnswer) {
            this();
            if (closedAnswer.isRight()) checkBox.setChecked(true);
            answer.setText(closedAnswer.getName());
        }

        public void onClick(Widget sender) {
            answersSet.remove(this);
            removeFromParent();
        }
    }


}
