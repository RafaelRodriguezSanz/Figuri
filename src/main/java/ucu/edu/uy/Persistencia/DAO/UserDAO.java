package ucu.edu.uy.Persistencia.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ucu.edu.uy.Persistencia.PO.UserPO;
import ucu.edu.uy.Persistencia.Utils.DB;
import ucu.edu.uy.Persistencia.Utils.SQL;

public class UserDAO {

    public static boolean createUser(UserPO user) throws IOException, SQLException {
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Users/CRUD/createUser");
        SQL.populateQuery(query,
                String.valueOf(user.getCi()),
                '"' + new String(user.getContrasenia()) + '"',
                '"' + new String(user.getDireccion()) + '"',
                '"' + new String(user.getFoto()) + '"',
                '"' + new String(user.getNombre()) + '"',
                String.valueOf(user.getTelefono()));
        boolean result = DB.getSINGLE_INSTANCE().executeQuery(query);
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }

    public static void readUser() {

    }

    public static void updateUser() {

    }

    public static void deleteUser() {

    }
}
