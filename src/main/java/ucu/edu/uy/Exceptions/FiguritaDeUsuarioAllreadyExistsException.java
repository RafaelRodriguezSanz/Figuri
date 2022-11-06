package ucu.edu.uy.Exceptions;

public class FiguritaDeUsuarioAllreadyExistsException extends RuntimeException {
    public FiguritaDeUsuarioAllreadyExistsException() {
        super("Existent Figurine allready exists");
    }
}
