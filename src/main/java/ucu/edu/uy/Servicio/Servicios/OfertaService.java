package ucu.edu.uy.Servicio.Servicios;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

import org.identityconnectors.common.security.GuardedString;

import ucu.edu.uy.Persistencia.DAO.OfertaDAO;
import ucu.edu.uy.Persistencia.DAO.PublicacionDAO;
import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Persistencia.PO.OfertaPO;
import ucu.edu.uy.Persistencia.PO.PublicacionPO;
import ucu.edu.uy.Servicio.DTO.PublicacionDTO;
import ucu.edu.uy.Servicio.DTO.UserDTO;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Contrasenia;
import static ucu.edu.uy.Persistencia.Utils.Encoder.decode;
import static ucu.edu.uy.Persistencia.Utils.Encoder.encode;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;

public class OfertaService {
    private static final OfertaService SINGLE_INSTANCE = new OfertaService();

    private OfertaService() {
    }

    public static OfertaService getInstance() {
        return SINGLE_INSTANCE;
    }

    public static String createOferta(String id_publicacion, String... ofertasParam) throws SQLException {
        if (ofertasParam.length < 4) {
            String[] ofertas = new String[3];
            int i;
            for (i = 0; i < ofertasParam.length; i++) {
                ofertas[i] = ofertasParam[i];
            }
            for (int j = i; j < 4; j++) {
                ofertas[i] = null;
            }
            OfertaPO oferta = new OfertaPO(id_publicacion, ofertas);
            OfertaDAO.createOferta(oferta);
            return new String(oferta.getId_oferta());
        } else {
            return null;
        }
    }

    public static boolean deleteOferta(String id) {
        try {
            return OfertaDAO.deleteOferta(id);
        } catch (SQLException e) {
            return false;
        }
    }

    public static String changeOferta(String id_publicacion, String... ofertasParam) throws SQLException {
        if (ofertasParam.length < 4) {
            String[] ofertas = new String[3];
            int i;
            for (i = 0; i < ofertasParam.length; i++) {
                ofertas[i] = ofertasParam[i];
            }
            for (int j = i; j < 4; j++) {
                ofertas[i] = null;
            }
            OfertaPO oferta = new OfertaPO(id_publicacion, ofertas);
            OfertaDAO.updateOferta(oferta);
            return new String(oferta.getId_oferta());
        } else {
            return null;
        }
    }
}
