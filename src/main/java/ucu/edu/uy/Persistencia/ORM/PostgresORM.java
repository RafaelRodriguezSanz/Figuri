package ucu.edu.uy.Persistencia.ORM;

import java.util.UUID;

import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.common.security.GuardedByteArray.Accessor;

import ucu.edu.uy.Persistencia.PO.FiguritaDeUsuarioPO;
import ucu.edu.uy.Persistencia.PO.FiguritaExistentePO;
import ucu.edu.uy.Persistencia.PO.UserPO;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;
import ucu.edu.uy.Servicio.DTO.UserDTO;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Contrasenia;
import ucu.edu.uy.Servicio.POJO.Direccion;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;
import ucu.edu.uy.Servicio.POJO.Utils.PassAccessor;

public class PostgresORM {
    private static final PostgresORM SINGLE_INSTANCE = new PostgresORM();

    private PostgresORM() {
    }

    public static PostgresORM getInstance() {
        return SINGLE_INSTANCE;
    }

    public UserPO toPO(UserDTO user) {
        char[] nombre = user.getNombre().getNombre().toCharArray();
        char[] apellido = user.getNombre().getAppelido().toCharArray();
        char[] contrasenia = new char[62];
        PassAccessor accessor = new PassAccessor();
        user.getContrasenia().getPass().access(new PassAccessor());
        accessor.access(contrasenia);
        char[] direccion = user.getDireccion().getDireccion().toCharArray();
        int telefono = user.getTelefono().getDigitos();
        int ci = user.getCi().getDigitos();
        return new UserPO(nombre, apellido, contrasenia, direccion, telefono, ci);
    }

    public UserDTO toDTO(UserPO user) {
        Nombre nombre = new Nombre(String.valueOf(user.getNombre()), String.valueOf(user.getApellido()));
        Contrasenia contrasenia = new Contrasenia(new GuardedString(user.getContrasenia()));
        Direccion direccion = new Direccion(String.valueOf(user.getDireccion()));
        Telefono telefono = new Telefono(String.valueOf(user.getTelefono()));
        CI ci = new CI(String.valueOf(user.getCi()));
        return new UserDTO(nombre, contrasenia, direccion, telefono, ci);
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
