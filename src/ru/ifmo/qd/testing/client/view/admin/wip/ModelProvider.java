package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 30.11.2007
 * Time: 5:53:44
 * To change this template use File | Settings | File Templates.
 */
public interface ModelProvider {
    public List getChildren();

    public Object getParentElement();

    public boolean hasChildren();

    public Widget getLeafWidget();
}
