package ua.training.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommandTest {
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private LoginCommand loginCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginCommand = new LoginCommand(userService);
    }

    @Test
    public void execute() {

    }
}