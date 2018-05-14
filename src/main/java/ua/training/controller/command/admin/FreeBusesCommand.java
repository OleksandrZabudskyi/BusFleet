package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.model.entity.Bus;
import ua.training.model.service.TripService;
import ua.training.model.service.impl.TripServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FreeBusesCommand implements Command {
    private TripService tripService;

    public FreeBusesCommand(TripServiceImpl tripService) {
        this.tripService = tripService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        if(Objects.nonNull(tripId) && Integer.parseInt(tripId) != 0) {
            request.setAttribute(Attributes.TRIP_ID, tripId);
        }

        Optional<List<Bus>> buses = tripService.getFreeBuses(0, 0);
        request.setAttribute(Attributes.BUSES, buses.get());

        return Pages.BUSES_PAGE;
    }
}
