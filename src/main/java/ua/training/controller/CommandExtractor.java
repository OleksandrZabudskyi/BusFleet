package ua.training.controller;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.controller.command.*;
import ua.training.controller.command.LoginCommand;
import ua.training.controller.command.LogoutCommand;
import ua.training.controller.command.redirect.AdminPageCommand;
import ua.training.controller.command.redirect.DriverPageCommand;
import ua.training.controller.command.redirect.LoginPageCommand;
import ua.training.controller.command.redirect.RegistrationPageCommand;
import ua.training.model.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandExtractor {
    private static CommandExtractor instance;
    private Map<String, Command> commands = new HashMap<>();

    private CommandExtractor() {
        commands.put(NameCommands.INDEX_PAGE, new IndexPageCommand());
        commands.put(NameCommands.LOGIN_PAGE, new LoginPageCommand());
        commands.put(NameCommands.REGISTRATION_PAGE, new RegistrationPageCommand());
        commands.put(NameCommands.ADMIN_PAGE, new AdminPageCommand());
        commands.put(NameCommands.DRIVER_PAGE, new DriverPageCommand());
        commands.put(NameCommands.LOGIN, new LoginCommand(new EmployeeServiceImpl()));
        commands.put(NameCommands.REGISTRATION, new DriverRegistrationCommand(new EmployeeServiceImpl()));
        commands.put(NameCommands.LOGOUT, new LogoutCommand());
        commands.put(NameCommands.LANGUAGE, new LanguageCommand());


    }

    public Command getCommand(HttpServletRequest request) {
        /*String action = request.getParameter(Attributes.ACT);*/
        String path = request.getRequestURI();
        path = path.replaceAll(Attributes.DOMAIN , Attributes.EMPTY_SIGN);
        /*return commands.getOrDefault(action, new IndexPageCommand());*/
        return commands.getOrDefault(path, new IndexPageCommand());
    }

    public static CommandExtractor getInstance() {
        if (instance == null) {
            instance = new CommandExtractor();
        }
        return instance;
    }
}

