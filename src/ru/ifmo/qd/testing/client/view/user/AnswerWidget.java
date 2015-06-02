package ru.ifmo.qd.testing.client.view.user;

import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.model.tests.ClosedAnswer;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.12.2007
 * Time: 10:54:56
 * To change this template use File | Settings | File Templates.
 */
public class AnswerWidget extends Composite {

    ClosedAnswer closedAnswer;
    private HorizontalPanel mainPanel = new HorizontalPanel();
    private CheckBox checkBox = new CheckBox();
    private Label name;

    public AnswerWidget(ClosedAnswer closedAnswer) {
        this.closedAnswer = closedAnswer;
        mainPanel.addStyleName("testing-closedAnswerPanel");
        mainPanel.setSpacing(5);
        initWidget(mainPanel);
        mainPanel.add(checkBox);
        name = new HTML(closedAnswer.getName());
        mainPanel.add(name);
    }

    public boolean isChecked(){
        return checkBox.isChecked();
    }

    public ClosedAnswer getAnswer() {
        return closedAnswer;

    }
}
