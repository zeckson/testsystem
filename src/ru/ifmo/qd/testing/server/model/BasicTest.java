package ru.ifmo.qd.testing.server.model;

import ru.ifmo.qd.testing.client.model.tests.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 31.03.2008
 * Time: 22:24:46
 * To change this template use File | Settings | File Templates.
 */
public class BasicTest implements TestGenerator {

    private List testSequence;
    private int index;
    private double scores = 0;
    private Question next, prev;

    private BasicTest() {
        index = 0;
        next = null;
        prev = null;
    }

    public BasicTest(Subject subject) {
        this();
        testSequence = new ArrayList();
        for (Iterator iterator = subject.getBlocks().iterator(); iterator.hasNext();) {
            Block block = (Block) iterator.next();
            testSequence.addAll(generateTest(block));
        }
    }

    public BasicTest(Block block) {
        this();
        testSequence = generateTest(block);
    }

    private List generateTest(Block block) {
        List list = new ArrayList();
        for (Iterator iterator2 = block.getParts().iterator(); iterator2.hasNext();) {
            Part part = (Part) iterator2.next();
            for (Iterator iterator3 = part.getQuestionGroups().iterator(); iterator3.hasNext();) {
                QuestionGroup questionGroup = (QuestionGroup) iterator3.next();
                Random random = new Random();
                List questions = questionGroup.getQuestions();
                list.add(questions.get(random.nextInt(questions.size())));
            }
        }
        return list;
    }

    public Question next() {

        next = (Question) testSequence.get(index++);
        next.setQuestionGroup(null);
        if (next instanceof ClosedQuestion) {
            ClosedQuestion closedQuestion = (ClosedQuestion) next;
            closedQuestion.setAnswersSet(new HashSet(closedQuestion.getAnswersSet()));
            return closedQuestion;
        }
        prev = next;
        return next;
    }

    public Question next(double scores) {
        scores += scores;
        return next();
    }

    public Question prev() {
        next = prev;
        prev = (Question) testSequence.get(index);
        return prev;
    }

    public boolean hasNext() {
        return testSequence.size() > index;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean hasPrev() {
        return index > 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getScores() {
        return scores;
    }
}
