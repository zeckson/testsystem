package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 14:19:47
 * To change this template use File | Settings | File Templates.
 */
@DiscriminatorValue(value = "OPEN")
@Entity
public class OpenQuestion extends Question implements Serializable {
    @Basic @Lob
    @Column(name = "ANSWER", length = 2147483647)
    private String answerText;

    public OpenQuestion() {
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
