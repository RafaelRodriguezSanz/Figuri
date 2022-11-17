package ucu.edu.uy.Servicio.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ucu.edu.uy.Servicio.POJO.Estado;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class FiguritaDeUsuarioDTO {
    UUID id_figurita_De_Usuario;
    Integer figurita;
    Estado estado;
    Integer id_usuario;
}
