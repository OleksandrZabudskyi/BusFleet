package ua.training.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.model.service.EmployeeService;
import ua.training.model.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommandTest {
    @Mock
    private EmployeeService employeeService;
    @Mock
    private SecurityService securityService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private LoginCommand loginCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginCommand = new LoginCommand(employeeService, securityService);
    }

    @Test
    public void execute() {

    }
}