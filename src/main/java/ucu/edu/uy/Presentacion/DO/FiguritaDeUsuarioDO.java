package ucu.edu.uy.Presentacion.DO;

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
public class FiguritaDeUsuarioDO {
    UUID id_figurita_usuario;
    Integer id_figurita_existente;
    String tipo, descripcion, pais, estado;
}