package ru.ifmo.qd.testing.client.view.admin.manager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.i18n.AdminWidgetConstants;
import ru.ifmo.qd.testing.client.i18n.AlertConstants;
import ru.ifmo.qd.testing.client.model.tests.*;
import ru.ifmo.qd.testing.client.view.common.Option;

import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.12.2007
 * Time: 16:34:00
 * To change this template use File | Settings | File Templates.
 */
public class RebuildWidget extends Composite {
    private AdminWidgetConstants constants = (AdminWidgetConstants) GWT.create(AdminWidgetConstants.class);
    private AlertConstants alertConstants = (AlertConstants) GWT.create(AlertConstants.class);
    private VerticalPanel groups;
    private Subject subject;

    public RebuildWidget(int subjectId) {
        //TODO link between student & tutor
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(new HTML("<h1>" + constants.questionRebalance() + "</h1>"));
        mainPanel.setWidth("100%");
        mainPanel.setHeight("100%");
        initWidget(mainPanel);
        groups = new VerticalPanel();

        mainPanel.add(groups);
        mainPanel.setCellHorizontalAlignment(groups, HorizontalPanel.ALIGN_CENTER);
        mainPanel.setCellVerticalAlignment(groups, VerticalPanel.ALIGN_MIDDLE);

        loadSubject(subjectId);
//        countChanges();
    }

    private void loadSubject(int id) {
        TestService.App.getInstance().getSubjectById(id,
                new AsyncCallback<Subject>() {

                    public void onFailure(Throwable caught) {
                        requestFailed(caught);
                    }

                    public void onSuccess(Subject result) {
                        subject=result;
                        fillContent();
                    }                                        
                });
    }

    private void requestFailed(Throwable caught) {
        Window.alert("Failed! " + caught.getMessage());
    }

    private void fillContent() {
        groups.clear();
        Map<Question, Option<Question>> questionsToPanels = new HashMap<Question, Option<Question>>();
        List<Option<Block>> blocks = new ArrayList<Option<Block>>();
        for (Block block : subject.getBlocks()) {
            Option<Block> blockOption = new Option<Block>(block, block.getBlockName());
            for (Part part : block.getParts()) {
                Option<Part> partOption = new Option<Part>(part, part.getPartName());
                for (QuestionGroup group : part.getQuestionGroups()) {
                    Option<QuestionGroup> groupOption = new Option<QuestionGroup>(group, group.getName());
                    for (Question question : group.getQuestions()) {
                        Option<Question> questionOption = new Option<Question>(question, question.getMainText());
                        groupOption.addSuboption(questionOption);
                    }
                    partOption.addSuboption(groupOption);
                }
                blockOption.addSuboption(partOption);
            }
            blocks.add(blockOption);
        }
        for (Option<Block> block : blocks) {
            groups.add(new OptionContainerPanel(1, block));
        }
    }

    private void countChanges() {
        TestService.App.getInstance().getStatistic(new AsyncCallback() {

            public void onFailure(Throwable caught) {
                Window.alert(alertConstants.getStatisticFailed());
            }

            public void onSuccess(Object result) {
            }
        });
    }

}