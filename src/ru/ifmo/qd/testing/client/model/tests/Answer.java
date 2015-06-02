package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 14:22:53
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ANSWER_TYPE", discriminatorType = DiscriminatorType.STRING)
@Entity()
public abstract class Answer implements Serializable{
    @Column(name = "ANSWER_ID")
    @Id()
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
        
    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
