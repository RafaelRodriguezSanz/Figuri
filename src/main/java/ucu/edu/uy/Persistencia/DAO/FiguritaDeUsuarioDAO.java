package ucu.edu.uy.Persistencia.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ucu.edu.uy.Exceptions.FiguritaDeUsuarioAllreadyExistsException;
import ucu.edu.uy.Exceptions.FiguritaDeUsuarioNotFoundException;
import ucu.edu.uy.Persistencia.PO.FiguritaDeUsuarioPO;
import ucu.edu.uy.Persistencia.Utils.DB;
import ucu.edu.uy.Persistencia.Utils.SQL;

public class FiguritaDeUsuarioDAO {

    public static boolean createFiguritaDeUsuario(FiguritaDeUsuarioPO figuritaDeUsuario)
            throws IOException, SQLException {
        try {
            readFiguritaDeUsuario(String.valueOf(figuritaDeUsuario.getId()));
        } catch (FiguritaDeUsuarioNotFoundException e) {
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/Usuarios/CRUD/createFiguritaDeUsuario");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setString(1, new String(figuritaDeUsuario.getId()));
            statement.setInt(2, figuritaDeUsuario.getFigurita());
            statement.setString(3, new String(figuritaDeUsuario.getEstado()));
            boolean result = DB.getSINGLE_INSTANCE().executeQuery(statement);
            statement.close();
            DB.getSINGLE_INSTANCE().disconnect();
            return result;
        }
        throw new FiguritaDeUsuarioAllreadyExistsException();
    }

    public static FiguritaDeUsuarioPO readFiguritaDeUsuario(String id) {
        FiguritaDeUsuarioPO figuritaDeUsuario;
        try {
            figuritaDeUsuario = new FiguritaDeUsuarioPO();
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/FiguritasDeUsuarios/CRUD/readFiguritaDeUsuario");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setString(1, id);
            ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
            DB.getSINGLE_INSTANCE().disconnect();

            figuritaDeUsuario.setId(result.getString(1).toCharArray());
            figuritaDeUsuario.setFigurita(result.getInt(2));
            figuritaDeUsuario.setEstado(result.getString(3).toCharArray());
            statement.close();
        } catch (Exception e) {
            throw new FiguritaDeUsuarioNotFoundException();
        }
        return figuritaDeUsuario;
    }

    public static boolean updateFiguritaDeUsuario(FiguritaDeUsuarioPO figuritaDeUsuario) throws SQLException {
        readFiguritaDeUsuario(new String(figuritaDeUsuario.getId()));
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Usuarios/CRUD/updateFiguritaDeUsuario");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, new String(figuritaDeUsuario.getId()));
        statement.setInt(2, figuritaDeUsuario.getFigurita());
        statement.setString(3, new String(figuritaDeUsuario.getEstado()));
        statement.setString(4, new String(figuritaDeUsuario.getId()));
        boolean result = DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }

    public static boolean deleteFiguritaDeUsuario(String id) throws SQLException {
        readFiguritaDeUsuario(id);
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Usuarios/CRUD/deleteFiguritaDeUsuario");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, id);
        boolean result = DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }
}
