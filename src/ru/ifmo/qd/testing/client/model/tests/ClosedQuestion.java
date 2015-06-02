package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 14:31:28
 * To change this template use File | Settings | File Templates.
 */
@DiscriminatorValue(value = "CLOSED")
@Entity
public class ClosedQuestion extends Question implements Serializable {

    @OneToMany(targetEntity = Answer.class ,cascade = CascadeType.ALL)
    private List<Answer> answersSet;

    public ClosedQuestion() {
    }

    public Set getAnswersSet() {
        return (Set) answersSet;
    }

    public void setAnswersSet(Set answersSet) {
        this.answersSet = (List<Answer>) answersSet;
    }
}
