package ua.training.controller.util;

import ua.training.constant.Messages;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RequestParametersValidator {
    private final HttpServletRequest request;

    public RequestParametersValidator(HttpServletRequest request) {
        this.request = request;
    }

    public boolean validateIfNullOrEmpty(String... parameters) {
        boolean invalidParams = false;
        for(String parameterName : parameters) {
            String parameterValue = request.getParameter(parameterName);
            if (Objects.isNull(parameterValue) || parameterValue.isEmpty()) {
                request.setAttribute(
                        String.format(Messages.PARAMS_ERROR, parameterName),
                        String.format(Messages.PARAMS_BLANK_FIELD, parameterName));
                invalidParams = true;
            }
        }
        return invalidParams;
    }
}
