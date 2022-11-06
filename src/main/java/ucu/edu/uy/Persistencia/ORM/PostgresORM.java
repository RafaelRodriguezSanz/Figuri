package ucu.edu.uy.Persistencia.ORM;

import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.common.security.GuardedByteArray.Accessor;

import ucu.edu.uy.Persistencia.PO.UserPO;
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
}
