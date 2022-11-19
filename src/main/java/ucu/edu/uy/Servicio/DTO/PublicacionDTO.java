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
public class PublicacionDTO {
    String id_publicacion;
    String id_figurita_usuario;
    String id_estado_publicacion;
    Integer id_figurita_existente_1;
    Integer id_figurita_existente_2;
    Integer id_figurita_existente_3;
    Date fecha;
}
