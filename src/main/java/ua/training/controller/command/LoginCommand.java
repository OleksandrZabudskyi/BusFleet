package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.constant.Pages;
import ua.training.controller.listener.ActiveUser;
import ua.training.controller.util.RequestParametersValidator;
import ua.training.model.entity.Employee;
import ua.training.model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Attributes.EMAIL);
        String password = request.getParameter(Attributes.PASSWORD);
        RequestParametersValidator requestParametersValidator = new RequestParametersValidator(request);
        if (requestParametersValidator.validateIfNullOrEmpty(Attributes.EMAIL, Attributes.PASSWORD)) {
            return Pages.LOGIN_PAGE;
        }

        Optional<Employee> userOptional = userService.findUserByEmail(email);
        if (!userOptional.isPresent() || !password.equals(userOptional.get().getPassword())) {
            request.setAttribute(Attributes.ERROR_MESSAGE, Messages.WRONG_LOGIN_OR_PASSWORD);
            return Pages.LOGIN_PAGE;
        }
        Employee employee = userOptional.get();
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(Attributes.USER, employee);
        httpSession.setAttribute(Attributes.ROLE, employee.getRole());

        ActiveUser activeUser = new ActiveUser(employee);
        httpSession.setAttribute(Attributes.ACTIVE_USER, activeUser);
        if (activeUser.isAlreadyLoggedIn()) {
            request.setAttribute(Attributes.INFO_MESSAGE, Messages.USER_ALREADY_LOGGED);
            return Pages.LOGIN_PAGE;
        }
        return getUserPage(employee.getRole());
    }

    private String getUserPage(Employee.ROLE role) {
        if (role.equals(Employee.ROLE.ADMIN)) {
            return Pages.ADMIN_PAGE;
        }
        if (role.equals(Employee.ROLE.DRIVER)) {
            return Pages.DRIVER_PAGE;
        }
        return Pages.INDEX_PAGE;
    }
}
