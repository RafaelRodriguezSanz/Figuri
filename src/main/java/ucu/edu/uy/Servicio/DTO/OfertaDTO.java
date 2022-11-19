package ucu.edu.uy.Servicio.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OfertaDTO {
    String id_oferta;
    String id_publicacion;
    String id_publicacion1;
    String id_publicacion2;
    String id_publicacion3;
    Date fecha;
}
