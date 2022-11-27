package ucu.edu.uy.Servicio.Servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ucu.edu.uy.Persistencia.DAO.FiguritaExistenteDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Persistencia.PO.FiguritaDeUsuarioPO;
import ucu.edu.uy.Persistencia.PO.FiguritaExistentePO;
import ucu.edu.uy.Presentacion.DO.FiguritaExistenteDO;
import ucu.edu.uy.Presentacion.Mappers.FiguritaExistenteMapper;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;

public class FiguritaExistenteService {
    private static final FiguritaExistenteService SINGLE_INSTANCE = new FiguritaExistenteService();

    private FiguritaExistenteService() {
    }

    public static FiguritaExistenteService getInstance() {
        return SINGLE_INSTANCE;
    }

    public static FiguritaExistenteDO getFigurita(String id_figurita_existente) {
        FiguritaExistentePO figurita = FiguritaExistenteDAO
                .readFiguritaExistente(Integer.valueOf(id_figurita_existente));
        FiguritaExistenteDTO figuritaDto = PostgresORM.getInstance().toDTO(figurita);
        return FiguritaExistenteMapper.toDO(figuritaDto);
    }

    public static FiguritaExistenteDO getFigurita(Integer id_figurita_existente) {
        FiguritaExistentePO figurita = FiguritaExistenteDAO
                .readFiguritaExistente(Integer.valueOf(id_figurita_existente));
        FiguritaExistenteDTO figuritaDto = PostgresORM.getInstance().toDTO(figurita);
        return FiguritaExistenteMapper.toDO(figuritaDto);
    }

    public static Collection<FiguritaExistenteDO> readAll() {
        Collection<FiguritaExistenteDO> figuritaExistenteConverted = new ArrayList<>();
        Collection<FiguritaExistentePO> figuritasExistentes = FiguritaExistenteDAO.readAll();
        for (FiguritaExistentePO figuritaExistentePO : figuritasExistentes) {
            FiguritaExistenteDO figuritaExistenteDO = FiguritaExistenteMapper
                    .toDO(PostgresORM.getInstance().toDTO(figuritaExistentePO));
            figuritaExistenteConverted.add(figuritaExistenteDO);
        }
        return figuritaExistenteConverted;
    }
}
