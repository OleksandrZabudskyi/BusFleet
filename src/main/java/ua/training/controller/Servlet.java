package ua.training.controller;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

@WebServlet(urlPatterns = {"/bus-fleet/*"})
public class Servlet extends HttpServlet {
    private CommandExtractor commandExtractor;

    @Override
    public void init() {
        commandExtractor = CommandExtractor.getInstance();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandExtractor.getCommand(request);
        String page = command.execute(request, response);
        if (page.contains(NameCommands.REDIRECT)) {
            response.sendRedirect(page.replace(NameCommands.REDIRECT, Attributes.EMPTY_SIGN));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

