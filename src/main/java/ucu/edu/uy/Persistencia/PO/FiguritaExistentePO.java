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
public class FiguritaExistente {
    int numero;
    char[] tipo, descripcion, pais;
}
