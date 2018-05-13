package ua.training.controller.command;

import ua.training.constant.Attributes;
import ua.training.constant.GlobalConstants;
import ua.training.constant.Pages;
import ua.training.model.entity.Trip;
import ua.training.model.service.TripService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TripsAndRoutesCommand implements Command {
    private TripService tripService;

    public TripsAndRoutesCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        String pageNumber = request.getParameter(Attributes.PAGE);

        if (Objects.nonNull(pageNumber)) {
            currentPage = Integer.parseInt(request.getParameter(Attributes.PAGE));
        }

        Optional<List<Trip>> trips = tripService.getTripsAndRoutes((currentPage - 1)
                * GlobalConstants.RECORDS_PER_PAGE, GlobalConstants.RECORDS_PER_PAGE);

        int numberOfRecords = tripService.getNumberOfRecords();
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / GlobalConstants.RECORDS_PER_PAGE);
        request.setAttribute(Attributes.TRIPS, trips.get());
        request.setAttribute(Attributes.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(Attributes.CURRENT_PAGE, currentPage);

        return Pages.ALL_TRIPS_PAGE;
    }
}
