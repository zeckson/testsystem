package ru.ifmo.qd.testing.server.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import ru.ifmo.qd.testing.client.model.tests.*;
import ru.ifmo.qd.testing.server.manager.ICommand;
import ru.ifmo.qd.testing.server.manager.TestManager;

import java.util.*;

public class HibernateTestManager extends HibernateExecutable implements TestManager<Session> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactoryForTests();

    public ClosedQuestion addClosedQuestion(int questionGroupID, String name, String body, Set answers, int weight, int timeSec) {
        ClosedQuestion closedQuestion = new ClosedQuestion();
        closedQuestion.setName(name);
        closedQuestion.setMainText(body);
        closedQuestion.setWeight(Integer.valueOf(weight));
        closedQuestion.setTime(Integer.valueOf(timeSec));
        closedQuestion.setAnswersSet(new HashSet());

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            QuestionGroup questionGroup = (QuestionGroup) findObject(questionGroupID, QuestionGroup.class);
            closedQuestion.setQuestionGroup(questionGroup);
            questionGroup.getQuestions().add(closedQuestion);
            session.update(questionGroup);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }
        Set closedAnswers = new HashSet();
        for (Iterator iterator = answers.iterator(); iterator.hasNext();) {
            ClosedAnswer closedAnswer = (ClosedAnswer) iterator.next();
            closedAnswers.add(addAnswerToClosedQuestion(closedQuestion, closedAnswer.getName(), closedAnswer.isRight()));
        }
        closedQuestion.setAnswersSet(closedAnswers);

        return closedQuestion;
    }

    public ClosedAnswer addAnswerToClosedQuestion(ClosedQuestion closedQuestion, String name, boolean right) {
        ClosedAnswer closedAnswer = new ClosedAnswer();
        closedAnswer.setName(name);
        closedAnswer.setQuestion(closedQuestion);
        closedAnswer.setRight(right);
        closedQuestion.getAnswersSet().add(closedAnswer);

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(closedQuestion);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return closedAnswer;
    }

    public OpenQuestion addOpenQuestion(int id, String name, String body, String answer, int weight, int timeSec) {
        OpenQuestion openQuestion = new OpenQuestion();

        openQuestion.setName(name);
        openQuestion.setMainText(body);
        openQuestion.setAnswerText(answer);
        openQuestion.setWeight(Integer.valueOf(weight));
        openQuestion.setTime(Integer.valueOf(timeSec));


        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            QuestionGroup group = (QuestionGroup) findObject(id, QuestionGroup.class);
            group.getQuestions().add(openQuestion);
            openQuestion.setQuestionGroup(group);
            session.update(group);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return openQuestion;
    }

    public OpenQuestion updateOpenQuestion(OpenQuestion openQuestion) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        openQuestion.setQuestionGroup(getQuestion(openQuestion.getId()).getQuestionGroup());
        try {
            tx = session.beginTransaction();
            session.update(openQuestion);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return openQuestion;

    }

    public void updateClosedQuestion(ClosedQuestion question) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        Set answers = question.getAnswersSet();
        question.setQuestionGroup(getQuestion(question.getId()).getQuestionGroup());
        question.setAnswersSet(new HashSet());
        try {
            tx = session.beginTransaction();
            session.update(question);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        for (Iterator iterator = answers.iterator(); iterator.hasNext();) {
            ClosedAnswer closedAnswer = (ClosedAnswer) iterator.next();
            addAnswerToClosedQuestion(question, closedAnswer.getName(), closedAnswer.isRight());
        }

    }


    public Question getQuestion(int questionID) {
        return (Question) findObject(questionID, Question.class);
    }

    public void removeQuestion(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Question question = (Question) findObject(id, Question.class);
            QuestionGroup questionGroup = (QuestionGroup) findObject(question.getQuestionGroup().getId(), QuestionGroup.class);
            questionGroup.getQuestions().remove(question);
            //TODO delete answers from ClosedQuestion
            session.delete(question);
            session.flush();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }


    }

    public void removeAnswer(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Answer answer = (Answer) findObject(id, Answer.class);
            ClosedQuestion closedQuestion = (ClosedQuestion) findObject(answer.getQuestion().getId(), Question.class);
            closedQuestion.getAnswersSet().remove(answer);
            session.delete(answer);
            session.flush();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return;

    }


    public List getSubjects() {
        List subjects;

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            subjects = session.createCriteria(Subject.class).list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }


        return subjects;
    }

    public Subject getSubjectById(int id) {
        Subject subject;

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            subject = (Subject) findObject(id, Subject.class);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) try {
                tx.rollback();
            } catch (HibernateException ignore) {
            }
            throw he;
        } finally {
            session.close();
        }

        return subject;
    }

    public void removeSubject(Subject subject) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Subject subject1 = (Subject) findObject(subject.getId(), Subject.class);
            session.delete(subject1);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return;
    }

    private Object findObject(int id, Class clazz) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Object object = null;

        try {
            tx = session.beginTransaction();
            object = session.createCriteria(clazz).add(Expression.eq("id", Integer.valueOf(id))).uniqueResult();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return object;
    }

    public Subject createSubject(String name, String description, String authorName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        Subject subject = new Subject();
        subject.setSubjectName(name);
        subject.setDescription(description);
        subject.setCreatorName(authorName);
        subject.setBlocks(new ArrayList());

        try {
            tx = session.beginTransaction();
            session.persist(subject);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        return subject;
    }

    public void removeBlock(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Block block = (Block) findObject(id, Block.class);
            Subject subject = (Subject) findObject(block.getSubject().getId(), Subject.class);
            subject.removeBlock(block);
            session.delete(block);
            session.flush();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

        return;
    }

    public Block createBlock(int subjectID, String name, String desc) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        Block block = new Block();
        block.setBlockName(name);
        block.setDescription(desc);
        block.setParts(new ArrayList());

        try {
            tx = session.beginTransaction();
            Subject subject = (Subject) findObject(subjectID, Subject.class);
            subject.addBlock(block);
            block.setSubject(subject);
            session.update(subject);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        return block;
    }

    public List<Block> getBlocks(int id) {
        Subject subject = getSubjectById(id);

        List<Block> blocks = null;
        if (subject != null) {
            Session session = sessionFactory.openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                blocks = subject.getBlocks();
                tx.commit();
            } catch (HibernateException he) {
                if (tx != null) try {
                    tx.rollback();
                } catch (HibernateException ignore) {
                }
                throw he;
            } finally {
                session.close();
            }
        }
        return blocks;
    }

    public List<Part> getParts(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removePart(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Block block = (Block) findObject(id, Block.class);
            Part part = (Part) findObject(block.getSubject().getId(), Part.class);
            block.removePart(part);
            session.delete(part);
            session.flush();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }
    }

    public Part createPart(int id, String name, String desc) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        Part part = new Part();
        part.setPartName(name);
        part.setDescription(desc);
        part.setQuestionGroups(new ArrayList());

        try {
            tx = session.beginTransaction();
            Block block = (Block) findObject(id, Block.class);
            block.addPart(part);
            part.setBlock(block);
            session.update(block);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        return part;
    }

    public List getQuestionGroups(int id) {

        List qnGroups = new ArrayList(((Part) findObject(id, Part.class)).getQuestionGroups());

        return qnGroups;
    }

    public void removeQuestionGroup(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            QuestionGroup questionGroup = (QuestionGroup) findObject(id, QuestionGroup.class);
            Part part = (Part) findObject(questionGroup.getPart().getId(), Part.class);
            part.removeQuestionGroup(questionGroup);
            session.delete(questionGroup);
            session.flush();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            throw he;
        } finally {
            session.close();
        }

    }

    public QuestionGroup createQuestionGroup(int id, String name, String desc) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        QuestionGroup questionGroup = new QuestionGroup();
        questionGroup.setName(name);
        questionGroup.setDescription(desc);
        questionGroup.setQuestions(new ArrayList());

        try {
            tx = session.beginTransaction();
            Part part = (Part) findObject(id, Part.class);
            part.addQuestionGroup(questionGroup);
            questionGroup.setPart(part);
            session.update(part);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        return questionGroup;
    }

    public List getQuestions(int id) {
        return new ArrayList(((QuestionGroup) findObject(id, QuestionGroup.class)).getQuestions());
    }

    @Override
    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Object executeCommand(ICommand<Session> command) {

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Object res = null;

        try {
            tx = session.beginTransaction();
            res = command.execute(session);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        return res;
    }
}
