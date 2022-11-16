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
    String id_publicación;
    String id_publicación1;
    String id_publicación2;
    String id_publicación3;
    Date fecha;
}