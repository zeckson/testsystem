package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 25.11.2007
 * Time: 18:46:54
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "subject")
@Entity
public class Subject implements Serializable {

    @Id()
    @Column(name = "SUBJECT_ID")
    private int id;

    @Basic
    @Column(name = "SUBJECT_NAME")
    private String subjectName;
    @Basic
    @Column(name = "SUBJECT_CREATOR")
    private String creatorName;
    @Basic
    @Column(name = "SUBJECT_DESCRIPTOR")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, /*fetch = FetchType.EAGER,*/ mappedBy = "subject") //not work EAGER under hiberante    
    private List<Block> blocks;

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
    }
}
