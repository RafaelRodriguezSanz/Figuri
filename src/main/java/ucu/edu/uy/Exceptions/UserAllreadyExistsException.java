package ucu.edu.uy.Exceptions;

public class UserAllreadyExistsException extends RuntimeException {
    public UserAllreadyExistsException() {
        super("User allready exists");
    }
}
