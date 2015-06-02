package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 14:04:13
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "QUESTION_TYPE", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Question implements Serializable {
    @Id
    @Column(name = "QUESTION_ID")
    private int id;
    @Basic
    @Column(name = "NAME")
    private String name;

    @Lob @Basic
    @Column(name = "TEXT",length = 2147483647)
    private String mainText;
    @Basic
    @Column(name = "WEIGHT")
    private Integer weight;
    @Basic
    @Column(name = "TIME")
    private Integer time;

    @JoinColumn(name = "QUESTION_GROUP_ID")
    @ManyToOne
    private QuestionGroup questionGroup;


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionGroup getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(QuestionGroup questionGroup) {
        this.questionGroup = questionGroup;
    }
}
