package ucu.edu.uy.Persistencia.ORM;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import org.identityconnectors.common.security.GuardedString;
import ucu.edu.uy.Persistencia.PO.FiguritaDeUsuarioPO;
import ucu.edu.uy.Persistencia.PO.FiguritaExistentePO;
import ucu.edu.uy.Persistencia.PO.UserPO;
import ucu.edu.uy.Persistencia.Utils.Encoder;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;
import static ucu.edu.uy.Persistencia.Utils.Encoder.decode;
import ucu.edu.uy.Servicio.DTO.UserDTO;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Contrasenia;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;

public class PostgresORM {
    private static final PostgresORM SINGLE_INSTANCE = new PostgresORM();

    private PostgresORM() {
    }

    public static PostgresORM getInstance() {
        return SINGLE_INSTANCE;
    }

    public UserPO toPO(UserDTO user, String contrasenia) throws NoSuchAlgorithmException {
        char[] nombre = user.getNombre().getNombre().toCharArray();
        char[] apellido = user.getNombre().getAppelido().toCharArray();
        int telefono = user.getTelefono().getDigitos();
        int ci = user.getCi().getDigitos();
        return new UserPO(ci, nombre, apellido, telefono, contrasenia.toCharArray());
    }

    public UserDTO toDTO(UserPO user) {
        Nombre nombre = new Nombre(String.valueOf(user.getNombre()), String.valueOf(user.getApellido()));
        Contrasenia contrasenia = new Contrasenia(
                new GuardedString(String.valueOf(user.getContrasenia()).toCharArray()));

        /*
         * Securely wipe the char array by storing random values in it.
         * Some standards require multiple rounds of overwriting; see:
         * https://en.wikipedia.org/wiki/Data_erasure#Standards
         * Extracted from:
         * https://stackoverflow.com/questions/51242150/java-equivalent-of-securestring/
         * 52861568#52861568
         */
        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < user.getContrasenia().length; i++)
            user.getContrasenia()[i] = (char) sr.nextInt(Character.MAX_VALUE + 1);
        // noinspection UnusedAssignment
        user.setContrasenia(null);
        Telefono telefono = new Telefono(String.valueOf(user.getTelefono()));
        CI ci = new CI(String.valueOf(user.getCi()));
        return new UserDTO(ci, nombre, telefono, contrasenia);
    }

    public FiguritaExistentePO toPO(FiguritaExistenteDTO figuritaExistente) {
        int numero = figuritaExistente.getNumero();
        char[] tipo = figuritaExistente.getTipo().toCharArray();
        char[] descripcion = figuritaExistente.getDescripcion().toCharArray();
        char[] pais = figuritaExistente.getPais().toCharArray();
        return new FiguritaExistentePO(numero, tipo, descripcion, pais);
    }

    public FiguritaExistenteDTO toDTO(FiguritaExistentePO figuritaExistente) {
        Integer numero = figuritaExistente.getNumero();
        String tipo = new String(figuritaExistente.getTipo());
        String descripcion = new String(figuritaExistente.getDescripcion());
        String pais = new String(figuritaExistente.getPais());
        return new FiguritaExistenteDTO(numero, tipo, descripcion, pais);
    }

    public FiguritaDeUsuarioPO toPO(FiguritaDeUsuarioDTO figuritaDeUsuario) {
        char[] id = figuritaDeUsuario.getId().toString().toCharArray();
        int figurita = figuritaDeUsuario.getFigurita();
        char[] estado = figuritaDeUsuario.getEstado().toCharArray();
        return new FiguritaDeUsuarioPO(id, figurita, estado);
    }

    public FiguritaDeUsuarioDTO toDTO(FiguritaDeUsuarioPO figuritaDeUsuario) {
        UUID id = UUID.fromString(new String(figuritaDeUsuario.getId()));
        Integer figurita = figuritaDeUsuario.getFigurita();
        String estado = new String(figuritaDeUsuario.getEstado());
        return new FiguritaDeUsuarioDTO(id, figurita, estado);
    }

}
