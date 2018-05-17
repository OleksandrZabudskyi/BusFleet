package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.controller.command.Command;
import ua.training.controller.util.RequestParametersValidator;
import ua.training.model.service.impl.TripServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetDriverCommand implements Command {
    private TripServiceImpl tripService;

    public SetDriverCommand(TripServiceImpl tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        String driverId = request.getParameter(Attributes.DRIVER_ID);
        String currentPage = request.getParameter(Attributes.PAGE);

        RequestParametersValidator parametersValidator = new RequestParametersValidator(request);
        if (parametersValidator.validateIfNullOrEmpty(Attributes.TRIP_ID, Attributes.DRIVER_ID)) {
            return NameCommands.ALL_TRIPS;
        }
        tripService.setDriverOnTrip(Integer.parseInt(tripId), Integer.parseInt(driverId));
        request.setAttribute(Attributes.PAGE, currentPage);
        return NameCommands.ALL_TRIPS;
    }
}
