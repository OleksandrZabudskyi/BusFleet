package ua.training.controller.listener;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.LogMessage;
import ua.training.model.entity.Employee;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static Logger logger = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setAttribute(Attributes.ROLE, Employee.ROLE.GUEST.toString());
        logger.debug(LogMessage.SESSION_CREATED + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.debug(LogMessage.SESSION_DESTROYED + session.getId());
    }
}
