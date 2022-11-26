package ucu.edu.uy.Servicio.Servicios;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

import org.identityconnectors.common.security.GuardedString;

import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Servicio.DTO.UserDTO;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Contrasenia;
import static ucu.edu.uy.Persistencia.Utils.Encoder.decode;
import static ucu.edu.uy.Persistencia.Utils.Encoder.encode;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;

public class UserService {
    private static final UserService SINGLE_INSTANCE = new UserService();

    private UserService() {
    }

    public static UserService getInstance() {
        return SINGLE_INSTANCE;
    }

    public boolean register(String ciParam, String nombreParam, String apellidoParam, String telefonoParam,
            String contraseniaParam) throws IOException, SQLException, NoSuchAlgorithmException {
        Nombre nombre = new Nombre(nombreParam, apellidoParam);
        Contrasenia contrasenia = new Contrasenia(new GuardedString(contraseniaParam.toCharArray()));
        Telefono telefono = new Telefono(telefonoParam);
        CI ci = new CI(ciParam);
        UserDTO user = new UserDTO(ci, nombre, telefono, contrasenia);
        return UserDAO.createUser(PostgresORM.getInstance().toPO(user, encode(contraseniaParam)));
    }

    public boolean login(String ciParam, String contraseniaParam) throws NoSuchAlgorithmException {
        CI ci = new CI(ciParam);
        UserDTO user = PostgresORM.getInstance().toDTO(UserDAO.readUser(ci.getDigitos()));
        String encodedPass = encode(contraseniaParam);
        return Objects.equals(encodedPass, decode(user.getContrasenia().getPass()));
    }

    public boolean changePassword(String ciParam, String newPassword) throws NoSuchAlgorithmException, SQLException {
        CI ci = new CI(ciParam);
        UserDTO user = PostgresORM.getInstance().toDTO(UserDAO.readUser(ci.getDigitos()));
        user.setContrasenia(new Contrasenia(new GuardedString(ciParam.toCharArray())));
        return UserDAO.updateUser(PostgresORM.getInstance().toPO(user, encode(newPassword)))
                && login(ciParam, newPassword);
    }

    public boolean deleteUser(String ciParam, String password) throws NoSuchAlgorithmException, SQLException {
        CI ci = new CI(ciParam);
        boolean result = UserDAO.deleteUser(ci.getDigitos());
        try {
            login(ciParam, password);
            return false;
        } catch (Exception e) {
            return result;
        }
    }
}
