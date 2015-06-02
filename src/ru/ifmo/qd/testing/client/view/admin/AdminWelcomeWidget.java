package ru.ifmo.qd.testing.client.view.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.controller.AdminNavigationControllerListener;
import ru.ifmo.qd.testing.client.controller.DefaultNavigationControllerListener;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.model.users.Admin;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.12.2007
 * Time: 13:55:49
 * To change this template use File | Settings | File Templates.
 */
public class AdminWelcomeWidget extends Composite {
    private AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);

    private Admin admin;
    private VerticalPanel operationalPanel = new VerticalPanel();

    public AdminWelcomeWidget(Admin admin) {
        this.admin = admin;
        HorizontalPanel mainPanel = new HorizontalPanel();
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        initWidget(mainPanel);
        VerticalPanel innerPanel = new VerticalPanel();
        mainPanel.add(innerPanel);
        mainPanel.setCellHorizontalAlignment(innerPanel, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(innerPanel, VerticalPanel.ALIGN_MIDDLE);
        HTML greetingText = new HTML();
        greetingText.setHTML(constants.welcomeTo());
        innerPanel.add(greetingText);
        innerPanel.add(new HTML(constants.chooseOperation()));
        innerPanel.add(operationalPanel);
        drawOperationalPanel();

    }

    private void drawOperationalPanel() {

        operationalPanel.add(new Hyperlink(constants.editSubjects(),
                AdminNavigationControllerListener.CREATE_SUBJECTS_TOKEN + admin.getId()));
        operationalPanel.add(new Hyperlink(constants.viewResults(),
                AdminNavigationControllerListener.VIEW_RESULTS + admin.getId()));
        operationalPanel.add(new Hyperlink(constants.questionRebalance(), AdminNavigationControllerListener.REBALANCE_TOKEN));
        operationalPanel.add(new Hyperlink(constants.editUsers(),
                DefaultNavigationControllerListener.REGISTER_TOKEN));
        Hyperlink w = new Hyperlink(constants.logOff(),
                AdminNavigationControllerListener.LOG_OFF);        
        operationalPanel.add(w);

    }
}
