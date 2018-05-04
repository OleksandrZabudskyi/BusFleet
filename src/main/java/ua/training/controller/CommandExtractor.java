package ua.training.controller;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.controller.command.*;
import ua.training.controller.command.LoginCommand;
import ua.training.controller.command.LogoutCommand;
import ua.training.model.service.impl.UserServiceImpl;

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
        commands.put(NameCommands.LOGIN, new LoginCommand(new UserServiceImpl()));
        commands.put(NameCommands.REGISTRATION, new RegistrationCommand());
        commands.put(NameCommands.LOGOUT, new LogoutCommand());
        commands.put(NameCommands.LANGUAGE, new LanguageCommand());


    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter(Attributes.ACT);
        return commands.getOrDefault(action, new IndexPageCommand());
    }

    public static CommandExtractor getInstance() {
        if (instance == null) {
            synchronized (CommandExtractor.class) {
                if (instance == null) {
                    instance = new CommandExtractor();
                }
            }
        }
        return instance;
    }
}

