package ru.ifmo.qd.testing.client.view.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 23, 2010
 * Time: 12:02:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class Option<T extends Serializable> implements INamedClass{


    public T getUserObject() {
        return userObject;
    }

    private T userObject;
    private String name;

    public Option(T userObject, String name, List<Option> suboptions) {
        this(userObject, name);
        this.suboptions = suboptions;
    }

    public List<Option> getSuboptions() {
        return suboptions == null ? new ArrayList<Option>() : suboptions;
    }

    public void addSuboption(Option option) {
        if(suboptions==null) suboptions = new ArrayList<Option>();
        this.suboptions.add(option);
    }

    private List<Option> suboptions = null;

    public Option(T userObject, String name) {
        this.name = name;
        this.userObject = userObject;
    }

    public String getName() {
        return name;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
