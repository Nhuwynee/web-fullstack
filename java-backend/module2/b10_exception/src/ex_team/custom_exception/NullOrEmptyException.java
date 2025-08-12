package ex_team.custom_exception;

public class NullOrEmptyException extends Exception {
    public NullOrEmptyException(String message) {
        super(message);
    }
}
