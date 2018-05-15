package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.model.service.TripService;
import ua.training.model.service.impl.TripServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AllDriversCommand implements Command {
    private TripService tripService;

    public AllDriversCommand(TripServiceImpl tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        String currentPage = request.getParameter(Attributes.PAGE);
        if (Objects.nonNull(tripId) && Integer.parseInt(tripId) != 0) {
            request.setAttribute(Attributes.TRIP_ID, tripId);
        }
        request.setAttribute(Attributes.DRIVERS, tripService.getAllDrivers());
        request.setAttribute(Attributes.PAGE, currentPage);
        return Pages.DRIVERS_PAGE;
    }
}
