package ru.ifmo.qd.testing.client.view.admin.manager;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.AdministrationService;
import ru.ifmo.qd.testing.client.model.statistics.QuestionAssessment;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.view.common.Option;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 24, 2010
 * Time: 10:04:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionOptionContainerPanel<T extends Question> extends OptionContainerPanel<T> implements AsyncCallback {

    public QuestionOptionContainerPanel(int ind, Option<T> tOption) {
        super(ind, tOption);
        T question = tOption.getUserObject();
        AdministrationService.App.getInstance().getQuestionCountedDifficulty(question.getId(), this);
    }

    public void onFailure(Throwable caught) {
        Window.alert("Failed! " + caught.getMessage());
    }

    public void onSuccess(Object result) {
        QuestionAssessment assessment = (QuestionAssessment) result;
        HorizontalPanel panel = new HorizontalPanel();
        subPanel.add(panel);
        NumberFormat numberFormat = NumberFormat.getFormat("0.####");
        Label w = new HTML("\u03b4<sub>c</sub>: " + assessment.getCur_normalized_difficulty() + "(" + numberFormat.format(assessment.getReal_delta()) + ")");
        w.setStyleName("test_difficulty");
        panel.add(w);
        Label counted = new HTML("\u03b4<sub>n</sub>: " + assessment.getNew_normalized_difficulty() + "(" + numberFormat.format(assessment.getReal_delta()) + ")");
        counted.setStyleName("test_difficulty");
        Label delta = new Label("\u0394: " + assessment.getNorm_delta() + "(" + numberFormat.format(assessment.getReal_delta()) + ")");
        delta.setStyleName("test_difficulty");
        panel.add(counted);
        if (assessment.getNorm_delta() > 0) {
            String styleClass = assessment.getNorm_delta() > 1 ? "very-" : "";
            boolean easier = assessment.getCur_normalized_difficulty() > assessment.getNew_normalized_difficulty();
            Label changeSign = new Label(easier ? "\u2193" : "\u2191");
            styleClass = styleClass + (easier ? "easier" : "harder");
            changeSign.setStyleName(styleClass);
//            changeSign.addStyleName("test_difficulty");
            panel.add(changeSign);
        }
        panel.add(delta);
    }
}
