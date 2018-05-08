package ua.training.controller.filter;

import ua.training.constant.Attributes;
import ua.training.constant.Pages;
import ua.training.model.entity.Employee;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/bus-fleet/index_page"})
public class IndexPageFilter implements Filter {
    private Map<String, String> mapping;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        mapping = new HashMap<>();
        mapping.put(Employee.ROLE.GUEST.toString(), Pages.INDEX_PAGE);
        mapping.put(Employee.ROLE.ADMIN.toString(), Pages.ADMIN_PAGE);
        mapping.put(Employee.ROLE.DRIVER.toString(), Pages.DRIVER_PAGE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String role = (String) httpRequest.getSession().getAttribute(Attributes.ROLE);
        request.getRequestDispatcher(mapping.getOrDefault(role, Pages.INDEX_PAGE)).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
