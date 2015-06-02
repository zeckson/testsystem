package ru.ifmo.qd.testing.server;

import org.hibernate.Session;
import ru.ifmo.qd.testing.client.AdministrationService;
import ru.ifmo.qd.testing.client.model.statistics.AnsweringStatistic;
import ru.ifmo.qd.testing.client.model.statistics.QuestionAssessment;
import ru.ifmo.qd.testing.client.model.tests.*;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.users.User;
import ru.ifmo.qd.testing.server.manager.AuthorizationManager;
import ru.ifmo.qd.testing.server.manager.ICommand;
import ru.ifmo.qd.testing.server.manager.StatisticsManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateAuthorizationManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateStatisticsManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateTestManager;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 24.04.2008
 * Time: 13:00:32
 * To change this template use File | Settings | File Templates.
 */
public class AdministrationServiceImpl extends RemoteServiceServlet implements AdministrationService {
    private static Map map = new HashMap();

    public void saveToFile(int userID, OutputStream stream, StringBuilder stat) {
        List statistics = getStatistics();
        AuthorizationManager manager = new HibernateAuthorizationManager();
        PrintStream writer = new PrintStream(stream);
        User user = manager.getUser(userID);


        List<AnsweringStatistic> statisticsCurrentUser = new ArrayList<AnsweringStatistic>();
        for (Object statistisc : statistics) {
            AnsweringStatistic answeringStatistic = (AnsweringStatistic) statistisc;
            if (answeringStatistic.getPersonID() == user.getId()) {
                statisticsCurrentUser.add(answeringStatistic);
            }
        }

        writer.println("Статистика прохождения тестов пользователя: " + user.getSurename() + " "
                + user.getName() + "Идентификатор: " + userID);
//        writer.print("Отвеченные вопросы: " + statisticsCurrentUser);
        List questionsList = new ArrayList();
        int i = 0;
        int time = 0;
        int totalTime = 0;
        double scores = 0;
        int numSr = 0;
        int numLight = 0;
        int answered = 0;
        int numHard = 0;
        cicle:
        for (AnsweringStatistic answeringStatistic : statisticsCurrentUser) {
            i++;

            Question question = new HibernateTestManager().getQuestion(answeringStatistic.getQuestionID());
            if (map.containsKey(question.getMainText())) {
                QuestionStatistics questionStatistics = (QuestionStatistics) map.get(question.getMainText());
                questionStatistics.setTryAnswer(questionStatistics.getTryAnswer() + 1);
                questionStatistics.setDifficulty(question.getWeight());
                if (answeringStatistic.getScore() > 0)
                    questionStatistics.setAnswered(questionStatistics.getAnswered() + 1);
                map.put(question.getMainText(), questionStatistics);
            } else {
                QuestionStatistics questionStatistics = new QuestionStatistics();

                questionStatistics.setTryAnswer(questionStatistics.getTryAnswer() + 1);
                if (answeringStatistic.getScore() > 0)
                    questionStatistics.setAnswered(questionStatistics.getAnswered() + 1);
                map.put(question.getMainText(), questionStatistics);
            }
            if (question == null) return;
            questionsList.add(question);
            double weight = 0;
            writer.println("Вопрос № " + i + "\tВес вопроса: " + (weight = question.getWeight().doubleValue()) + "\tВремя потраченное на ответ: " + answeringStatistic.getAnswerTime() / 60 + ":" + answeringStatistic.getAnswerTime() % 60);
            writer.println("Текст вопроса: \n" + question.getMainText());
            writer.println("Правильный ответ: " + getRightAnswer(question));
            writer.println("Данный ответ: " + answeringStatistic.getAnswer());
            writer.println("Набранные баллы: " + answeringStatistic.getScore() + "\tДата прохождения: " + new Date(answeringStatistic.getDate()).toString());
            writer.println("--------------------------------------------------------------");
            if (weight < 3 && weight > 1 && answeringStatistic.getScore() == weight) {
                numSr += 1;
            } else if (weight < 2 && answeringStatistic.getScore() == weight) {
                numLight += 1;
            }
            time += answeringStatistic.getAnswerTime();
            totalTime += question.getTime().intValue();
            scores += answeringStatistic.getScore();
            answered += answeringStatistic.getScore() > 0 ? 1 : 0;
            numHard += question.getWeight().doubleValue() == 3d && answeringStatistic.getScore() == weight ? 1 : 0;
        }
        writer.println("Всего вопросов задано: " + questionsList.size() + "\tиз них правильно отвеченных: " + answered);
        writer.println("Отвечено на сложных вопросов: " + numHard + " средних: " + numSr + " легких: " + numLight);
        writer.println("Всего набранно баллов: " + scores + " из " + 30d + " процент: " + Math.round(scores / 30d * 100) + " пересчитанный процент: " + Math.round(((scores / 30d) - numSr * 0.014 - numLight * 0.0266) * 100));
        writer.println("Всего потрачено времени:  " + time / 60 + ":" + time % 60 + " из " + totalTime / 60 + ":" + totalTime % 60);
        stat.append(String.format("%s\t%.4f\t%.4f\t%d\t%d%n", new Object[]{new StringBuffer().append(user.getSurename()).append(" ").append(user.getName()).toString(), new Double((scores / 30d) * 100), new Double(((scores / 30d) - (numSr * 0.014) - (numLight * 0.0266)) * 100), new Integer(time), new Integer(questionsList.size())}));
    }

    public void saveToFile(int userID) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public QuestionAssessment getQuestionCountedDifficulty(int questionId) {
        Question question = new HibernateTestManager().getQuestion(questionId);
        return getQuestionParameters(question);
    }

    private QuestionAssessment getQuestionParameters(Question question) {
        StatisticsManager manager = new HibernateStatisticsManager();
        List<AnsweringStatistic> statisticList = manager.getStatisticForQuestion(question.getId());
        return new QuestionAssessment(statisticList, question.getWeight());
    }

    public void writeStatisticinCSV(int subjectId, OutputStream stream) {
        PrintStream outStream = new PrintStream(stream);
        outStream.println("Имя Блока;Имя раздела;Вопрос;Ответов;;;Исходная сложность;;Оцененная сложность;;Разница;");
        outStream.println(";           ;      ;Всего;Правильных;Неправильных;приведенная;реальная;приведенная;реальная;приведенная;реальная");
        Subject subject = new HibernateTestManager().getSubjectById(subjectId);
        for (Block block : subject.getBlocks()) {
            writeWithIndent(0, block.getBlockName(), outStream);
            for (Part part : block.getParts()) {
                writeWithIndent(1, part.getPartName(), outStream);
                for (QuestionGroup group : part.getQuestionGroups()) {
                    for (Question question : group.getQuestions()) {
                        writeWithIndent(2, collectQuestionInfo(question), outStream);
                    }
                }
            }
        }
    }

    private String collectQuestionInfo(Question question) {
        QuestionAssessment params = getQuestionParameters(question);
        return String.format("%s;%d;%d;%d;%d;%.4f;%d;%.4f;%d;%.4f", question.getMainText(), params.getAllAnswers(), params.getRightAnswered(), params.getNotRightAnswered(),
                params.getCur_normalized_difficulty(), params.getCurrentDifficulty(), params.getNew_normalized_difficulty(), params.getNewDifficulty(),
                params.getNorm_delta(), params.getReal_delta());
    }

    private void writeWithIndent(int indent, String value, PrintStream stream) {
        value = value.replaceAll("(\n|\r)", "");
        for (int i = 0; i < indent; i++) {
            stream.print(";");
        }
        stream.println(value);
    }

    public void writeCompressedTestResults(OutputStream stream) {
        ZipOutputStream zip = new ZipOutputStream(stream);
        List<Person> users = new HibernateAuthorizationManager().getUsers();
        StringBuilder stats = new StringBuilder();
        stats.append("Сводная таблица:\nФамилия И.О. \tОтносительный балл\tСкорректированный балл\tВремя\tКоличество заданных вопросов");
        for (Person user : users) {
            try {
                zip.putNextEntry(new ZipEntry(user.getSurename() + ".txt"));
                this.saveToFile(user.getId(), zip, stats);
                zip.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            zip.putNextEntry(new ZipEntry("Сводная таблица.txt"));
            PrintStream writer = new PrintStream(zip);
            writer.print(stats.toString());
            zip.closeEntry();
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        AdministrationServiceImpl service = new AdministrationServiceImpl();
        AuthorizationManager manager = new HibernateAuthorizationManager();
        PrintStream printWriter = new PrintStream(new BufferedOutputStream(new FileOutputStream("Сводная таблица.txt")));
        StringBuilder stat = new StringBuilder();

        stat.append("Сводная таблица:\nФамилия И.О. \tОтносительный балл\tСкорректированный балл\tВремя\tКоличество заданных вопросов\n");
        List users = manager.getUsers();
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            User user = (User) iterator.next();
            OutputStream writer = new FileOutputStream("results" + "/" + user.getSurename() + ".txt");
            service.saveToFile(user.getId(), writer, stat);
        }
        /*for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
            String question = (String) iterator.next();
            QuestionStatistics statistics = (QuestionStatistics) map.get(question);
            printWriter.printf("%s\t%d\t%d\t%.4f\t%d %n", new Object[]{question, new Integer(statistics.getTryAnswer()), new Integer(statistics.getAnswered()), new Double(statistics.getAnswered() / (double) statistics.getTryAnswer()), new Integer(statistics.getDifficulty())});
        }*/
        printWriter.print(stat.toString());
        printWriter.close();
    }

    private String getRightAnswer(final Question question) {
        final StringBuilder res = new StringBuilder();
        if (question instanceof OpenQuestion) {
            return ((OpenQuestion) question).getAnswerText();
        } else {
            new HibernateTestManager().executeCommand(new ICommand<Session>() {
                public Object execute(Session sessionManager) {
                    ClosedQuestion q = (ClosedQuestion) sessionManager.get(ClosedQuestion.class, question.getId());
                    for (Object closedAnswer : q.getAnswersSet()) {
                        ClosedAnswer answer = (ClosedAnswer) closedAnswer;
                        if (answer.isRight()) {
                            res.append(answer.getName()).append(";");
                        }
                    }
                    return null;
                }
            });

        }
        return res.toString();
    }

    public List getStatistics() {
        List statistics = new HibernateStatisticsManager().getStatistic();
        return statistics;
    }

    private class QuestionStatistics {
        private int tryAnswer = 0;
        private int answered = 0;
        private int difficulty = 0;

        public int getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }

        public int getTryAnswer() {
            return tryAnswer;
        }

        public void setTryAnswer(int tryAnswer) {
            this.tryAnswer = tryAnswer;
        }

        public int getAnswered() {
            return answered;
        }

        public void setAnswered(int answered) {
            this.answered = answered;
        }
    }
}
