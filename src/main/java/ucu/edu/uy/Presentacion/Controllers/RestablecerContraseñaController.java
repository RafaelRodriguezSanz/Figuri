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

public class RestablecerContraseñaController {

    @FXML
    private Button Atras;

    @FXML
    private Button Confirmar;

    @FXML
    private TextField cedula;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField nombre;
    private Stage stage;
    private Scene scene;

    @FXML
    void switchToHome(ActionEvent event) {

    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        scene = FXMLLoader.load(getClass().getResource("/Views/FiguriLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
