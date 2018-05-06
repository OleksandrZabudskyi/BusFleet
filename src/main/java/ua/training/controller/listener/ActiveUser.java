package ua.training.controller.listener;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessage;
import ua.training.model.entity.Employee;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.HashSet;

public class ActiveUser implements HttpSessionBindingListener {
    private static Logger logger = Logger.getLogger(ActiveUser.class);
    private Employee employee;
    private boolean alreadyLoggedIn;

    public ActiveUser(Employee employee) {
        this.employee = employee;
    }

    public boolean isAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        addLoginUser(httpSessionBindingEvent);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        removeLoginUser(httpSessionBindingEvent);
    }

    private void removeLoginUser(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        HashSet<ActiveUser> activeUsers = (HashSet<ActiveUser>) context.getAttribute(Attributes.LOGGED_USERS);
        activeUsers.remove(this);
        logger.debug(LogMessage.REMOVE_USER + employee.getEmail());
    }

    private void addLoginUser(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        HashSet<ActiveUser> activeUsers = (HashSet<ActiveUser>) context.getAttribute(Attributes.LOGGED_USERS);
        alreadyLoggedIn = !activeUsers.add(this);
        logger.debug(LogMessage.ADD_USER + employee.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActiveUser activeUser = (ActiveUser) o;

        return employee != null ? employee.equals(activeUser.employee) : activeUser.employee == null;
    }

    @Override
    public int hashCode() {
        return employee != null ? employee.hashCode() : 0;
    }
}
