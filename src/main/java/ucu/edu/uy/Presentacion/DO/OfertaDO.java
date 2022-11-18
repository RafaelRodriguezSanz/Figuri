package ucu.edu.uy.Presentacion.DO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfertaDO {
    String id_oferta;
    String id_publicacion;
    String id_publicacion1;
    String id_publicacion2;
    String id_publicacion3;
    Date fecha;
}