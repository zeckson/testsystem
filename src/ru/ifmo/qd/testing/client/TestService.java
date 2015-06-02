package ru.ifmo.qd.testing.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.*;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 2:02:56
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath("TestService")
public interface TestService extends RemoteService {

    Question updateOpenQuestion(OpenQuestion openQuestion);

    Question updateClosedQuestion(ClosedQuestion question);

    Question getQuestion(int id);

    List<Subject> getSubjectsList();

    void removeSubject(Subject subject);

    Subject createSubject(String name, String description, String authorName);

    List<Block> getBlockList(int subjectID);

    void removeBlock(int id);

    Block createBlock(int subjectID, String name, String desc);

    List<Part> getPartsList(int partID);

    void removePart(int id);

    Part createPart(int partID, String name, String desc);

    List<QuestionGroup> getQnGroupsList(int id);

    void removeQnGroup(int id);

    QuestionGroup createQnGroup(int id, String name, String desc);

    OpenQuestion createOpenQuestion(int id, String name, String body, String answer, int weight, int time);

    ClosedQuestion createClosedQuestion(int id, String name, String text, Set<Answer> answers, int weight, int i);

    List<Question> getQuestionsList(int id);

    void removeQuestion(int id);

    void sendStatistic(AnsweringStatistic statistic);

    List<AnsweringStatistic> getStatistic();

    Subject getSubjectById(int id);

    /**
     * Utility/Convenience class.
     * Use TestService.App.getInstance() to access static instance of GWTRemoteServiceAsync
     */
    public static class App {
        private static TestServiceAsync ourInstance = null;

        public static synchronized TestServiceAsync getInstance() {
            if (ourInstance == null) {
                ourInstance = (TestServiceAsync) GWT.create(TestService.class);
            }
            return ourInstance;
        }
    }
}
