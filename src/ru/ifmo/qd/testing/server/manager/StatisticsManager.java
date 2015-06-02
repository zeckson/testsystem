package ru.ifmo.qd.testing.server.manager;

import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 25.09.2010
 * Time: 14:30:16
 * To change this template use File | Settings | File Templates.
 */
public interface StatisticsManager<E> extends Executable<E> {
    void saveStatistic(AnsweringStatistic statistic);

    List getStatistic();

    List<AnsweringStatistic> getStatisticForQuestion(int id);
}
