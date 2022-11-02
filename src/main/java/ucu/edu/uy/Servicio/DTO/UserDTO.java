package ucu.edu.uy.Servicio.DTO;

import org.identityconnectors.common.security.GuardedString;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ucu.edu.uy.Servicio.POJO.CI;
import ucu.edu.uy.Servicio.POJO.Direccion;
import ucu.edu.uy.Servicio.POJO.Foto;
import ucu.edu.uy.Servicio.POJO.Nombre;
import ucu.edu.uy.Servicio.POJO.Telefono;
import ucu.edu.uy.Servicio.POJO.Contrasenia;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    Nombre nombre;
    Contrasenia contrasenia;
    Direccion direccion;
    Foto foto;
    Telefono telefono;
    CI ci;
}
