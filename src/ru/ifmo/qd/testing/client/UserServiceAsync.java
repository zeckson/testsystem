package ru.ifmo.qd.testing.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.Question;
import ru.ifmo.qd.testing.client.model.tests.Subject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.04.2008
 * Time: 1:54:23
 * To change this template use File | Settings | File Templates.
 */
public interface UserServiceAsync {
    void getAvalaibleTests(AsyncCallback<List<Subject>> asyncCallback);

    void next(AnsweringStatistic statistic, AsyncCallback<Question> asyncCallback);

    void startTest(int id, AsyncCallback<Question> asyncCallback);
}
