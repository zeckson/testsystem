package ru.ifmo.qd.testing.client.view.common;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: May 23, 2010
 * Time: 12:00:45 AM
 * To change this template use File | Settings | File Templates.
 */
public interface OptionsSelectionListener<T extends Serializable> {

    public void optionSelected(T o);

    public void collectOptions(AsyncCallback<List<T>> optionsCallback);
}
