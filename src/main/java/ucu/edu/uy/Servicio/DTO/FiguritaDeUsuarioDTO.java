package ucu.edu.uy.Servicio.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class FiguritaDeUsuarioDTO {
    UUID id;
    Integer figurita;
    String estado;
}
