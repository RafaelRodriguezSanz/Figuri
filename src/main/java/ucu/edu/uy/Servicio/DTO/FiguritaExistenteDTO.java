package ucu.edu.uy.Servicio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class FiguritaExistenteDTO {
    Integer id_figurita_existente;
    String tipo;
    String descripcion;
    String pais;
}
