package ru.ifmo.qd.testing.server.manager;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 04.04.11
 * Time: 9:54
 * To change this template use File | Settings | File Templates.
 */
public interface Executable<E> {
    Object executeCommand(ICommand<E> command);
}
