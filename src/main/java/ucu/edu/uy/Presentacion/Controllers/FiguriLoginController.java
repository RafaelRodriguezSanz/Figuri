package ucu.edu.uy.Presentacion.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

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
    private TextField nombreUsuario;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void IngresarUsuario(ActionEvent event) {

    }

    @FXML
    void cambiarContraseña(ActionEvent event) {

    }


    @FXML
    void switchToRegistrar(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RegistroDeUsuario.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
