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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import ucu.edu.uy.Servicio.Servicios.UserService;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.text.Text;

@Getter
@Setter

public class RestablecerContraseniaController {

    @FXML
    private Button Atras;

    @FXML
    private Button Confirmar;

    @FXML
    private TextField cedula;

    @FXML
    private PasswordField contrasenia;

    @FXML
    private TextField nombre;
    private Stage stage;
    private Scene scene;

    @FXML
    private Text errorContrasenia;

    @FXML
    void switchToHome(ActionEvent event) throws IOException, NoSuchAlgorithmException, SQLException {
        boolean changePassword = UserService.getInstance().changePassword(cedula.getText(),
                contrasenia.getText());
        System.out.println("changePassword " + changePassword);
        if (changePassword) {
            Stage stage = (Stage) Confirmar.getScene().getWindow();
            Scene scene = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
            stage.setScene(scene);
            stage.show();
        } else {
            this.getErrorContrasenia().setVisible(true);
        }

    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        scene = FXMLLoader.load(getClass().getResource("/Views/FiguriLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
