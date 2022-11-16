package ucu.edu.uy.Presentacion.Mappers;

import ucu.edu.uy.Presentacion.DO.OfertaDO;
import ucu.edu.uy.Presentacion.DO.PublicacionDO;
import ucu.edu.uy.Servicio.DTO.OfertaDTO;
import ucu.edu.uy.Servicio.DTO.PublicacionDTO;

public class OfertaMapper {
    public static OfertaDO toDO(OfertaDTO oferta) {
        return new OfertaDO(
                oferta.getId_oferta(),
                oferta.getId_publicacion(),
                oferta.getId_publicacion1(),
                oferta.getId_publicacion2(),
                oferta.getId_publicacion3(),
                oferta.getFecha());
    }
}
