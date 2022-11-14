package ucu.edu.uy.Exceptions;

public class FiguritaExistenteNotFoundException extends RuntimeException {
    public FiguritaExistenteNotFoundException(Exception e) {
        super("Existent Figurine was not found", e);
    }
}
