package ucu.edu.uy;

import java.io.IOException;
import java.sql.SQLException;

import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Persistencia.PO.UserPO;
import ucu.edu.uy.Persistencia.Utils.DB;

/**
 * TestSQLStatement
 */
public class TestSQLStatement {

    public static void main(String[] args) throws IOException, SQLException {
        UserPO user = new UserPO();
        user.setNombre("Rafael".toCharArray());
        user.setApellido("Rodriguez".toCharArray());
        user.setCi(50149323);
        user.setTelefono(91352825);
        user.setContrasenia("MyPass".toCharArray());
        user.setDireccion("Soriano".toCharArray());

        try {
            UserDAO.createUser(user);
        } catch (Exception e) {
            System.out.println("User Was not created successfully");
        }

        try {
            user.setNombre("OtroNombre".toCharArray());
            UserDAO.updateUser(user);
        } catch (Exception e) {
            System.out.println("User Was not updated successfully");
        }

        UserPO me = UserDAO.readUser(50149323);
        System.out.println(PostgresORM.getInstance().toDTO(me).toString());
        try {
        } catch (Exception e) {
            System.out.println("User Was not read successfully");
        }

        try {
            UserDAO.deleteUser(50149323);
        } catch (Exception e) {
            System.out.println("User Was not deleted successfully");
        }

    }
}