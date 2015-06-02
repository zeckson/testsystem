package ru.ifmo.qd.testing.server.manager;

import ru.ifmo.qd.testing.client.model.tests.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 25.09.2010
 * Time: 14:31:14
 * To change this template use File | Settings | File Templates.
 */
public interface TestManager<E> extends Executable<E> {
    ClosedQuestion addClosedQuestion(int questionGroupID, String name, String body, Set answers, int weight, int timeSec);

    ClosedAnswer addAnswerToClosedQuestion(ClosedQuestion closedQuestion, String name, boolean right);

    OpenQuestion addOpenQuestion(int id, String name, String body, String answer, int weight, int timeSec);

    OpenQuestion updateOpenQuestion(OpenQuestion openQuestion);

    void updateClosedQuestion(ClosedQuestion question);

    Question getQuestion(int questionID);

    void removeQuestion(int id);

    void removeAnswer(int id);

    List getSubjects();

    Subject getSubjectById(int id);

    void removeSubject(Subject subject);

    Subject createSubject(String name, String description, String authorName);

    List<Block> getBlocks(int id);

    void removeBlock(int id);

    Block createBlock(int subjectID, String name, String desc);

    List<Part> getParts(int id);

    void removePart(int id);

    Part createPart(int id, String name, String desc);

    List<QuestionGroup> getQuestionGroups(int id);

    void removeQuestionGroup(int id);

    QuestionGroup createQuestionGroup(int id, String name, String desc);

    List getQuestions(int id);
}
