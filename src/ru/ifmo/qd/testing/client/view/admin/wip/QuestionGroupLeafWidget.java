/*
package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.userLabel.client.ui.*;
import ru.ifmo.qd.testing.client.model.tests.QuestionGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

*/
/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 29.11.2007
 * Time: 13:47:46
 * To change this template use File | Settings | File Templates.
 */
/*
public class QuestionGroupLeafWidget extends Composite implements EditWidgetProvider {

    private List refreshListeners = new ArrayList();

    HorizontalPanel mainPanel = new HorizontalPanel();
    Label label = new HTML();
    private QuestionGroup questionGroup;

    public QuestionGroupLeafWidget() {
        mainPanel.add(label);
        initWidget(mainPanel);
    }

    private void changeLabel(QuestionGroup questionGroup) {
        label.setText(questionGroup.getName() + " (" + questionGroup.getDescription() + ")");
    }

    public void addRefreshListener(RefreshListener refreshListener) {
        refreshListeners.add(refreshListener);
    }

    public Widget getEditWidget() {
        EditQuestionGroupWidget questionGroupWidget = new EditQuestionGroupWidget(questionGroup);
        questionGroupWidget.addUpdateListener(new UpdateListener() {
            public void updated(Object object) {
                setQuestionGroup((QuestionGroup) object);
                fireRefresh();
            }
        });
        return questionGroupWidget;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void fireRefresh() {
        for (Iterator iterator = refreshListeners.iterator(); iterator.hasNext();) {
            RefreshListener refreshListener = (RefreshListener) iterator.next();
            refreshListener.refresh();
        }
    }

    public void setQuestionGroup(QuestionGroup questionGroup) {
        this.questionGroup = questionGroup;
        changeLabel(this.questionGroup);
    }

    public static interface RefreshListener {
        public void refresh();
    }

}

*/
