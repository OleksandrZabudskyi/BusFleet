package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.controller.util.RequestParametersValidator;
import ua.training.model.bean.User;
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
        String page;
        String email = request.getParameter(Attributes.EMAIL);
        String password = request.getParameter(Attributes.PASSWORD);
        RequestParametersValidator requestParametersValidator = new RequestParametersValidator(request);
        if (requestParametersValidator.validateIfNullOrEmpty(Attributes.EMAIL, Attributes.PASSWORD)) {
            return Pages.LOGIN_PAGE;
        }

        Optional<User> userOptional = userService.findUserByEmail(email);
        if (userOptional.isPresent() && password.equals(userOptional.get().getPassword())) {
            User user = userOptional.get();
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(Attributes.USER, user);
            httpSession.setAttribute(Attributes.ROLE, user.getRole());
            page = getUserPage(user.getRole());
        } else {
            request.setAttribute(Attributes.ERROR_MESSAGE, Messages.WRONG_PASSWORD);
            page = Pages.INDEX_PAGE;
        }
        return page;
    }

    private String getUserPage(User.ROLE role) {
        if (role.equals(User.ROLE.ADMIN)) {
            return Pages.ADMIN_PAGE;
        }
        if (role.equals(User.ROLE.DRIVER)) {
            return Pages.DRIVER_PAGE;
        }
        return Pages.INDEX_PAGE;
    }
}
