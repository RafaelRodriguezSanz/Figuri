package ucu.edu.uy.Exceptions;

public class FiguritaDeUsuarioNotFoundException extends RuntimeException {
    public FiguritaDeUsuarioNotFoundException() {
        super("Figurita de usuario was not found");
    }
}
