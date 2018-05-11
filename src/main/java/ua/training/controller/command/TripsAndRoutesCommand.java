package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TripsAndRoutesCommand implements Command {
    private TripService tripService;

    public TripsAndRoutesCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<List<Trip>> trips = tripService.getTripsAndRoutes();
        request.setAttribute(Attributes.TRIPS, trips.get());
        return Pages.ALL_TRIPS_PAGE;
    }
}
