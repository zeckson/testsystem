package ru.ifmo.qd.testing.client.view.admin.wip;

import com.google.gwt.user.client.ui.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 30.11.2007
 * Time: 5:42:46
 * To change this template use File | Settings | File Templates.
 */
public class QuestionsTree extends Composite implements TreeListener {
    private Tree tree = null;
    private TreeItem parent = null;
    private Map treeItems = new HashMap();
    private List treeChangeListeners = new ArrayList();

    public QuestionsTree() {
        this.tree = new Tree();
        initWidget(tree);
    }

    public void addModelItem(ModelProvider modelProvider) {
        if (modelProvider.getParentElement() == null) {
            parent = createItem(modelProvider.getLeafWidget());
            tree.addItem(parent);
            getChild(modelProvider);
        } else {
            if (parent != null) {
                parent.addItem(createItem(modelProvider.getLeafWidget()));
                getChild(modelProvider);
            }
        }
    }

    private void getChild(ModelProvider modelProvider) {
        if (modelProvider.hasChildren()) {
            for (Iterator iterator = modelProvider.getChildren().iterator(); iterator.hasNext();) {
                Object o = iterator.next();
                if (o instanceof ModelProvider) {
                    addModelItem((ModelProvider) o);
                }
            }
        }
    }

    private TreeItem createItem(Widget leafWidget) {
        TreeItem treeItem = new TreeItem(leafWidget);
        treeItems.put(treeItem, leafWidget);
        return treeItem;
    }

    public void addTreeChangeListener(TreeChangeListener treeChangeListener) {
        treeChangeListeners.add(treeChangeListener);
    }

    public void removeTreeChangeListener(TreeChangeListener treeChangeListener) {
        treeChangeListeners.add(treeChangeListener);
    }

    public void onTreeItemSelected(TreeItem item) {
        treeItemSelected(item, (ModelProvider) treeItems.get(item));
    }

    public void onTreeItemStateChanged(TreeItem item) {
        treeItemStateChanged(item, (ModelProvider) treeItems.get(item));

    }

    private void treeItemStateChanged(TreeItem item, ModelProvider modelProvider) {
        for (Iterator iterator = treeChangeListeners.iterator(); iterator.hasNext();) {
            TreeChangeListener treeChangeListener = (TreeChangeListener) iterator.next();
            treeChangeListener.itemStateChanged(item, modelProvider);
        }
    }

    private void treeItemSelected(TreeItem item, ModelProvider o) {
        for (Iterator iterator = treeChangeListeners.iterator(); iterator.hasNext();) {
            TreeChangeListener treeChangeListener = (TreeChangeListener) iterator.next();
            treeChangeListener.itemSelected(item, o);
        }
    }


}
