package ru.ifmo.qd.testing.client.model.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 20:41:48
 * To change this template use File | Settings | File Templates.
 */
@DiscriminatorValue(value = "USER")
@Table(name = "people")
@Entity
public class User extends Person implements Serializable {

    @Column(name = "PERSON_GROUP")
    @Basic
    private String group;
    @Column(name = "PERSON_TUTOR")
    @Basic
    private String tutor;
    @Transient
    private int limit;

    public User() {
    }    

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
