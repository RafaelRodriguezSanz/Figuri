package ucu.edu.uy.Presentacion.DO;

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
public class FiguritaDeUsuarioDO {
    Integer id_figurita_existente;
    String tipo, descripcion, pais, estado;
}