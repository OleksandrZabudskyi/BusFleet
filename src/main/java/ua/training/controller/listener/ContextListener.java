package ua.training.controller.listener;

import ua.training.constant.Attributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<ActiveUser, HttpSession> loggedUsers = new ConcurrentHashMap<>();
        servletContextEvent.getServletContext().setAttribute(Attributes.LOGGED_USERS, loggedUsers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(Attributes.LOGGED_USERS);
    }
}
