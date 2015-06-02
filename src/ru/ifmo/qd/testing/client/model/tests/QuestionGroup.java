package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 26.11.2007
 * Time: 0:59:37
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "question_group")
@Entity
public class QuestionGroup implements Serializable {

    @Column(name = "QUESTION_GROUP_ID")
    @Id
    private int id;

    @Column(name = "name")
    @Basic
    private String name;
    @Column(name = "description")
    @Basic
    private String description;
    @JoinColumn(name = "PART_ID")
    @ManyToOne()
    private Part part;

    @OneToMany(mappedBy ="questionGroup",cascade = CascadeType.ALL)
    private List<Question> questions;

    public QuestionGroup() {
    }

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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
