package ru.ifmo.qd.testing.client.model.tests;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 25.11.2007
 * Time: 20:11:07
 * To change this template use File | Settings | File Templates.
 */

@Table(name = "block")
@Entity()
public class Block implements Serializable{

    @Column(name = "BLOCK_ID")
    @Id
    private int id;

    @Column(name = "blockName")
    @Basic
    private String blockName;

    @JoinColumn(name = "SUBJECT_ID", nullable = false)
    @ManyToOne
    private Subject subject;

    @Column(name = "description")
    @Basic
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "block")
    private List<Part> parts;

    public Block() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public void addPart(Part part) {
        parts.add(part);
    }

    public void removePart(Part part) {
        parts.remove(part);
    }
}
