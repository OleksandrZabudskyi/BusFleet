package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.NameCommands;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.controller.util.RequestParametersValidator;
import ua.training.model.service.impl.TripServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SetBusCommand implements Command {
    private TripServiceImpl tripService;

    public SetBusCommand(TripServiceImpl tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        String busId = request.getParameter(Attributes.BUS_ID);
        String currentPage = request.getParameter(Attributes.PAGE);
        RequestParametersValidator parametersValidator = new RequestParametersValidator(request);
        if (parametersValidator.validateIfNullOrEmpty(Attributes.TRIP_ID, Attributes.BUS_ID)) {
            return NameCommands.ALL_TRIPS;
        }
        tripService.setBus(Integer.parseInt(tripId), Integer.parseInt(busId));
        request.setAttribute(Attributes.PAGE, currentPage);
        return NameCommands.ALL_TRIPS;
    }
}
