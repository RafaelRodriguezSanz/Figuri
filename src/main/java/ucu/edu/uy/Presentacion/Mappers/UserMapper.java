package ucu.edu.uy.Presentacion.Mappers;

import ucu.edu.uy.Presentacion.DO.UserDO;
import ucu.edu.uy.Servicio.DTO.UserDTO;

public class UserMapper {
    public static UserDO toDO(UserDTO user) {
        return new UserDO(user.getNombre().getNombre(),
                user.getNombre().getAppelido(),
                user.getDireccion().toString(),
                user.getTelefono().toInteger(),
                user.getCi().toInteger());
    }
}
