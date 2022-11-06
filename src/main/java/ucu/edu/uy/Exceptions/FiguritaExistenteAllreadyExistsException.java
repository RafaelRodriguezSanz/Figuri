package ucu.edu.uy.Exceptions;

public class FiguritaExistenteAllreadyExistsException extends RuntimeException {
    public FiguritaExistenteAllreadyExistsException() {
        super("Existent Figurine allready exists");
    }
}
