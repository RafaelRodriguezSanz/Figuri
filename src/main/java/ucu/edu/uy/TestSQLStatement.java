package ucu.edu.uy;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import ucu.edu.uy.Servicio.Servicios.FiguritaDeUsuarioService;
import ucu.edu.uy.Servicio.Servicios.FiguritaExistenteService;
import ucu.edu.uy.Servicio.Servicios.PublicacionService;
import ucu.edu.uy.Servicio.Servicios.UserService;

/**
 * TestSQLStatement
 */
public class TestSQLStatement {

        public static void main(String[] args) throws IOException, SQLException, NoSuchAlgorithmException {
                System.out
                                .println(UserService.getInstance().deleteUser("5014932-3", "MyPass1234"));

                System.out
                                .println(UserService.getInstance().register("5014932-3", "Rafael",
                                                "Rodriguez", "091352862",
                                                "MyPass123"));
                System.out
                                .println(UserService.getInstance().login("5014932-3", "MyPass123"));

                System.out
                                .println(UserService.getInstance().changePassword("5014932-3",
                                                "MyPass1234"));

                System.out.println(FiguritaExistenteService.getInstance().getFigurita("78"));
                String id = FiguritaDeUsuarioService.getInstance().createFigurita("640", "1",
                                "5014932-3");
                System.out.println(FiguritaDeUsuarioService.getInstance().readFigurita(id));
                // System.out.println(FiguritaDeUsuarioService.getInstance().deleteFigurita(id));

                String publicationid = PublicacionService.getInstance().post(id, "1", "2", "3");
                System.out.println(publicationid);
                System.out.println(PublicacionService.getInstance().read(publicationid));
                System.out.println(PublicacionService.getInstance().caducar(publicationid));
                System.out.println(PublicacionService.getInstance().read(publicationid));
                System.out.println(PublicacionService.getInstance().finalizar(publicationid));
                System.out.println(PublicacionService.getInstance().read(publicationid));
                System.out.println(PublicacionService.getInstance().delete(publicationid));
                System.out.println(PublicacionService.getInstance().read(publicationid) == null);
        }
}