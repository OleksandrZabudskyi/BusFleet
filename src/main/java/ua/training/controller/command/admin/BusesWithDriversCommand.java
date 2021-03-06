package ua.training.controller.command.admin;

import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.controller.command.Command;
import ua.training.model.service.BusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Command for loading buses and drivers
 *
 * @author Zabudskyi Oleksandr
 * @see Command
 * @see Attributes
 * @see Pages
 */
public class BusesWithDriversCommand implements Command {
    private BusService busService;

    public BusesWithDriversCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter(Attributes.TRIP_ID);
        String currentPage = request.getParameter(Attributes.PAGE);
        if(Objects.nonNull(tripId) && Integer.parseInt(tripId) != 0) {
            request.setAttribute(Attributes.TRIP_ID, tripId);
        }
        request.setAttribute(Attributes.BUSES, busService.getAllBusesToAllDrivers());
        request.setAttribute(Attributes.PAGE, currentPage);
        return Pages.BUSES_WITH_ROUTES;
    }
}
