package ru.ifmo.qd.testing.server.manager;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 19.11.2010
 * Time: 13:56:02
 * To change this template use File | Settings | File Templates.
 */
public interface ICommand<E> {
    Object execute(E sessionManager);
}
