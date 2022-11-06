package ucu.edu.uy.Exceptions;

public class FiguritaDeUsuarioNotFoundException extends RuntimeException {
    public FiguritaDeUsuarioNotFoundException() {
        super("User was not found");
    }
}
