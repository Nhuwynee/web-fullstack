package ex_team.custom_exception;

public class TeacherNotFoundException extends Exception {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
