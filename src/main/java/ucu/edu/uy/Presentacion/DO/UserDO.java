package ucu.edu.uy.Presentacion.DO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDO {
    String nombre, apellido, contrasenia, direccion, foto;
    Integer telefono, ci;
}