package ru.ifmo.qd.testing.server.model;

import ru.ifmo.qd.testing.client.model.tests.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.04.2008
 * Time: 14:02:17
 * To change this template use File | Settings | File Templates.
 */
public class AdvancedTest implements TestGenerator {
    private Map questions = new TreeMap();
    private Question next = null;
    private Question prev;
    private Object[] levelsArray;
    private int index = -1;
    private int max = 1;
    private int levels = 0;
    private int currentLevel = 0;
    private double scores = 0;

    public AdvancedTest(Subject subject) {
        generateTest(subject);
        levelsArray = questions.keySet().toArray();
        levels = levelsArray.length - 1;
        currentLevel = levels;
        prev = next(1);
        scores -= 1;
    }

    private Map generateTest(Subject subject) {
        for (Iterator iterator = subject.getBlocks().iterator(); iterator.hasNext();) {
            Block block = (Block) iterator.next();
            for (Iterator iter = block.getParts().iterator(); iter.hasNext();) {
                Part part = (Part) iter.next();
                for (Iterator iterator1 = part.getQuestionGroups().iterator(); iterator1.hasNext();) {
                    QuestionGroup questionGroup = (QuestionGroup) iterator1.next();
                    for (Iterator iterator2 = questionGroup.getQuestions().iterator(); iterator2.hasNext();) {
                        Question question = (Question) iterator2.next();
                        if (questions.containsKey(question.getWeight())) {
                            List questionsList = (List) questions.get(question.getWeight());

                            if(!questionsList.contains(question.getQuestionGroup())){
                                questionsList.add(question.getQuestionGroup());
                                questions.put(question.getWeight(), questionsList);
                            }

                        } else {
                            List questionsList = new ArrayList();
                            questionsList.add(question.getQuestionGroup());
                            questions.put(question.getWeight(), questionsList);
                        }
                        max = (question.getWeight().intValue() > max) ? question.getWeight().intValue() : max; 
                    }
                }
            }
        }
        return questions;
    }

    public Question next() {
        return next(1);
    }

    public Question next(double scores) {
        scores += scores;
        try{
        next = createNewQuestion(scores);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
        next.setQuestionGroup(null);
        if (next instanceof ClosedQuestion) {
            ClosedQuestion closedQuestion = (ClosedQuestion) next;
            closedQuestion.setAnswersSet(new HashSet(closedQuestion.getAnswersSet()));
            next = closedQuestion;
        }
        prev = next;
        return next;
    }

    public Question prev() {
        return prev;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean hasNext() {
        return true;
    }

    public boolean hasPrev() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getScores() {
        return scores;
    }


    private Question createNewQuestion(double scores) throws IndexOutOfBoundsException{
        QuestionGroup questionGroup;
        if (scores > 0) {
            currentLevel = levels;
            questionGroup = (QuestionGroup) ((List) questions.get(levelsArray[currentLevel])).get(++index);
        } else {
            if (--currentLevel > -1) {
                questionGroup = (QuestionGroup) ((List) questions.get(levelsArray[currentLevel])).get(index);
            } else {
                currentLevel = levels;
                questionGroup = (QuestionGroup) ((List) questions.get(levelsArray[currentLevel])).get(++index);
            }
        }
        Random random = new Random(System.currentTimeMillis());
        return (Question) questionGroup.getQuestions().get(random.nextInt(questionGroup.getQuestions().size()));
    }
}
