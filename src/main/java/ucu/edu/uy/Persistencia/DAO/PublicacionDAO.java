package ucu.edu.uy.Persistencia.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import ucu.edu.uy.Persistencia.PO.PublicacionPO;
import ucu.edu.uy.Persistencia.Utils.DB;
import ucu.edu.uy.Persistencia.Utils.SQL;

public class PublicacionDAO {

    public static String createPublicacion(PublicacionPO publicacion) throws SQLException {
        try {
            readPublicacion(new String(publicacion.getId_publicacion()));
        } catch (Exception e) {
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/Publicaciones/CRUD/createPublicacion");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            Date fecha = new Date(System.currentTimeMillis());
            statement.setString(1, new String(publicacion.getId_publicacion()));
            statement.setString(2, new String(publicacion.getId_figurita_usuario()));
            statement.setString(3, new String(publicacion.getId_estado_publicacion()));
            statement.setInt(4, Integer.valueOf(publicacion.getId_figurita_existente_1()));
            statement.setInt(5,
                    publicacion.getId_figurita_existente_2() != null
                            ? Integer.valueOf(publicacion.getId_figurita_existente_2())
                            : null);
            statement.setInt(6,
                    publicacion.getId_figurita_existente_3() != null
                            ? Integer.valueOf(publicacion.getId_figurita_existente_3())
                            : null);
            statement.setDate(7, fecha);
            DB.getSINGLE_INSTANCE().executeQuery(statement);
            statement.close();
            DB.getSINGLE_INSTANCE().disconnect();
        }
        try {
            readPublicacion(new String(publicacion.getId_publicacion()));
            return new String(publicacion.getId_publicacion());
        } catch (Exception ex) {
            return null;
        }
    }

    public static PublicacionPO readPublicacion(String id) throws SQLException {
        PublicacionPO publicacion;
        publicacion = new PublicacionPO();
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Publicaciones/CRUD/readPublicacion");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, id);
        ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
        DB.getSINGLE_INSTANCE().disconnect();

        publicacion.setId_publicacion(result.getString(1).toCharArray());
        publicacion.setId_figurita_usuario(result.getString(2).toCharArray());
        publicacion.setId_estado_publicacion(result.getString(3).toCharArray());
        publicacion.setId_figurita_existente_1(result.getInt(4));
        publicacion.setId_figurita_existente_2(result.getInt(5));
        publicacion.setId_figurita_existente_3(result.getInt(6));
        publicacion.setFecha(result.getDate(7));
        statement.close();
        return publicacion;
    }

    public static boolean deletePublicacion(String id) throws SQLException {
        System.out.println("Deleting: " + id);
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Publicaciones/CRUD/deletePublicacion");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        System.out.println(statement.toString());
        statement.setString(1, id);
        statement.setString(2, id);
        statement.setString(3, id);
        statement.setString(4, id);
        statement.setString(5, id);
        boolean result = false;
        try {
            result = !DB.getSINGLE_INSTANCE().executeQuery(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Deleted: " + result);
        DB.getSINGLE_INSTANCE().disconnect();
        statement.close();
        return result;
    }

    public static boolean updatePublicacion(PublicacionPO publicacion) throws SQLException {
        readPublicacion(new String(publicacion.getId_publicacion()));
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Publicaciones/CRUD/updatePublicacion");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, new String(publicacion.getId_publicacion()));
        statement.setString(2, new String(publicacion.getId_figurita_usuario()));
        statement.setString(3, new String(publicacion.getId_estado_publicacion()));
        statement.setInt(4, Integer.valueOf(publicacion.getId_figurita_existente_1()));
        statement.setInt(5, Integer.valueOf(publicacion.getId_figurita_existente_2()));
        statement.setInt(6, Integer.valueOf(publicacion.getId_figurita_existente_3()));
        statement.setDate(7, publicacion.getFecha());
        statement.setString(8, new String(publicacion.getId_publicacion()));

        boolean result = !DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }

    public static Collection<PublicacionPO> readAllPublicacion() throws SQLException {
        Collection<PublicacionPO> publications = new ArrayList<>();
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Publicaciones/readAllPublicacion");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
        DB.getSINGLE_INSTANCE().disconnect();
        boolean continues = true;
        while (continues) {
            PublicacionPO publicacion;
            publicacion = new PublicacionPO();
            publicacion.setId_publicacion(result.getString(1).toCharArray());
            publicacion.setId_figurita_usuario(result.getString(2).toCharArray());
            publicacion.setId_estado_publicacion(result.getString(3).toCharArray());
            publicacion.setId_figurita_existente_1(result.getInt(4));
            publicacion.setId_figurita_existente_2(result.getInt(5));
            publicacion.setId_figurita_existente_3(result.getInt(6));
            publicacion.setFecha(result.getDate(7));
            publications.add(publicacion);
            continues = result.next();
        }
        statement.close();
        return publications;
    }
}
