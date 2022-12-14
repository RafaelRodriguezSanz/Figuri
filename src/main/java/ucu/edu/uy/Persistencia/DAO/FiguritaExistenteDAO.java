package ucu.edu.uy.Persistencia.DAO;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
            DB.getSINGLE_INSTANCE().connect("Figuri");
            String query = SQL.getQuery("DT/FiguritasExistentes/CRUD/readFiguritasExistente");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            statement.setInt(1, numero);
            ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
            DB.getSINGLE_INSTANCE().disconnect();

            figuritaExistente.setId_figurita_existente(result.getInt(1));
            figuritaExistente.setTipo(result.getString(2).toCharArray());
            figuritaExistente.setDescripcion(result.getString(3).toCharArray());
            figuritaExistente.setPais(result.getString(4));
            statement.close();
        } catch (Exception e) {
            throw new FiguritaExistenteNotFoundException();
        }
        return figuritaExistente;
    }

    public static Collection<FiguritaExistentePO> readAll() throws FiguritaDeUsuarioNotFoundException {
        Collection<FiguritaExistentePO> figuritasExistente = new ArrayList<>();
        try {
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/FiguritasExistentes/readAllFiguritasExistentes");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
            DB.getSINGLE_INSTANCE().disconnect();

            boolean continues = true;
            while (continues) {
                FiguritaExistentePO figuritaExistente;
                figuritaExistente = new FiguritaExistentePO();
                figuritaExistente.setId_figurita_existente(result.getInt(1));
                figuritaExistente.setTipo(result.getString(2).toCharArray());
                figuritaExistente.setDescripcion(result.getString(3).toCharArray());
                figuritaExistente.setPais(result.getString(4));
                figuritasExistente.add(figuritaExistente);
                continues = result.next();
            }
            statement.close();
        } catch (Exception e) {
            throw new FiguritaExistenteNotFoundException();
        }
        return figuritasExistente;
    }
}
