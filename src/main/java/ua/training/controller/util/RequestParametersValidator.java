package ua.training.controller.util;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.Messages;
import ua.training.constant.Regex;
import ua.training.controller.command.LoginCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParametersValidator {
    private static Logger logger = Logger.getLogger(LoginCommand.class);
    private final HttpServletRequest request;

    public RequestParametersValidator(HttpServletRequest request) {
        this.request = request;
    }

    public boolean validateIfNullOrEmpty(String... parameters) {
        boolean invalidParams = false;
        for (String parameterName : parameters) {
            String parameterValue = request.getParameter(parameterName);
            if (Objects.isNull(parameterValue) || parameterValue.trim().isEmpty()) {
                blankParameterMessage(parameterName);
                invalidParams = true;
            }
        }
        return invalidParams;
    }

    private void blankParameterMessage(String parameterName) {
        String errorName = String.format(Messages.PARAMS_ERROR, parameterName);
        String paramMessage = String.format(Messages.PARAMS_BLANK_FIELD, parameterName);
        request.setAttribute(errorName, paramMessage);
        logger.warn(errorName.concat(Attributes.COLLON_SIGN).concat(paramMessage));
    }

    public boolean validateDriverData() {
        List<Boolean> result = new ArrayList<>();
        result.add(validateData(Attributes.FIRST_NAME,Regex.NAME));
        result.add(validateData(Attributes.LAST_NAME, Regex.NAME));
        result.add(validateData(Attributes.PHONE_NUMBER, Regex.PHONE));
        result.add(validateData(Attributes.DRIVER_LICENCE_NUMBER, Regex.LICENCE_NUMBER));
        result.add(validateData(Attributes.DRIVING_EXPERIENCE, Regex.POSITIVE_NUMBER));
        result.add(validateData(Attributes.EMAIL, Regex.REGEX_EMAIL));
        result.add(validateData(Attributes.PASSWORD, Regex.PASSWORD));

        return result.stream().allMatch(e -> e.equals(true));
    }

    public boolean validateData(String parameter, String regex) {
        boolean invalidParams = false;
        String parameterValue = request.getParameter(parameter);
        if (!Pattern.matches(regex, parameterValue)) {
            invalidParameterMessage(parameter);
            invalidParams = true;
        }
        return invalidParams;
    }

    private void invalidParameterMessage(String parameterName) {
        String errorName = String.format(Messages.PARAMS_ERROR, parameterName);
        String paramMessage = String.format(Messages.WRONG_PARAMS, parameterName);
        request.setAttribute(errorName, paramMessage);
        logger.warn(errorName.concat(Attributes.COLLON_SIGN).concat(paramMessage));
    }



}
