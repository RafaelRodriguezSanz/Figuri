package ucu.edu.uy.Persistencia.PO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPO {
    int ci;
    char[] nombre;
    char[] apellido;
    int telefono;
    char[] contrasenia;
}
