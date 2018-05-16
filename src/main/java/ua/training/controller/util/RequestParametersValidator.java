package ua.training.controller.util;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.constant.Regex;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class RequestParametersValidator {
    private static Logger logger = Logger.getLogger(RequestParametersValidator.class);
    private final HttpServletRequest request;

    public RequestParametersValidator(HttpServletRequest request) {
        this.request = request;
    }

    public boolean validateIfNullOrEmpty(String... parameters) {
        boolean invalidParams = false;
        for (String parameterName : parameters) {
            String parameterValue = request.getParameter(parameterName);
            if (Objects.isNull(parameterValue) || parameterValue.trim().isEmpty()) {
                blankParameterMessage(parameterName, parameterValue);
                invalidParams = true;
            }
        }
        return invalidParams;
    }

    private void blankParameterMessage(String parameterName, String parameterValue) {
        String errorName = String.format(Messages.PARAMS_ERROR, parameterName);
        String paramMessage = String.format(Messages.PARAMS_BLANK_FIELD, parameterValue);
        request.setAttribute(errorName, paramMessage);
        logger.debug(errorName.concat(Attributes.COLLON_SIGN).concat(paramMessage));
    }

    public boolean isInvalidDriverData() {
        List<Boolean> result = new ArrayList<>();
        result.add(isInvalidData(Attributes.FIRST_NAME,Regex.NAME));
        result.add(isInvalidData(Attributes.LAST_NAME, Regex.NAME));
        result.add(isInvalidData(Attributes.PHONE_NUMBER, Regex.PHONE));
        result.add(isInvalidData(Attributes.DRIVER_LICENCE_NUMBER, Regex.LICENCE_NUMBER));
        result.add(isInvalidData(Attributes.DRIVING_EXPERIENCE, Regex.POSITIVE_NUMBER));
        result.add(isInvalidData(Attributes.EMAIL, Regex.REGEX_EMAIL));
        result.add(isInvalidData(Attributes.PASSWORD, Regex.PASSWORD));

        return result.stream().anyMatch(e -> e.equals(true));
    }

    public boolean isInvalidData(String parameter, String regex) {
        boolean invalidParams = false;
        String parameterValue = request.getParameter(parameter);
        if (!Pattern.matches(regex, parameterValue)) {
            invalidParameterMessage(parameter, parameterValue);
            invalidParams = true;
        }
        return invalidParams;
    }

    private void invalidParameterMessage(String parameterName, String parameterValue) {
        String errorName = String.format(Messages.PARAMS_ERROR, parameterName);
        String paramMessage = String.format(Messages.WRONG_PARAMS, parameterValue);
        request.setAttribute(errorName, paramMessage);
        logger.warn(errorName.concat(Attributes.COLLON_SIGN).concat(paramMessage));
    }



}
