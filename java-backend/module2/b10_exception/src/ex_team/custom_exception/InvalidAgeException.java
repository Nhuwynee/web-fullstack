package ex_team.custom_exception;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
