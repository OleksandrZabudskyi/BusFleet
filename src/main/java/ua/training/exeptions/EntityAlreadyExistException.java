package ua.training.exeptions;

/**
 *  Class marks exception for specific case and storing some data
 *
 * @author Zabudskyi Oleksandr
 * @see Exception
 */
public class EntityAlreadyExistException extends Exception {

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
