package ru.ifmo.qd.testing.client.model.statistics;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 22, 2010
 * Time: 11:20:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "RANK_CHANGE")
public class QuestionChange implements Serializable{


    @Column(name = "SELF_ID")
    @Id()
    private int id;

    @Column(name = "QUESTION_ID" )
    @Basic()
    private int questionID;

    @Column(name = "APPLIED" )
    @Basic()
    private boolean applied;

    @Column(name = "NEW_DIFFICULTY" )
    @Basic()
    private int newDifficulty;

    @Column(name = "OLD_DIFFICULTY" )
    @Basic()
    private int oldDifficulty;

    public QuestionChange() {
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

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public int getNewDifficulty() {
        return newDifficulty;
    }

    public void setNewDifficulty(int newDifficulty) {
        this.newDifficulty = newDifficulty;
    }

    public int getOldDifficulty() {
        return oldDifficulty;
    }

    public void setOldDifficulty(int oldDifficulty) {
        this.oldDifficulty = oldDifficulty;
    }
}
