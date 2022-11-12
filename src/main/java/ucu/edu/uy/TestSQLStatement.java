package ucu.edu.uy;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import ucu.edu.uy.Servicio.Servicios.UserService;

/**
 * TestSQLStatement
 */
public class TestSQLStatement {

        public static void main(String[] args) throws IOException, SQLException, NoSuchAlgorithmException {

                System.out
                                .println(UserService.getInstance().register("5014932-3", "Rafael",
                                                "Rodriguez", "091352862",
                                                "MyPass123"));
                System.out
                                .println(UserService.getInstance().login("5014932-3", "MyPass123"));

                System.out
                                .println(UserService.getInstance().changePassword("5014932-3", "MyPass1234"));

                System.out
                                .println(UserService.getInstance().deleteUser("5014932-3", "MyPass1234"));
        }
}