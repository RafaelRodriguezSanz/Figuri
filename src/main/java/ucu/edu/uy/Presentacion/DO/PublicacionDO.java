package ucu.edu.uy.Presentacion.DO;

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
public class PublicacionDO {
    String id_publicacion;
    String id_figurita_usuario;
    String id_estado_publicacion;
    Integer id_figurita_existente_1;
    Integer id_figurita_existente_2;
    Integer id_figurita_existente_3;
    Date fecha;
}