package ucu.edu.uy.Presentacion.Mappers;

import ucu.edu.uy.Presentacion.DO.FiguritaExistenteDO;
import ucu.edu.uy.Presentacion.DO.UserDO;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;
import ucu.edu.uy.Servicio.DTO.UserDTO;

public class FiguritaExistenteMapper {
    public static FiguritaExistenteDO toDO(FiguritaExistenteDTO figurita) {
        return new FiguritaExistenteDO(figurita.getId_figurita_existente(),
                figurita.getTipo(),
                figurita.getDescripcion(),
                figurita.getPais());
    }
}
