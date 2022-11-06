package ucu.edu.uy.Persistencia.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ucu.edu.uy.Exceptions.FiguritaDeUsuarioNotFoundException;
import ucu.edu.uy.Exceptions.FiguritaExistenteAllreadyExistsException;
import ucu.edu.uy.Persistencia.PO.FiguritaExistentePO;
import ucu.edu.uy.Persistencia.Utils.DB;
import ucu.edu.uy.Persistencia.Utils.SQL;

public class FiguritaExistenteDAO {

    public static boolean createFiguritaExistente(FiguritaExistentePO figuritaExistente)
            throws IOException, SQLException {
        try {
            readFiguritaExistente(figuritaExistente.getNumero());
        } catch (FiguritaDeUsuarioNotFoundException e) {
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/Usuarios/CRUD/createFiguritaExistente");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setInt(1, figuritaExistente.getNumero());
            statement.setString(2, new String(figuritaExistente.getTipo()));
            statement.setString(3, new String(figuritaExistente.getDescripcion()));
            statement.setString(4, new String(figuritaExistente.getPais()));
            boolean result = DB.getSINGLE_INSTANCE().executeQuery(statement);
            statement.close();
            DB.getSINGLE_INSTANCE().disconnect();
            return result;
        }
        throw new FiguritaExistenteAllreadyExistsException();
    }

    public static FiguritaExistentePO readFiguritaExistente(int numero) {
        FiguritaExistentePO figuritaExistente;
        try {
            figuritaExistente = new FiguritaExistentePO();
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/FiguritasDeUsuarios/CRUD/readFiguritaExistente");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setInt(1, numero);
            ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
            DB.getSINGLE_INSTANCE().disconnect();

            figuritaExistente.setNumero(result.getInt(1));
            figuritaExistente.setTipo(result.getString(2).toCharArray());
            figuritaExistente.setDescripcion(result.getString(3).toCharArray());
            figuritaExistente.setPais(result.getString(4).toCharArray());
            statement.close();
        } catch (Exception e) {
            throw new FiguritaDeUsuarioNotFoundException();
        }
        return figuritaExistente;
    }

    public static boolean updateFiguritaExistente(FiguritaExistentePO figuritaExistente) throws SQLException {
        readFiguritaExistente(figuritaExistente.getNumero());
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Usuarios/CRUD/updateFiguritaExistente");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setInt(1, figuritaExistente.getNumero());
        statement.setString(2, new String(figuritaExistente.getTipo()));
        statement.setString(3, new String(figuritaExistente.getDescripcion()));
        statement.setString(4, new String(figuritaExistente.getPais()));
        statement.setInt(5, figuritaExistente.getNumero());
        boolean result = DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }

    public static boolean deleteFiguritaExistente(int numero) throws SQLException {
        readFiguritaExistente(numero);
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Usuarios/CRUD/deleteFiguritaExistente");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setInt(1, numero);
        boolean result = DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }
}
