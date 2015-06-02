package ru.ifmo.qd.testing.client.view.admin.manager;

import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.model.tests.Block;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.view.common.Option;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 24, 2010
 * Time: 3:58:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class OptionContainerPanel<T extends Serializable> extends VerticalPanel {
    protected Panel mainPanel;
    protected VerticalPanel subPanel = new VerticalPanel();
    protected Option<T> option;

    public OptionContainerPanel(int ind, Option<T> option) {
        this.option = option;
        mainPanel = this.init(ind);
        this.add(mainPanel);
        Label title = new HTML("<h" + ind + ">" + this.option.getName() + "</h" + ind + ">");
        subPanel.add(title);
        mainPanel.add(subPanel);
        addToPanel(++ind, option.getSuboptions());
    }

    protected Panel init(int indent){
        HorizontalPanel panel = new HorizontalPanel();
        Label l = new Label();
        panel.add(l);
        l.setWidth(50 * (indent - 1) + "px");
        return panel;
    }

    public void addToPanel(int ind, List<Option> options) {
        for (Option<T> option : options) {
            OptionContainerPanel w;
            if (option.getUserObject() instanceof Question) {
                w = new QuestionOptionContainerPanel(ind ,option);
            } else {
                w = new OptionContainerPanel(ind, option);
            }
            this.add(w);
        }
    }

}
