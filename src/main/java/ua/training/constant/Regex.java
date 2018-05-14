package ua.training.constant;

public interface Regex {
    String REGEX_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    String PHONE = "^\\+\\d{2}\\d{3}\\d{3}-\\d{2}-\\d{2}$";
    String URL = "(.*\\/app\\/)|(bus-fleet\\/)|(admin\\/)|(driver\\/)";
}
