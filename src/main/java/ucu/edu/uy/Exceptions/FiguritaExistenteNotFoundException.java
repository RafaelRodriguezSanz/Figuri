package ucu.edu.uy.Exceptions;

public class FiguritaExistenteNotFoundException extends RuntimeException {
    public FiguritaExistenteNotFoundException(Exception e) {
        super("User was not found", e);
    }
}
