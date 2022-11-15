package ucu.edu.uy.Presentacion.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.security.NoSuchAlgorithmException;
import ucu.edu.uy.Servicio.Servicios.UserService;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.text.Text;

@Getter
@Setter
public class FiguriLoginController {

    @FXML
    private Button IngresarUsuario;

    @FXML
    private Button Registrarse;

    @FXML
    private Hyperlink cambiarContraseña;

    @FXML
    private PasswordField contraseña;

    @FXML
    private Text errorLogin;

    @FXML
    private TextField nombreUsuario;
    private Stage stage;
    private Scene scene;

    @FXML
    void switchToHome(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        boolean logged = UserService.getInstance().login(nombreUsuario.getText(),
        contraseña.getText());
        System.out.println("login " + logged);
        if (logged) {
            Stage stage = (Stage) IngresarUsuario.getScene().getWindow();
            Scene scene = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
            stage.setScene(scene);
            stage.show();
        } else {
            this.getErrorLogin().setVisible(true);
        }
    }

    @FXML
    void switchToCambiarContraseña(ActionEvent event) throws IOException {
        scene = FXMLLoader.load(getClass().getResource("/Views/RestablecerContraseña.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void switchToRegistrar(ActionEvent event) throws IOException {
        scene = FXMLLoader.load(getClass().getResource("/Views/RegistroDeUsuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
