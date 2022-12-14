package ucu.edu.uy.Servicio.Servicios;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.identityconnectors.common.security.GuardedString;

import ucu.edu.uy.Persistencia.DAO.OfertaDAO;
import ucu.edu.uy.Persistencia.DAO.PublicacionDAO;
import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Persistencia.PO.OfertaPO;
import ucu.edu.uy.Persistencia.PO.PublicacionPO;
import ucu.edu.uy.Presentacion.DO.OfertaDO;
import ucu.edu.uy.Presentacion.Mappers.OfertaMapper;
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

    public String createOferta(String id_publicacion, String... ofertasParam) throws SQLException {
        if (ofertasParam.length < 4) {
            String[] ofertas = new String[3];
            int i;
            for (i = 0; i < ofertasParam.length; i++) {
                ofertas[i] = ofertasParam[i];
            }
            for (int j = i; j < 3; j++) {
                ofertas[j] = null;
            }
            OfertaPO oferta = new OfertaPO(id_publicacion, ofertas);
            OfertaDAO.createOferta(oferta);
            return new String(oferta.getId_oferta());
        } else {
            return null;
        }
    }

    public boolean deleteOferta(String id) {
        try {
            return OfertaDAO.deleteOferta(id);
        } catch (SQLException e) {
            return false;
        }
    }

    public OfertaPO readOferta(String id) {
        try {
            return OfertaDAO.readOferta(id);
        } catch (SQLException e) {
            return null;
        }
    }

    public Collection<OfertaDO> readAll(String publicationId) {
        try {
            Collection<OfertaDO> ofertasConverted = new ArrayList<>();
            Collection<OfertaPO> ofertas = OfertaDAO.readAll(publicationId);
            for (OfertaPO ofertaPO : ofertas) {
                OfertaDO ofertaDO = OfertaMapper.toDO(PostgresORM.getInstance().toDTO(ofertaPO));
                ofertasConverted.add(ofertaDO);
            }
            return ofertasConverted;
        } catch (SQLException e) {
            return null;
        }
    }

    public String changeOferta(String id_publicacion, String... ofertasParam) throws SQLException {
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
