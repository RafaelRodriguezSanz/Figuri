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
    Integer ci;
    String nombre, apellido;
    Integer telefono;
}