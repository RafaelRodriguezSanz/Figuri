package ucu.edu.uy.Servicio.Servicios;

import ucu.edu.uy.Persistencia.DAO.FiguritaExistenteDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
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
}
