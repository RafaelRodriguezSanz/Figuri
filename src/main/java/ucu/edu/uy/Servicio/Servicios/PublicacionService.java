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

import ucu.edu.uy.Persistencia.DAO.PublicacionDAO;
import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Persistencia.PO.PublicacionPO;
import ucu.edu.uy.Presentacion.DO.PublicacionDO;
import ucu.edu.uy.Presentacion.Mappers.PublicacionMapper;
import ucu.edu.uy.Servicio.DTO.PublicacionDTO;
import ucu.edu.uy.Servicio.DTO.UserDTO;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Contrasenia;
import static ucu.edu.uy.Persistencia.Utils.Encoder.decode;
import static ucu.edu.uy.Persistencia.Utils.Encoder.encode;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;

public class PublicacionService {
    private static final PublicacionService SINGLE_INSTANCE = new PublicacionService();

    private PublicacionService() {
    }

    public static PublicacionService getInstance() {
        return SINGLE_INSTANCE;
    }

    public String post(String id_figurita_usuario, String id_figurita_existente_1, String id_figurita_existente_2,
            String id_figurita_existente_3) {
        Date fecha = new Date(System.currentTimeMillis());
        char[] id = UUID.randomUUID().toString().toCharArray();
        PublicacionPO publicacion = new PublicacionPO(
                id,
                id_figurita_usuario.toCharArray(),
                "Activa".toCharArray(),
                id_figurita_existente_1 != null ? Integer.valueOf(id_figurita_existente_1) : null,
                id_figurita_existente_2 != null ? Integer.valueOf(id_figurita_existente_2) : null,
                id_figurita_existente_3 != null ? Integer.valueOf(id_figurita_existente_3) : null,
                fecha);
        try {
            return PublicacionDAO.createPublicacion(publicacion);
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean delete(String id_publicacion) throws NoSuchAlgorithmException {
        try {
            return PublicacionDAO.deletePublicacion(id_publicacion);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean activar(String id_publicacion) {
        PublicacionDTO publicacion;
        try {
            publicacion = PostgresORM.getInstance().toDTO(PublicacionDAO.readPublicacion(id_publicacion));
        } catch (SQLException e) {
            return false;
        }
        publicacion.setId_estado_publicacion("Activa");
        PublicacionPO publicacionUpdated = PostgresORM.getInstance().toPO(publicacion);
        try {
            return PublicacionDAO.updatePublicacion(publicacionUpdated);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean caducar(String id_publicacion) {
        PublicacionDTO publicacion;
        try {
            publicacion = PostgresORM.getInstance().toDTO(PublicacionDAO.readPublicacion(id_publicacion));
        } catch (SQLException e) {
            return false;
        }
        publicacion.setId_estado_publicacion("Caducada");
        PublicacionPO publicacionUpdated = PostgresORM.getInstance().toPO(publicacion);
        try {
            return PublicacionDAO.updatePublicacion(publicacionUpdated);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean finalizar(String id_publicacion) {
        PublicacionDTO publicacion;
        try {
            publicacion = PostgresORM.getInstance().toDTO(PublicacionDAO.readPublicacion(id_publicacion));
        } catch (SQLException e) {
            return false;
        }
        publicacion.setId_estado_publicacion("Finalizada");
        PublicacionPO publicacionUpdated = PostgresORM.getInstance().toPO(publicacion);
        try {
            return PublicacionDAO.updatePublicacion(publicacionUpdated);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean inactivar(String id_publicacion) {
        PublicacionDTO publicacion;
        try {
            publicacion = PostgresORM.getInstance().toDTO(PublicacionDAO.readPublicacion(id_publicacion));
        } catch (SQLException e) {
            return false;
        }
        publicacion.setId_estado_publicacion("Inactiva");
        PublicacionPO publicacionUpdated = PostgresORM.getInstance().toPO(publicacion);
        try {
            return PublicacionDAO.updatePublicacion(publicacionUpdated);
        } catch (SQLException e) {
            return false;
        }
    }

    public PublicacionPO read(String id_publicacion) throws NoSuchAlgorithmException {
        try {
            return PublicacionDAO.readPublicacion(id_publicacion);
        } catch (SQLException e) {
            return null;
        }
    }

    public Collection<PublicacionDO> readAll() throws NoSuchAlgorithmException {
        try {
            Collection<PublicacionDO> publicationsConverted = new ArrayList<>();
            Collection<PublicacionPO> publications = PublicacionDAO.readAllPublicacion();
            for (PublicacionPO publicacionPO : publications) {
                PublicacionDO publicacionDO = PublicacionMapper.toDO(PostgresORM.getInstance().toDTO(publicacionPO));
                publicationsConverted.add(publicacionDO);
            }
            return publicationsConverted;
        } catch (SQLException e) {
            return null;
        }
    }
}
