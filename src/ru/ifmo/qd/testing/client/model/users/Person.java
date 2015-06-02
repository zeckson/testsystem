package ru.ifmo.qd.testing.client.model.users;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 05.11.2007
 * Time: 20:31:29
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "people")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_PRIVILIGIES", discriminatorType = DiscriminatorType.STRING)
@Entity
public abstract class Person implements Serializable {
    @Column(name = "PERSON_ID")
    @Id
    private int id;
    @Column(name = "PERSON_NAME")
    @Basic
    private String name;
    @Column(name = "PERSON_SURENAME")
    @Basic
    private String surename;
    @Column(name = "PERSON_INFO")
    @Basic
    private String info;
    @Column(name = "PERSON_LOGIN")
    @Basic
    private String loginName;
    @Column(name = "PERSON_PASSWORD")
    @Basic
    private String password;
    @Column(name = "PERSON_SUBJECTS")
    @Basic
    private String associatedSubjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAssociatedSubjects() {
        return associatedSubjects;
    }

    public void setAssociatedSubjects(String associatedSubjects) {
        this.associatedSubjects = associatedSubjects;
    }
}
