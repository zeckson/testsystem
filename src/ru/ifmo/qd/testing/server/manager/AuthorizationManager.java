package ru.ifmo.qd.testing.server.manager;

import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.client.model.users.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 25.09.2010
 * Time: 14:28:28
 * To change this template use File | Settings | File Templates.
 */
public interface AuthorizationManager<E> extends Executable<E> {
    Admin addAdmin(String loginName, String password, String name, String surename,
                   String info);

    User addUser(String loginName, String password, String name, String surename,
                 String info);

    Person getPerson(String login, String password);

    void removePerson(int personId);

    List<Person> getUsers();

    User getUser(int userID);
}
