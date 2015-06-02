package ru.ifmo.qd.testing.client.model.statistics;

import java.io.Serializable;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 25, 2010
 * Time: 12:30:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionAssessment implements Serializable{

    public static double MIN_RATE = -3.0;
    public static double MAX_RATE = 3.0;
    public static double LOW_BORDER = -.5;
    public static double HIGH_BORDER = .5;
    public static double COEFF = .5;

    int cur_normalized_difficulty;
    int new_normalized_difficulty;
    double currentDifficulty;
    double newDifficulty;
    int range;
    double real_delta;
    int norm_delta;

    public int getRightAnswered() {
        return rightAnswered;
    }

    public int getAllAnswers() {
        return allAnswers;
    }

    public int getNotRightAnswered() {
        return notRightAnswered;
    }

    private int rightAnswered;
    private int allAnswers;
    private int notRightAnswered;

    public QuestionAssessment() {
    }

    public QuestionAssessment(int cur_norm_dif, double new_dif) {

        count();
    }
    public QuestionAssessment(List<AnsweringStatistic> statistics, int origianlWeight) {
        allAnswers = statistics.size();
        rightAnswered = 0;
        for (AnsweringStatistic answeringStatistic : statistics) {
            if(answeringStatistic.getScore() > 0)
                ++rightAnswered;
        }
        notRightAnswered = allAnswers - rightAnswered;
        double result = notRightAnswered == 0 ? -3.0 : rightAnswered == 0 ? 3.0 : Math.log((rightAnswered/(double) allAnswers) /(double) (notRightAnswered/(double) allAnswers));
        this.cur_normalized_difficulty = origianlWeight;
        this.newDifficulty = result;
        count();
    }

    private void count(){
        this.currentDifficulty = toReal(this.cur_normalized_difficulty);
        this.new_normalized_difficulty = toNormalized(this.newDifficulty);
        this.real_delta = abs(this.currentDifficulty - this.newDifficulty);
        this.norm_delta = abs(this.cur_normalized_difficulty - this.new_normalized_difficulty);
    }

    private int toNormalized(double real){
        if(real<LOW_BORDER){
            return 1;
        }else if(real>HIGH_BORDER){
            return 3;
        }
        return 2;
    }

    private double toReal(int normalized){
        double res = 0.;
        switch (normalized){
            case 0:
            case 1:
                res = LOW_BORDER - COEFF;
                break;
            case 2:
                res = (LOW_BORDER + HIGH_BORDER)/2 + COEFF/2;
                break;
            case 3:
                res = HIGH_BORDER + COEFF;
                break;
        }
        return res;
    }

    public int getCur_normalized_difficulty() {
        return cur_normalized_difficulty;
    }

    public int getNew_normalized_difficulty() {
        return new_normalized_difficulty;
    }

    public double getCurrentDifficulty() {
        return currentDifficulty;
    }

    public double getNewDifficulty() {
        return newDifficulty;
    }

    public int getRange() {
        return range;
    }

    public double getReal_delta() {
        return real_delta;
    }

    public int getNorm_delta() {
        return norm_delta;
    }
}
