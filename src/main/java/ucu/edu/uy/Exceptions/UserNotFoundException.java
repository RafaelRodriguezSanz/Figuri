package ucu.edu.uy.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Exception e) {
        super("User was not found", e);
    }
}
