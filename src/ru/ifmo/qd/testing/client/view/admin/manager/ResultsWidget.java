package ru.ifmo.qd.testing.client.view.admin.manager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.LoginService;
import ru.ifmo.qd.testing.client.AdministrationService;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.users.User;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.12.2007
 * Time: 16:34:00
 * To change this template use File | Settings | File Templates.
 */
public class ResultsWidget extends Composite implements ChangeListener {
    private AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);

    private VerticalPanel mainPanel = new VerticalPanel();
    private VerticalPanel innerPanel = new VerticalPanel();
    private HorizontalPanel userBar = new HorizontalPanel();
    private Label loginLabel = new Label(constants.loginLabel());
    private Label dateLabel = new Label(constants.dateLabel());
    private Label timeLabel = new Label(constants.timeLabel());
    private ListBox dateListBox = new ListBox(false);

    private ListBox usersBox = new ListBox();
    private DockPanel mainDockPanel = new DockPanel();
    private DockPanel fakePanel = new DockPanel();
    private StatisticPanel statisticPanel;
    private List statisticList;
    private List usersList;
    private int userID = -1;
    private Button exportButton = new Button("Export", new ClickListener() {
        public void onClick(Widget widget) {
            AdministrationService.App.getInstance().saveToFile(userID, new AsyncCallback() {
                public void onFailure(Throwable throwable) {
                    Window.alert("������ ���������� � ����");
                }

                public void onSuccess(Object o) {
//                    Window.alert("Saved in \\webapps\\results");
                }
            });
        }
    });

    public ResultsWidget(int id) {
        //TODO link between student & tutor
        mainPanel.add(new HTML("<h1>" + constants.viewResults() + "</h1>"));
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        initWidget(mainPanel);
        mainPanel.add(mainDockPanel);
        mainPanel.setCellHorizontalAlignment(mainDockPanel, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(mainDockPanel, VerticalPanel.ALIGN_MIDDLE);

        usersBox.setEnabled(false);
        usersBox.addChangeListener(this);
        mainDockPanel.add(userBar, DockPanel.NORTH);
        mainDockPanel.setCellHorizontalAlignment(userBar, HorizontalPanel.ALIGN_LEFT);
        loadUserBar();
        mainDockPanel.add(fakePanel, DockPanel.CENTER);
        fakePanel.add(innerPanel, DockPanel.CENTER);
        innerPanel.add(new HTML(constants.loading()));
        loadStatistics();
        loadUsers();
    }

    private void showInfo(String[] userInfo) {
        int id = Integer.parseInt(userInfo[0]);
        userID = id;
        String loginName = userInfo[1];
        List dateList = new ArrayList();

        loginLabel.setText(constants.loginLabel() + loginName);
        loginLabel.setVisible(true);

        for (Iterator iterator = statisticList.iterator(); iterator.hasNext();) {
            AnsweringStatistic statistic = (AnsweringStatistic) iterator.next();
            if (statistic.getPersonID() == id) {
                if (!dateList.contains(new Date(statistic.getDate())))
                    dateList.add(new Date(statistic.getDate()));
            }
        }

        if (dateList.size() != 0) {
            dateListBox.clear();
            dateLabel.setVisible(true);
            for (Iterator iterator = dateList.iterator(); iterator.hasNext();) {
                Date date = (Date) iterator.next();
                dateListBox.addItem(DateTimeFormat.getShortDateTimeFormat().format(date), String.valueOf(date.getTime()));
            }
            dateListBox.setVisible(true);
            dateListBox.setItemSelected(0, true);
            onChange(dateListBox);
        }
    }

    private void loadUserBar() {
        userBar.setStyleName("userbar");
        userBar.setSpacing(5);
        userBar.add(new Label(constants.userLabel()));
        userBar.add(usersBox);
        loginLabel.setVisible(false);
        userBar.add(loginLabel);
        dateLabel.setVisible(false);
        userBar.add(dateLabel);
        dateListBox.setVisible(false);
        dateListBox.addChangeListener(this);
        userBar.add(dateListBox);
        timeLabel.setVisible(false);
        userBar.add(timeLabel);
        exportButton.setVisible(false);
        userBar.add(exportButton);
        userBar.add(new HTML("<a href=\"gwtentrypoint/download?stat=test\">download</a>"));
    }

    private void showAnswers(int userID, long userDate) {
        innerPanel.clear();
        int totalTime = 0;

        for (Iterator iterator = statisticList.iterator(); iterator.hasNext();) {
            AnsweringStatistic statistic = (AnsweringStatistic) iterator.next();
            if (statistic.getPersonID() == userID /*&& statistic.getDate() == userDate*/) {
                statisticPanel = new StatisticPanel();
                innerPanel.add(statisticPanel);
                statisticPanel.setQuestionID(statistic.getQuestionID());
                statisticPanel.setAnswers(statistic.getAnswer());
                totalTime += statistic.getAnswerTime();
            }
        }

        timeLabel.setText(constants.timeLabel() + " " + totalTime / 60 + ":" + totalTime % 60);
        timeLabel.setVisible(true);
//        exportButton.setVisible(true);

    }

    private void loadUsers() {
        LoginService.App.getInstance().getUsersList(new AsyncCallback() {

            public void onFailure(Throwable caught) {
                Window.alert(alertConstants.getUsersFailed());
            }

            public void onSuccess(Object result) {
                usersList = (List) result;
                fillUsersBox();
            }
        });
    }

    private void fillUsersBox() {
        usersBox.clear();
        for (Iterator iterator = usersList.iterator(); iterator.hasNext();) {
            User user = (User) iterator.next();
            usersBox.addItem(user.getName() + " " + user.getSurename(), String.valueOf(user.getId()) + "&" + user.getLoginName());
        }
        if (usersBox.getItemCount() > 0) {
            usersBox.setItemSelected(0, true);
            usersBox.setEnabled(true);
            onChange(usersBox);
        }
    }

    private void loadStatistics() {
        TestService.App.getInstance().getStatistic(new AsyncCallback() {

            public void onFailure(Throwable caught) {
                Window.alert(alertConstants.getStatisticFailed());
            }

            public void onSuccess(Object result) {
                statisticList = (List) result;

            }
        });
    }

    public void onChange(Widget sender) {
        if (sender.equals(usersBox)) {
            String[] userInfo = usersBox.getValue(usersBox.getSelectedIndex()).split("&");
            showInfo(userInfo);
        }
        if (sender.equals(dateListBox)) {
            long userDate = Long.parseLong(dateListBox.getValue(dateListBox.getSelectedIndex()));
            if (userID >= 0) showAnswers(userID, userDate);
        }
    }
}
