package ru.ifmo.qd.testing.server.model;

import ru.ifmo.qd.testing.client.model.tests.Question;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 30.03.2008
 * Time: 3:50:31
 * To change this template use File | Settings | File Templates.
 */
public interface TestGenerator {


    public Question next();

    public Question next(double scores);

    public Question prev();

    public boolean hasNext();

    public boolean hasPrev();

    public double getScores();
}
