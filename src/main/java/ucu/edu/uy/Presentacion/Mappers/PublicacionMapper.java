package ucu.edu.uy.Presentacion.Mappers;

import ucu.edu.uy.Presentacion.DO.PublicacionDO;
import ucu.edu.uy.Servicio.DTO.PublicacionDTO;

public class PublicacionMapper {
    public static PublicacionDO toDO(PublicacionDTO publicacion) {
        return new PublicacionDO(publicacion.getId_publicacion(),
                publicacion.getId_figurita_usuario(),
                publicacion.getId_estado_publicacion(),
                publicacion.getId_figurita_existente_1(),
                publicacion.getId_figurita_existente_2(),
                publicacion.getId_figurita_existente_3(),
                publicacion.getFecha());
    }
}
