package ucu.edu.uy.Servicio.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;
import ucu.edu.uy.Servicio.POJO.Contrasenia;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OfertaDTO {
    String id_oferta;
    String id_publicación;
    String id_publicación1;
    String id_publicación2;
    String id_publicación3;
    Date fecha;
}
