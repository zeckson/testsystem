package ru.ifmo.qd.testing.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.ifmo.qd.testing.client.model.statistics.QuestionAssessment;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 24.04.2008
 * Time: 13:00:32
 * To change this template use File | Settings | File Templates.
 */
public interface AdministrationServiceAsync {
    void saveToFile(int userID, AsyncCallback callback);

    void getQuestionCountedDifficulty(int questionId, AsyncCallback<QuestionAssessment> async);
}
