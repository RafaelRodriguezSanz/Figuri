package ucu.edu.uy.Presentacion.Mappers;

import ucu.edu.uy.Persistencia.DAO.FiguritaExistenteDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Presentacion.DO.FiguritaDeUsuarioDO;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.FiguritaExistenteDTO;

public class FiguritaDeUsuarioMapper {
    public static FiguritaDeUsuarioDO toDO(FiguritaDeUsuarioDTO figuritaDeUsuario) {
        FiguritaExistenteDTO figurita = PostgresORM.getInstance()
                .toDTO(FiguritaExistenteDAO.readFiguritaExistente(figuritaDeUsuario.getFigurita()));
        return new FiguritaDeUsuarioDO(
                figuritaDeUsuario.getId_figurita_De_Usuario(),
                figurita.getId_figurita_existente(),
                figurita.getTipo(),
                figurita.getDescripcion(),
                figurita.getPais(),
                figuritaDeUsuario.getEstado().name());
    }
}
