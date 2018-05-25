package ua.training.exeptions;

public class EntityAlreadyHandledException extends Exception {
    private int id;

    public EntityAlreadyHandledException(String message, int id) {
        super(message);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
