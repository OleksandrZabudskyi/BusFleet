package ua.training.controller.command.redirect;

import ua.training.constant.Pages;
import ua.training.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for redirecting to driver page
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Pages
 */
public class DriverPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return Pages.DRIVER_PAGE;
    }
}
