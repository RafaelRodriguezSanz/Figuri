package ucu.edu.uy.Servicio.Servicios;

import java.io.IOException;
import java.sql.SQLException;

import org.identityconnectors.common.security.GuardedString;

import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Presentacion.DO.UserDO;
import ucu.edu.uy.Servicio.DTO.UserDTO;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Contrasenia;
import ucu.edu.uy.Servicio.POJO.Direccion;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;

public class UserService {
    private static final UserService SINGLE_INSTANCE = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return SINGLE_INSTANCE;
    }

    public boolean createUser(String nombreParam, String apellidoParam, String contraseniaParam, String direccionParam,
            String telefonoParam, String ciParam) throws IOException, SQLException {
        Nombre nombre = new Nombre(nombreParam, apellidoParam);
        Contrasenia contrasenia = new Contrasenia(new GuardedString(contraseniaParam.toCharArray()));
        Direccion direccion = new Direccion(direccionParam);
        Telefono telefono = new Telefono(telefonoParam);
        CI ci = new CI(ciParam);
        UserDTO user = new UserDTO(nombre, contrasenia, direccion, telefono, ci);
        return UserDAO.createUser(PostgresORM.getInstance().toPO(user));
    }
}
