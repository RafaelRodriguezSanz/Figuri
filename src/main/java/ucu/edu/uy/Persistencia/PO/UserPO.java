package ucu.edu.uy.Persistencia.PO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPO {
    char[] nombre, apellido, contrasenia, direccion, foto;
    int telefono, ci;
}
