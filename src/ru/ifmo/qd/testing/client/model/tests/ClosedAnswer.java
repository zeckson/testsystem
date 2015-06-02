package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 14:34:25
 * To change this template use File | Settings | File Templates.
 */
@DiscriminatorValue(value = "CLOSED")
@Entity
public class ClosedAnswer extends Answer implements Serializable {
    @Column(name = "ANSWER_NAME")
    @Basic
    private String name;
    @Column(name = "ANSWER_RIGHT")
    @Basic
    private boolean right;

    public ClosedAnswer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
