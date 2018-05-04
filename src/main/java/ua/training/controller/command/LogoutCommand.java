package ua.training.controller.command;

import ua.training.constant.NameCommands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session)) {
            session.invalidate();
            System.out.println(request.getSession().getId());
            System.out.println();
        }
        return NameCommands.REDIRECT.concat(NameCommands.INDEX_PAGE);
    }
}
