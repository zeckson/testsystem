package ru.ifmo.qd.testing.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.ifmo.qd.testing.client.TestService;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.tests.*;
import ru.ifmo.qd.testing.server.manager.StatisticsManager;
import ru.ifmo.qd.testing.server.manager.TestManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateStatisticsManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateTestManager;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 06.11.2007
 * Time: 2:02:56
 * To change this template use File | Settings | File Templates.
 */
public class TestServiceImpl extends RemoteServiceServlet implements TestService {

    public Question updateOpenQuestion(OpenQuestion openQuestion) {
        (new HibernateTestManager()).updateOpenQuestion(openQuestion);
        return openQuestion;
    }

    public Question updateClosedQuestion(ClosedQuestion question) {
        (new HibernateTestManager()).updateClosedQuestion(question);

        return getQuestion(question.getId());
    }

    public Question getQuestion(int id) {
        Question question = (new HibernateTestManager()).getQuestion(id);
        question.setQuestionGroup(null);
        if (question instanceof ClosedQuestion) {
            ((ClosedQuestion) question).setAnswersSet(new HashSet(((ClosedQuestion) question).getAnswersSet()));
        }
        return question;
    }


    public List getSubjectsList() {
        List list = getSubjectsUnsafeList();

        for (Object aSubject : list) {
            Subject subject = (Subject) aSubject;
            subject.setBlocks(null);
        }
        return list;
    }

    private List getSubjectsUnsafeList() {
        TestManager manager = new HibernateTestManager();
        List list = manager.getSubjects();
        return list;
    }

    public void removeSubject(Subject subject) {
        TestManager manager = new HibernateTestManager();
        manager.removeSubject(subject);
    }

    public Subject createSubject(String name, String description, String authorName) {
        TestManager manager = new HibernateTestManager();
        Subject subject = manager.createSubject(name, description, authorName);
        subject.setBlocks(null);
        return subject;
    }

    public Subject getSubjectById(int id) {
        TestManager manager = new HibernateTestManager();
        Subject subject = manager.getSubjectById(id);
        for (Block block : subject.getBlocks()) {
            for (Part part : block.getParts()) {
                for (QuestionGroup questionGroup : part.getQuestionGroups()) {
                    for (Question question : questionGroup.getQuestions()) {
                        if (question instanceof ClosedQuestion)
                            ((ClosedQuestion) question).setAnswersSet(new HashSet(((ClosedQuestion) question).getAnswersSet()));
                    }
                    questionGroup.setQuestions(new ArrayList(questionGroup.getQuestions()));
                }
                part.setQuestionGroups(new ArrayList(part.getQuestionGroups()));
            }
            block.setParts(new ArrayList(block.getParts()));
        }
        subject.setBlocks(new ArrayList(subject.getBlocks()));
        return subject;
    }

    public List getBlockList(int subjectID) {
        TestManager manager = new HibernateTestManager();
        return manager.getBlocks(subjectID);
    }

    private ArrayList filterBlocks(ArrayList blocks) {
        for (Iterator iterator = blocks.iterator(); iterator.hasNext();) {
            Block block = (Block) iterator.next();
            block.setParts(null);
            block.setSubject(null);
        }
        return blocks;
    }

    public void removeBlock(int id) {
        TestManager manager = new HibernateTestManager();
        manager.removeBlock(id);
    }

    public Block createBlock(int subjectID, String name, String desc) {
        TestManager manager = new HibernateTestManager();
        Block block = manager.createBlock(subjectID, name, desc);
        block.setParts(null);
        block.setSubject(null);
        return block;
    }

    public List getPartsList(int id) {
        TestManager manager = new HibernateTestManager();
        List list = manager.getSubjects();
        ArrayList parts = null;

        all:
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            Subject subject = (Subject) iterator.next();
            for (Iterator iterator1 = subject.getBlocks().iterator(); iterator1.hasNext();) {
                Block block = (Block) iterator1.next();
                if (block.getId() == id) {
                    parts = new ArrayList(block.getParts());
                    break all;
                }
            }
        }

        return filterParts(parts);  //To change body of implemented methods use File | Settings | File Templates.
    }

    private ArrayList filterParts(ArrayList parts) {
        if (parts.size() == 0) {
            return parts;
        }
        for (Iterator iterator = parts.iterator(); iterator.hasNext();) {
            Part part = (Part) iterator.next();
            part.setQuestionGroups(null);
            part.setBlock(null);
        }
        return parts;
    }

    public void removePart(int id) {
        TestManager manager = new HibernateTestManager();
        manager.removePart(id);
    }

    public Part createPart(int id, String name, String desc) {
        TestManager manager = new HibernateTestManager();
        Part part = manager.createPart(id, name, desc);
        part.setBlock(null);
        part.setQuestionGroups(null);
        return part;
    }

    public List getQnGroupsList(int id) {
        TestManager manager = new HibernateTestManager();
        List qnGroupsList = manager.getQuestionGroups(id);
        return filterQnGroups(qnGroupsList);
    }

    private List filterQnGroups(List qnGroupsList) {
        for (Iterator iterator = qnGroupsList.iterator(); iterator.hasNext();) {
            QuestionGroup questionGroup = (QuestionGroup) iterator.next();
            for (Iterator iterator1 = questionGroup.getQuestions().iterator(); iterator1.hasNext();) {
                Question question = (Question) iterator1.next();
                if (question instanceof ClosedQuestion) {
                    ((ClosedQuestion) question).setAnswersSet(new HashSet(((ClosedQuestion) question).getAnswersSet()));
                }
            }
            questionGroup.setQuestions(new ArrayList(questionGroup.getQuestions()));
            questionGroup.setPart(null);
        }
        return qnGroupsList;
    }

    public void removeQnGroup(int id) {
        TestManager manager = new HibernateTestManager();
        manager.removeQuestionGroup(id);
    }

    public QuestionGroup createQnGroup(int id, String name, String desc) {
        TestManager manager = new HibernateTestManager();
        QuestionGroup questionGroup = manager.createQuestionGroup(id, name, desc);
        questionGroup.setPart(null);
        questionGroup.setQuestions(new ArrayList());
        return questionGroup;
    }

    public OpenQuestion createOpenQuestion(int id, String name, String body, String answer, int weight, int time) {
        TestManager manager = new HibernateTestManager();
        OpenQuestion openQuestion = manager.addOpenQuestion(id, name, body, answer, weight, time);
        openQuestion.setQuestionGroup(null);
        return openQuestion;
    }

    public ClosedQuestion createClosedQuestion(int id, String name, String text, Set answers, int weight, int i) {
        TestManager manager = new HibernateTestManager();
        ClosedQuestion closedQuestion = manager.addClosedQuestion(id, name, text, answers, weight, i);
        closedQuestion.setAnswersSet(new HashSet(closedQuestion.getAnswersSet()));
        closedQuestion.setQuestionGroup(null);
        return closedQuestion;
    }

    public List getQuestionsList(int id) {
        TestManager manager = new HibernateTestManager();
        List questions = manager.getQuestions(id);
        return filterQuestions(questions);
    }

    public void removeQuestion(int id) {
        TestManager manager = new HibernateTestManager();
        manager.removeQuestion(id);
    }

    public void sendStatistic(AnsweringStatistic statistic) {
        StatisticsManager manager = new HibernateStatisticsManager();
        manager.saveStatistic(statistic);
    }

    public List getStatistic() {
        List statistics = new HibernateStatisticsManager().getStatistic();
        return statistics;
    }

    private List filterQuestions(List questions) {
        for (Iterator iterator = questions.iterator(); iterator.hasNext();) {
            Question question = (Question) iterator.next();
            if (question instanceof ClosedQuestion) {
                ClosedQuestion closedQuestion = (ClosedQuestion) question;
                closedQuestion.setAnswersSet(new HashSet(closedQuestion.getAnswersSet()));
            }
//            question.set(new ArrayList(question.getQuestions()));
            question.setQuestionGroup(null);
        }
        return questions;
    }
}