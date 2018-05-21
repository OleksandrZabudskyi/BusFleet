package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.constant.Pages;
import ua.training.model.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Command for log out user from system
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see NameCommands
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            session.removeAttribute(Attributes.ACTIVE_USER);
            session.setAttribute(Attributes.ROLE, Employee.ROLE.GUEST.toString());
        }
        return NameCommands.REDIRECT.concat(NameCommands.INDEX_PAGE);
    }
}
