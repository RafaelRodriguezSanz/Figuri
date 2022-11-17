package ucu.edu.uy.Persistencia.ORM;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

import org.identityconnectors.common.security.GuardedString;
import ucu.edu.uy.Persistencia.PO.FiguritaDeUsuarioPO;
import ucu.edu.uy.Persistencia.PO.FiguritaExistentePO;
import ucu.edu.uy.Persistencia.PO.OfertaPO;
import ucu.edu.uy.Persistencia.PO.PublicacionPO;
import ucu.edu.uy.Persistencia.PO.UserPO;
import ucu.edu.uy.Persistencia.Utils.Encoder;
import ucu.edu.uy.Presentacion.DO.OfertaDO;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;
import ucu.edu.uy.Servicio.DTO.OfertaDTO;
import ucu.edu.uy.Servicio.DTO.PublicacionDTO;

import static ucu.edu.uy.Persistencia.Utils.Encoder.decode;
import ucu.edu.uy.Servicio.DTO.UserDTO;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Contrasenia;
import ucu.edu.uy.Servicio.POJO.Estado;
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
        int numero = figuritaExistente.getId_figurita_existente();
        char[] tipo = figuritaExistente.getTipo().toCharArray();
        char[] descripcion = figuritaExistente.getDescripcion().toCharArray();
        char[] pais = figuritaExistente.getPais().toCharArray();
        return new FiguritaExistentePO(numero, tipo, descripcion, pais);
    }

    public FiguritaExistenteDTO toDTO(FiguritaExistentePO figuritaExistente) {
        Integer numero = figuritaExistente.getId_figurita_existente();
        String tipo = new String(figuritaExistente.getTipo());
        String descripcion = new String(figuritaExistente.getDescripcion());
        String pais = new String(figuritaExistente.getPais());
        return new FiguritaExistenteDTO(numero, tipo, descripcion, pais);
    }

    public FiguritaDeUsuarioPO toPO(FiguritaDeUsuarioDTO figuritaDeUsuario) {
        char[] id_figurita_usuario = figuritaDeUsuario.getId_figurita_De_Usuario().toString().toCharArray();
        int id_figurita_existente = figuritaDeUsuario.getFigurita();
        int id_estado_figurita = figuritaDeUsuario.getEstado().ordinal();
        int id_usuario = figuritaDeUsuario.getId_usuario();

        return new FiguritaDeUsuarioPO(id_figurita_usuario, id_figurita_existente, id_estado_figurita, id_usuario);
    }

    public FiguritaDeUsuarioDTO toDTO(FiguritaDeUsuarioPO figuritaDeUsuario) {
        UUID id = UUID.fromString(new String(figuritaDeUsuario.getId_figurita_usuario()));
        Integer figurita = figuritaDeUsuario.getId_figurita_existente();
        Estado estado = Estado.values()[figuritaDeUsuario.getId_estado_figurita()];
        Integer id_usuario = figuritaDeUsuario.getId_usuario();
        return new FiguritaDeUsuarioDTO(id, figurita, estado, id_usuario);
    }

    public PublicacionPO toPO(PublicacionDTO publicacion) {
        return new PublicacionPO(publicacion.getId_publicacion().toCharArray(),
                publicacion.getId_figurita_usuario().toCharArray(),
                publicacion.getId_estado_publicacion().toCharArray(),
                publicacion.getId_figurita_existente_1(),
                publicacion.getId_figurita_existente_2(),
                publicacion.getId_figurita_existente_3(),
                publicacion.getFecha());
    }

    public PublicacionDTO toDTO(PublicacionPO publicacion) {

        return new PublicacionDTO(new String(publicacion.getId_publicacion()),
                new String(publicacion.getId_figurita_usuario()),
                new String(publicacion.getId_estado_publicacion()),
                Integer.valueOf(publicacion.getId_figurita_existente_1()),
                Integer.valueOf(publicacion.getId_figurita_existente_2()),
                Integer.valueOf(publicacion.getId_figurita_existente_3()),
                publicacion.getFecha());
    }

    public OfertaPO toPO(OfertaDTO oferta) {
        return new OfertaPO(
                oferta.getId_oferta().toCharArray(),
                oferta.getId_publicacion().toCharArray(),
                oferta.getId_publicacion1().toCharArray(),
                oferta.getId_publicacion2().toCharArray(),
                oferta.getId_publicacion3().toCharArray(),
                oferta.getFecha());
    }

    public OfertaDTO toDTO(OfertaPO oferta) {
        return new OfertaDTO(
                new String(oferta.getId_oferta()),
                new String(oferta.getId_publicacion()),
                new String(oferta.getId_publicacion1()),
                new String(oferta.getId_publicacion2()),
                new String(oferta.getId_publicacion3()),
                oferta.getFecha());
    }
}
