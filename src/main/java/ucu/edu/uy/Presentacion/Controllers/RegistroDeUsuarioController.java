package ucu.edu.uy.Presentacion.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

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
    private TextField telefono;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void IngresarAAplicacion(ActionEvent event) {

    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FiguriLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
