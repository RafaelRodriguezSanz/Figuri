package ucu.edu.uy.Servicio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;
import ucu.edu.uy.Servicio.POJO.Contrasenia;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO {
    CI ci;
    Nombre nombre;
    Telefono telefono;
    Contrasenia contrasenia;
}
