package ru.ifmo.qd.testing.server.hibernate;

import org.hibernate.Session;
import org.hsqldb.Server;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ifmo.qd.testing.client.model.users.Admin;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.server.manager.ICommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 22.01.11
 * Time: 20:54
 * To change this template use File | Settings | File Templates.
 */
public class HibernateAuthorizationManagerTest {

    private Process hsqldb;
    private Server server;

    @Before()
    public void initHSQLDB() throws IOException {
/*        List<String> cmdLine = new ArrayList<String>();
        cmdLine.add("-jar lib/hsqdb.jar");
        ProcessBuilder processBuilder = new ProcessBuilder(cmdLine);
        hsqldb = processBuilder.start();*/
        server = new Server();
        server.setDatabasePath(0, "mem:test_database");
        server.setDatabaseName(0, "autority_db");
//        server.setNoSystemExit(true);
        server.start();
    }

    @After
    public void stopHSQLDB() {
//        hsqldb.destroy();
        server.stop();
    }


    @Test
    public void executeCommand() {
        TestCaseHibernateAuthManager manager = new TestCaseHibernateAuthManager();
        final Admin admin = new Admin();
        admin.setName("zeckson");
        admin.setSurename(null);
        admin.setInfo(null);
        admin.setLoginName("zeckson");
        admin.setPassword("ifmo.ru");
        admin.setAssociatedSubjects(new String());

        manager.executeCommand(new ICommand<Session>() {
            public Object execute(Session sessionManager) {
                sessionManager.persist(admin);
                return null;
            }
        });

        Admin res = (Admin) manager.executeCommand(new ICommand<Session>() {
            public Object execute(Session sessionManager) {
                return sessionManager.get(Person.class, admin.getId());
            }
        });


        assertEquals(null, "zeckson", res.getName());
    }
}
