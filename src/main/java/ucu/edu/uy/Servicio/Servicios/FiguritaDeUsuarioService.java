package ucu.edu.uy.Servicio.Servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import ucu.edu.uy.Persistencia.DAO.FiguritaDeUsuarioDAO;
import ucu.edu.uy.Persistencia.DAO.FiguritaExistenteDAO;
import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Persistencia.PO.FiguritaDeUsuarioPO;
import ucu.edu.uy.Persistencia.PO.FiguritaExistentePO;
import ucu.edu.uy.Presentacion.DO.FiguritaDeUsuarioDO;
import ucu.edu.uy.Presentacion.Mappers.FiguritaDeUsuarioMapper;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;
import ucu.edu.uy.Servicio.POJO.Estado;
import ucu.edu.uy.Servicio.POJO.Utils.Validator;

public class FiguritaDeUsuarioService {
    private static final FiguritaDeUsuarioService SINGLE_INSTANCE = new FiguritaDeUsuarioService();

    private FiguritaDeUsuarioService() {
    }

    public static FiguritaDeUsuarioService getInstance() {
        return SINGLE_INSTANCE;
    }

    public static FiguritaDeUsuarioDO readFigurita(String id_figurita_de_usuario) {
        FiguritaDeUsuarioPO figuritaUsuario = FiguritaDeUsuarioDAO.readFiguritaDeUsuario(id_figurita_de_usuario);
        return FiguritaDeUsuarioMapper.toDO(PostgresORM.getInstance().toDTO(figuritaUsuario));
    }

    public static FiguritaDeUsuarioDTO readFiguritaDTO(String id_figurita_de_usuario) {
        FiguritaDeUsuarioPO figuritaUsuario = FiguritaDeUsuarioDAO.readFiguritaDeUsuario(id_figurita_de_usuario);
        return PostgresORM.getInstance().toDTO(figuritaUsuario);
    }

    public static String createFigurita(String id_figurita_existente, String estado, String id_Usuario) {
        id_Usuario = Validator.cleanNumber(id_Usuario);
        UUID id = UUID.randomUUID();

        FiguritaDeUsuarioDTO figuritaDeUsuario = new FiguritaDeUsuarioDTO(id,
                Integer.valueOf(id_figurita_existente), Estado.valueOf(estado),
                Integer.valueOf(id_Usuario));
        try {
            FiguritaDeUsuarioDAO.createFiguritaDeUsuario(PostgresORM.getInstance().toPO(figuritaDeUsuario));
            return id.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean deleteFigurita(String id_figurita_de_usuario) throws SQLException {
        readFigurita(id_figurita_de_usuario);
        try {
            return FiguritaDeUsuarioDAO.deleteFiguritaDeUsuario(id_figurita_de_usuario);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Collection<FiguritaDeUsuarioDO> readAll() {
        Collection<FiguritaDeUsuarioDO> figuritaDeUsuarioConverted = new ArrayList<>();
        Collection<FiguritaDeUsuarioPO> figuritasDeUsuario = FiguritaDeUsuarioDAO.readAll();
        for (FiguritaDeUsuarioPO figuritaDeUsuarioPO : figuritasDeUsuario) {
            FiguritaDeUsuarioDO figuritaDeUsuarioDO = FiguritaDeUsuarioMapper
                    .toDO(PostgresORM.getInstance().toDTO(figuritaDeUsuarioPO));
            figuritaDeUsuarioConverted.add(figuritaDeUsuarioDO);
        }
        return figuritaDeUsuarioConverted;
    }
}
