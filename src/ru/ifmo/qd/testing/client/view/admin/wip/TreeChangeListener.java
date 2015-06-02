package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.user.client.ui.TreeItem;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 30.11.2007
 * Time: 6:27:54
 * To change this template use File | Settings | File Templates.
 */
public interface TreeChangeListener {
    void itemSelected(TreeItem item, ModelProvider o);

    void itemStateChanged(TreeItem item, ModelProvider modelProvider);
}
