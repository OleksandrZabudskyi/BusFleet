package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.constant.Pages;
import ua.training.exeptions.EntityAlreadyExistException;
import ua.training.model.entity.Driver;
import ua.training.model.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DriverRegistrationCommand implements Command {
    private EmployeeService employeeService;

    public DriverRegistrationCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        try {
            Driver driver = getDriverFromRequest(request);
                employeeService.registerDriver(driver);
                Driver savedDriver = employeeService.getDriver(driver.getEmail());
                request.setAttribute(Attributes.DRIVER, savedDriver);
                page = Pages.LOGIN_PAGE;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(Attributes.INFO_MESSAGE, Messages.USER_ALREADY_EXIST);
            page = Pages.REGISTRATION_PAGE;
        }
        return page;
    }

    private Driver getDriverFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter(Attributes.FIRST_NAME);
        String lastName = request.getParameter(Attributes.LAST_NAME);
        String phoneNumber = request.getParameter(Attributes.PHONE_NUMBER);
        String drivingLicenceNumber = request.getParameter(Attributes.DRIVER_LICENCE_NUMBER);
        int expiryDate = Integer.parseInt(request.getParameter(Attributes.EXPIRY_DATE));
        int drivingExperience = Integer.parseInt(request.getParameter(Attributes.DRIVING_EXPERIENCE));
        String email = request.getParameter(Attributes.EMAIL);
        String password = request.getParameter(Attributes.PASSWORD);

        return new Driver.DriverBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setPassword(password)
                .setDrivingLicenceNumber(drivingLicenceNumber)
                .setExpiryDate(expiryDate)
                .setDrivingExperience(drivingExperience)
                .createDriver();
    }
}