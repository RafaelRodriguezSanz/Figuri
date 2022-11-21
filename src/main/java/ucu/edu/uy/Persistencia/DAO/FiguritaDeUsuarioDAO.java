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
import ucu.edu.uy.Servicio.POJO.Estado;

public class FiguritaDeUsuarioDAO {

    public static boolean createFiguritaDeUsuario(FiguritaDeUsuarioPO figuritaDeUsuario)
            throws IOException, SQLException {
        try {
            readFiguritaDeUsuario(String.valueOf(figuritaDeUsuario.getId_figurita_usuario()));
        } catch (FiguritaDeUsuarioNotFoundException e) {
            DB.getSINGLE_INSTANCE().connect("Figuri");
            String query = SQL.getQuery("DT/FiguritasDeUsuarios/CRUD/createFiguritasDeUsuario");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setString(1, new String(figuritaDeUsuario.getId_figurita_usuario()));
            statement.setInt(2, Integer.valueOf(figuritaDeUsuario.getId_figurita_existente()));
            statement.setString(3, Estado.values()[figuritaDeUsuario.getId_estado_figurita()].toString());
            statement.setInt(4, Integer.valueOf(figuritaDeUsuario.getId_usuario()));
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
            DB.getSINGLE_INSTANCE().connect("Figuri");
            String query = SQL.getQuery("DT/FiguritasDeUsuarios/CRUD/readFiguritasDeUsuario");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setString(1, id);
            ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
            DB.getSINGLE_INSTANCE().disconnect();

            figuritaDeUsuario.setId_figurita_usuario(result.getString(1).toCharArray());
            figuritaDeUsuario.setId_figurita_existente(result.getInt(2));
            figuritaDeUsuario.setId_estado_figurita(Estado.valueOf(result.getString(3)).ordinal());
            figuritaDeUsuario.setId_usuario(result.getInt(4));
            statement.close();
        } catch (Exception e) {
            throw new FiguritaDeUsuarioNotFoundException();
        }
        return figuritaDeUsuario;
    }

    public static boolean deleteFiguritaDeUsuario(String id_figurita_usuario) throws SQLException {
        readFiguritaDeUsuario(id_figurita_usuario);
        DB.getSINGLE_INSTANCE().connect("Figuri");
        String query = SQL.getQuery("DT/FiguritasDeUsuarios/CRUD/deleteFiguritasDeUsuario");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, id_figurita_usuario);
        boolean result = !DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }
}
