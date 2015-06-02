package ru.ifmo.qd.testing.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.ifmo.qd.testing.client.LoginService;
import ru.ifmo.qd.testing.client.model.users.Person;
import ru.ifmo.qd.testing.server.exceptions.UnknownRoleException;
import ru.ifmo.qd.testing.server.manager.AuthorizationManager;
import ru.ifmo.qd.testing.server.hibernate.HibernateAuthorizationManager;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Zeckson
 * Date: 31.03.2008
 * Time: 23:11:02
 * To change this template use File | Settings | File Templates.
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
    private static final String LOGGED_IN_PERSON = "loggedInPerson";
    private static final Log LOG = LogFactory.getLog(LoginServiceImpl.class);
    


    public Person getPerson(String login, String password) {        
        AuthorizationManager manager = new HibernateAuthorizationManager();
        Person person = manager.getPerson(login, password);
//        getThreadLocalRequest().getSession().setMaxInactiveInterval(60);
        if (person != null) {
            getThreadLocalRequest().getSession().setAttribute(LOGGED_IN_PERSON, person);
        }
        return person;
    }

    public Person getLoggedInPerson() {
        return (Person) getThreadLocalRequest().getSession().getAttribute(LOGGED_IN_PERSON);
    }

    public void createPerson(String text, String text1, String text2, String text3, String text4) {
        if (text4.equalsIgnoreCase("admin")) {
            (new HibernateAuthorizationManager()).addAdmin(text, text1, text2, text3, text4);
        } else if (text4.equalsIgnoreCase("user")) {
            (new HibernateAuthorizationManager()).addUser(text, text1, text2, text3, text4);
        } else {
            try {
                throw new UnknownRoleException("Role for " + text4 + " is unknown!");
            } catch (UnknownRoleException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public List<Person> getUsersList() {
        AuthorizationManager manager = new HibernateAuthorizationManager();
        return manager.getUsers();
    }


    public void logoff() {
        getThreadLocalRequest().getSession().removeAttribute(LOGGED_IN_PERSON);
    }

}
