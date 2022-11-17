package ucu.edu.uy.Persistencia.PO;

import java.sql.Date;

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
public class PublicacionPO {
    char[] id_publicacion;
    char[] id_figurita_usuario;
    char[] id_estado_publicacion;
    int id_figurita_existente_1;
    int id_figurita_existente_3;
    int id_figurita_existente_2;
    Date fecha;
}
