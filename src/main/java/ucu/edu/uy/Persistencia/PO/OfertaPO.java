package ucu.edu.uy.Persistencia.PO;

import java.sql.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfertaPO {
    char[] id_oferta;
    char[] id_publicacion;
    char[] id_publicacion1;
    char[] id_publicacion2;
    char[] id_publicacion3;
    Date fecha;

    public OfertaPO(String id_publicacion, String... ofertas) {
        this.id_oferta = UUID.randomUUID().toString().toCharArray();
        this.id_publicacion = id_publicacion.toCharArray();
        this.id_publicacion1 = ofertas[0].toCharArray();
        this.id_publicacion2 = ofertas[1].toCharArray();
        this.id_publicacion3 = ofertas[2].toCharArray();
        this.fecha = new Date(System.currentTimeMillis());
    }
}
