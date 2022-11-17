package ucu.edu.uy.Presentacion.Mappers;

import ucu.edu.uy.Presentacion.DO.FiguritaDeUsuarioDO;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;

public class FiguritaDeUsuarioMapper {
    public static FiguritaDeUsuarioDO toDO(FiguritaDeUsuarioDTO figuritaDeUsuario,
            FiguritaExistenteDTO figuritaExistente) {
        return new FiguritaDeUsuarioDO(figuritaExistente.getId_figurita_existente(),
                figuritaExistente.getTipo(),
                figuritaExistente.getDescripcion(),
                figuritaExistente.getPais(),
                figuritaDeUsuario.getEstado().name());
    }
}
