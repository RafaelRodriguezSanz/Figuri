package ucu.edu.uy.Servicio.POJO;

import org.identityconnectors.common.security.GuardedString;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Contrasenia {
    GuardedString pass;

    @Override
    public final String toString() {
        return "**********";
    }
}
