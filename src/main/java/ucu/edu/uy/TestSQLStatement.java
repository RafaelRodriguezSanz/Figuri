package ucu.edu.uy;

import java.io.IOException;
import java.sql.SQLException;

import ucu.edu.uy.Persistencia.DAO.UserDAO;
import ucu.edu.uy.Persistencia.PO.UserPO;
import ucu.edu.uy.Persistencia.Utils.DB;

/**
 * TestSQLStatement
 */
public class TestSQLStatement {

    public static void main(String[] args) throws IOException, SQLException {
        UserPO user = new UserPO();
        user.setCi(50149323);
        user.setContrasenia(new char[] { 'm', 'y', ' ', 'p', 'a', 's', 's' });
        user.setDireccion(new char[] { '1', '2', '3', '4', '5' });
        user.setFoto(new char[] { 'p', 'i', 'c', });
        user.setNombre(new char[] { 'r', 'a', 'f', 'a', });
        user.setTelefono(91352825);

        UserDAO.createUser(user);
    }
}