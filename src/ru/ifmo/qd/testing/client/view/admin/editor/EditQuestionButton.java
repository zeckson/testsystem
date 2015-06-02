package ru.ifmo.qd.testing.client.view.admin.editor;

import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.model.tests.OpenQuestion;
import ru.ifmo.qd.testing.client.model.tests.ClosedQuestion;
import ru.ifmo.qd.testing.client.view.admin.wip.UpdateListener;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 18.05.2008
 * Time: 15:30:39
 * To change this template use File | Settings | File Templates.
 */
public class EditQuestionButton extends Button implements ClickListener {
    private static AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    Question question = null;
    CreateQuestionsWidget createQuestionsWidget;
    public EditQuestionButton(Question question, CreateQuestionsWidget createQuestionsWidget) {
        super(constants.editButton());
        this.question = question;
        this.createQuestionsWidget = createQuestionsWidget;
        addClickListener(this);
    }

    public void onClick(Widget widget) {
        setEnabled(false);
        if(question instanceof OpenQuestion){
            createQuestionsWidget.getOpenQuestionDialog().setOpenQuestion((OpenQuestion) question);
            createQuestionsWidget.getOpenQuestionDialog().addUpdateListener(new UpdateListener() {
                public void updated(Object object) {
                    question = (Question) object;

                }

            });
            createQuestionsWidget.getOpenQuestionDialog().show();
            createQuestionsWidget.getOpenQuestionDialog().center();
            setEnabled(true);
        }else if(question instanceof ClosedQuestion){
            createQuestionsWidget.getClosedQuestionDialog().setClosedQuestion((ClosedQuestion) question);
            createQuestionsWidget.getClosedQuestionDialog().addUpdateListener(new UpdateListener() {
                public void updated(Object object) {
                    question = (Question) object;
                }

            });
            createQuestionsWidget.getClosedQuestionDialog().show();
            createQuestionsWidget.getClosedQuestionDialog().center();
            setEnabled(true);
        }
    }
}
