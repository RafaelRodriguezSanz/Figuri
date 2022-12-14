package ucu.edu.uy.Persistencia.PO;

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
public class FiguritaDeUsuarioPO {
    char[] id_figurita_usuario;
    int id_figurita_existente;
    int id_estado_figurita;
    int id_usuario;
}
