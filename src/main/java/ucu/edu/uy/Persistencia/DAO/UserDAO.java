package ucu.edu.uy.Persistencia.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ucu.edu.uy.Exceptions.UserAllreadyExistsException;
import ucu.edu.uy.Exceptions.UserNotFoundException;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Persistencia.PO.UserPO;
import ucu.edu.uy.Persistencia.Utils.DB;
import ucu.edu.uy.Persistencia.Utils.SQL;
import ucu.edu.uy.Servicio.DTO.UserDTO;

public class UserDAO {

    public static boolean createUser(UserPO user) throws IOException, SQLException {
        try {
            readUser(user.getCi());
        } catch (UserNotFoundException e) {
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/Usuarios/CRUD/createUsuario");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setInt(1, user.getCi());
            statement.setString(2, new String(user.getNombre()));
            statement.setString(3, new String(user.getApellido()));
            statement.setInt(4, user.getTelefono());
            statement.setString(5, String.valueOf(user.getContrasenia()));
            DB.getSINGLE_INSTANCE().executeQuery(statement);
            statement.close();
            DB.getSINGLE_INSTANCE().disconnect();
            try {
                readUser(user.getCi());
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        throw new UserAllreadyExistsException();
    }

    public static UserPO readUser(int ci) {
        UserPO user;
        try {
            user = new UserPO();
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/Usuarios/CRUD/readUsuario");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setInt(1, Integer.valueOf(ci));
            ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
            DB.getSINGLE_INSTANCE().disconnect();

            user.setCi(result.getInt(1));
            user.setNombre(result.getString(2).toCharArray());
            user.setApellido(result.getString(3).toCharArray());
            user.setTelefono(result.getInt(4));
            user.setContrasenia(result.getString(5).toCharArray());
            statement.close();
        } catch (Exception e) {
            throw new UserNotFoundException(e);
        }
        return user;
    }

    public static boolean updateUser(UserPO user) throws SQLException {
        readUser(user.getCi());
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Usuarios/CRUD/updateUsuario");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setInt(1, user.getCi());
        statement.setString(2, new String(user.getNombre()));
        statement.setString(3, new String(user.getApellido()));
        statement.setInt(4, user.getTelefono());
        statement.setString(5, String.valueOf(user.getContrasenia()));
        statement.setInt(6, user.getCi());
        boolean result = !DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }

    public static boolean deleteUser(int ci) throws SQLException {
        readUser(ci);
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Usuarios/CRUD/deleteUsuario");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setInt(1, ci);
        statement.setInt(2, ci);
        statement.setInt(3, ci);
        boolean result = !DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }
}
