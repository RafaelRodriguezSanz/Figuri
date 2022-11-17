package ucu.edu.uy.Exceptions;

public class FiguritaExistenteNotFoundException extends RuntimeException {
    public FiguritaExistenteNotFoundException() {
        super("Existent Figurine was not found");
    }
}
