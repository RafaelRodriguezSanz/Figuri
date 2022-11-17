package ucu.edu.uy.Presentacion.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import ucu.edu.uy.Servicio.Servicios.UserService;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.text.Text;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


@Getter
@Setter
public class RegistroDeUsuarioController {
    @FXML
    private Button Atras;

    @FXML
    private Button Confirmar;

    @FXML
    private PasswordField Contraseña;

    @FXML
    private TextField apellido;

    @FXML
    private TextField cedula;

    @FXML
    private TextField nombre;
    @FXML
    private Text errorContraseña;

    @FXML
    private TextField telefono;
    private Stage stage;
    private Scene scene;

    @FXML
    void switchToHome(ActionEvent event) throws IOException, NoSuchAlgorithmException, SQLException {
        boolean register = UserService.getInstance().register(cedula.getText(), nombre.getText(), apellido.getText(), telefono.getText(), 
        Contraseña.getText());
        System.out.println("register " + register);
        if (register) {
            Stage stage = (Stage) Confirmar.getScene().getWindow();
            Scene scene = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
            stage.setScene(scene);
            stage.show();
        } else {
            this.getErrorContraseña().setVisible(true);
        }
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        scene = FXMLLoader.load(getClass().getResource("/Views/FiguriLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
