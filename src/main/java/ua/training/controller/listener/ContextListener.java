package ua.training.controller.listener;

import ua.training.constant.Attributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashSet;
import java.util.Set;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Set<ActiveUser> loggedUsers = new HashSet<>();
        servletContextEvent.getServletContext().setAttribute(Attributes.LOGGED_USERS, loggedUsers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(Attributes.LOGGED_USERS);
    }
}
