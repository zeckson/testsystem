package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 26.11.2007
 * Time: 0:57:32
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "part")
@Entity
public class Part implements Serializable {
    @Column(name = "PART_ID")
    @Id
    private int id;
    @Column(name = "partName")
    @Basic
    private String partName;

    @JoinColumn(name = "BLOCK_ID", nullable = false)
    @ManyToOne
    private Block block;
    @Column(name = "description")
    @Basic
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "part")
    private List<QuestionGroup> questionGroups;

    public Part() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionGroup> getQuestionGroups() {
        return questionGroups;
    }

    public void setQuestionGroups(List<QuestionGroup> questionGroups) {
        this.questionGroups = questionGroups;
    }

    public void removeQuestionGroup(QuestionGroup questionGroup) {
        questionGroups.remove(questionGroup);
    }

    public void addQuestionGroup(QuestionGroup questionGroup) {
        questionGroups.add(questionGroup);
    }
}
