package ucu.edu.uy.Persistencia.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import ucu.edu.uy.Persistencia.PO.OfertaPO;
import ucu.edu.uy.Persistencia.PO.PublicacionPO;
import ucu.edu.uy.Persistencia.Utils.DB;
import ucu.edu.uy.Persistencia.Utils.SQL;

public class OfertaDAO {

    public static String createOferta(OfertaPO oferta) throws SQLException {
        try {
            readOferta(new String(oferta.getId_oferta()));
        } catch (Exception e) {
            DB.getSINGLE_INSTANCE().connect("FiguriTest");
            String query = SQL.getQuery("DT/Ofertas/CRUD/createOferta");
            PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
            Date fecha = new Date(System.currentTimeMillis());

            statement.setString(1, new String(oferta.getId_oferta()));
            statement.setString(2, new String(oferta.getId_publicacion()));
            statement.setString(3, new String(oferta.getId_publicacion1()));
            if (Objects.nonNull(oferta.getId_publicacion2())) {
                statement.setString(4, new String(oferta.getId_publicacion2()));
            } else {
                statement.setString(4, null);
            }
            if (Objects.nonNull(oferta.getId_publicacion3())) {
                statement.setString(5, new String(oferta.getId_publicacion3()));
            } else {
                statement.setString(5, null);
            }
            statement.setDate(6, fecha);
            DB.getSINGLE_INSTANCE().executeQuery(statement);
            statement.close();
            DB.getSINGLE_INSTANCE().disconnect();
        }
        try {
            readOferta(new String(oferta.getId_oferta()));
            return new String(oferta.getId_oferta());
        } catch (Exception ex) {
            return null;
        }
    }

    public static OfertaPO readOferta(String id) throws SQLException {
        OfertaPO oferta;
        oferta = new OfertaPO();
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Ofertas/CRUD/readOferta");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, id);
        ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
        DB.getSINGLE_INSTANCE().disconnect();

        oferta.setId_oferta(result.getString(1).toCharArray());
        oferta.setId_publicacion(result.getString(2).toCharArray());
        oferta.setId_publicacion1(result.getString(3).toCharArray());
        if (Objects.nonNull(result.getString(4))) {
            oferta.setId_publicacion2(result.getString(4).toCharArray());
        } else {
            oferta.setId_publicacion2(null);
        }
        if (Objects.nonNull(result.getString(5))) {
            oferta.setId_publicacion3(result.getString(5).toCharArray());
        } else {
            oferta.setId_publicacion3(null);
        }

        oferta.setFecha(result.getDate(6));
        statement.close();
        return oferta;
    }

    public static boolean deleteOferta(String id) throws SQLException {
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Ofertas/CRUD/deleteOferta");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, id);
        boolean result = !DB.getSINGLE_INSTANCE().executeQuery(statement);
        DB.getSINGLE_INSTANCE().disconnect();
        statement.close();
        return result;
    }

    public static boolean updateOferta(OfertaPO oferta) throws SQLException {
        readOferta(new String(oferta.getId_oferta()));
        Date fecha = new Date(System.currentTimeMillis());
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Ofertas/CRUD/updateOferta");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, new String(oferta.getId_oferta()));
        statement.setString(2, new String(oferta.getId_publicacion()));
        statement.setString(3, new String(oferta.getId_publicacion1()));
        if (Objects.nonNull(oferta.getId_publicacion2())) {
            statement.setString(4, new String(oferta.getId_publicacion2()));
        } else {
            statement.setString(4, null);
        }
        if (Objects.nonNull(oferta.getId_publicacion3())) {
            statement.setString(5, new String(oferta.getId_publicacion3()));
        } else {
            statement.setString(5, null);
        }
        statement.setDate(6, fecha);

        boolean result = !DB.getSINGLE_INSTANCE().executeQuery(statement);
        statement.close();
        DB.getSINGLE_INSTANCE().disconnect();
        return result;
    }

    public static Collection<OfertaPO> readAll(String publicationId) throws SQLException {
        Collection<OfertaPO> ofertas = new ArrayList<>();
        DB.getSINGLE_INSTANCE().connect("FiguriTest");
        String query = SQL.getQuery("DT/Ofertas/readAllOferta");
        PreparedStatement statement = DB.getSINGLE_INSTANCE().getConnection().prepareStatement(query);
        statement.setString(1, publicationId);
        ResultSet result = DB.getSINGLE_INSTANCE().executeAction(statement);
        DB.getSINGLE_INSTANCE().disconnect();
        boolean continues = true;
        while (continues) {
            OfertaPO oferta = new OfertaPO();
            oferta.setId_oferta(result.getString(1).toCharArray());
            oferta.setId_publicacion(result.getString(2).toCharArray());
            oferta.setId_publicacion1(result.getString(3).toCharArray());
            if (Objects.nonNull(result.getString(4))) {
                oferta.setId_publicacion2(result.getString(4).toCharArray());
            } else {
                oferta.setId_publicacion2(null);
            }
            if (Objects.nonNull(result.getString(5))) {
                oferta.setId_publicacion3(result.getString(5).toCharArray());
            } else {
                oferta.setId_publicacion3(null);
            }
            oferta.setFecha(result.getDate(6));
            ofertas.add(oferta);
            continues = result.next();
        }
        statement.close();
        return ofertas;
    }
}
