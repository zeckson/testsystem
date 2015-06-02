package ru.ifmo.qd.testing.client.model.statistics;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 01.12.2007
 * Time: 9:18:31
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "STATISTIC")
public class AnsweringStatistic implements IsSerializable {

    @Column(name = "SELF_ID")
    @Id()
    private int id;

    @Column(name = "QUESTION_ID" )
    @Basic()
    private int questionID;

    @Column(name = "PERSON_ID" )
    @Basic()
    private int personID;

    @Column(name = "DATE" )
    @Basic()
    private long date;

    @Column(name = "TIME" )
    @Basic()
    private int answerTime;
    
    @Column(name = "TRY_NUMBER" )
    @Basic()
    private int tryNum;

    @Column(name = "SCORE" )
    @Basic()
    private double score;

    @Column(name = "ANSWER", length = 2147483647)
    @Basic() @Lob
    private String answer;

    public AnsweringStatistic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getDate() {
        return date;
    }

    public void setDate() {
        this.date =(new Date()).getTime();
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getTryNum() {
        return tryNum;
    }

    public void setTryNum(int tryNum) {
        this.tryNum = tryNum;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }
}
