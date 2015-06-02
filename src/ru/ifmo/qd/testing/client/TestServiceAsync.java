package ru.ifmo.qd.testing.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
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
public interface TestServiceAsync {


    void updateOpenQuestion(OpenQuestion openQuestion, AsyncCallback<Question> async);

    void updateClosedQuestion(ClosedQuestion question, AsyncCallback<Question> async);

    void getQuestion(int id, AsyncCallback<Question> async);

    /**
     */

    void getSubjectsList(AsyncCallback<List<Subject>> async);

    void removeSubject(Subject subject, AsyncCallback async);

    void createSubject(String name, String description, String authorName, AsyncCallback<Subject> async);

    /**
     * @param async
     */
    void getBlockList(int subjectID, AsyncCallback<List<Block>> async);

    void removeBlock(int id, AsyncCallback async);

    void createBlock(int subjectID, String name, String desc, AsyncCallback<Block> async);

    /**
     */
    void getPartsList(int partID, AsyncCallback<List<Part>> async);

    void removePart(int id, AsyncCallback async);

    void createPart(int partID, String name, String desc, AsyncCallback<Part> async);

    /**
     */
    void getQnGroupsList(int id, AsyncCallback<List<QuestionGroup>> async);

    void removeQnGroup(int id, AsyncCallback async);

    void createQnGroup(int id, String name, String desc, AsyncCallback<QuestionGroup> async);

    void createOpenQuestion(int id, String name, String body, String answer, int weight, int time, AsyncCallback<OpenQuestion> async);

    void createClosedQuestion(int id, String name, String text, Set<Answer> answers, int weight, int i, AsyncCallback<ClosedQuestion> async);

    /**
     */
    void getQuestionsList(int id, AsyncCallback<List<Question>> async);

    void removeQuestion(int id, AsyncCallback async);

    void sendStatistic(AnsweringStatistic statistic, AsyncCallback async);
    
    void getStatistic(AsyncCallback<List<AnsweringStatistic>> async);

    void getSubjectById(int id, AsyncCallback<Subject> async);
}
