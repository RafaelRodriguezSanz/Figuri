package ucu.edu.uy.Persistencia.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ucu.edu.uy.Exceptions.FiguritaDeUsuarioNotFoundException;
import ucu.edu.uy.Exceptions.FiguritaExistenteAllreadyExistsException;
import ucu.edu.uy.Exceptions.FiguritaExistenteNotFoundException;
import ucu.edu.uy.Persistencia.PO.FiguritaExistentePO;
import ucu.edu.uy.Persistencia.Utils.DB;
import ucu.edu.uy.Persistencia.Utils.SQL;

public class FiguritaExistenteDAO {

    public static FiguritaExistentePO readFiguritaExistente(int numero) throws FiguritaDeUsuarioNotFoundException {
        FiguritaExistentePO figuritaExistente;
        try {
            figuritaExistente = new FiguritaExistentePO();
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/FiguritasExistentes/CRUD/readFiguritasExistente");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setInt(1, numero);
            ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
            DB.getSINGLE_INSTANCE().disconnect();

            figuritaExistente.setId_figurita_existente(result.getInt(1));
            figuritaExistente.setTipo(result.getString(2).toCharArray());
            figuritaExistente.setDescripcion(result.getString(3).toCharArray());
            figuritaExistente.setPais(result.getString(4).toCharArray());
            statement.close();
        } catch (Exception e) {
            throw new FiguritaExistenteNotFoundException();
        }
        return figuritaExistente;
    }
}
