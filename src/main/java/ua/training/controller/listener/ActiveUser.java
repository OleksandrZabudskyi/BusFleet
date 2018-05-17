package ua.training.controller.listener;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessage;
import ua.training.model.entity.Employee;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;
import java.util.Optional;

public class ActiveUser implements HttpSessionBindingListener {
    private static Logger logger = Logger.getLogger(ActiveUser.class);
    private Employee employee;
    private boolean alreadyLoggedIn;

    public ActiveUser(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public boolean isAlreadyLoggedIn() {
        return alreadyLoggedIn;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        addLoggedUserIfNotPresent(event);
    }

    private void addLoggedUserIfNotPresent(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Map<ActiveUser, HttpSession> activeUsers = (Map<ActiveUser, HttpSession>) context.getAttribute(Attributes.LOGGED_USERS);
        Optional<HttpSession> oldSession = Optional.ofNullable(activeUsers.get(this));
        if (oldSession.isPresent()) {
            oldSession.get().invalidate();
            alreadyLoggedIn = true;
        }
        activeUsers.put(this, event.getSession());
        logger.debug(LogMessage.ADD_USER + employee.getEmail());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        removeLoggedUser(event);
    }

    private void removeLoggedUser(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        Map<ActiveUser, HttpSession> activeUsers = (Map<ActiveUser, HttpSession>) context.getAttribute(Attributes.LOGGED_USERS);
        activeUsers.remove(this);
        logger.debug(LogMessage.REMOVE_USER + employee.getEmail());
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
