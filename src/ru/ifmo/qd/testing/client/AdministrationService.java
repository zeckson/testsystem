package ru.ifmo.qd.testing.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import ru.ifmo.qd.testing.client.model.statistics.QuestionAssessment;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 24.04.2008
 * Time: 13:00:32
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath("AdministrationService")
public interface AdministrationService extends RemoteService {

    void saveToFile(int userID);

    QuestionAssessment getQuestionCountedDifficulty(int questionId);

    public static class App {
        private static AdministrationServiceAsync ourInstance = null;

        public static synchronized AdministrationServiceAsync getInstance() {
            if (ourInstance == null) {
                ourInstance = (AdministrationServiceAsync) GWT.create(AdministrationService.class);
            }
            return ourInstance;
        }
    }
}
