package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.xml.validation.Validator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ucu.edu.uy.Servicio.Servicios.UserService;
import static ucu.edu.uy.Servicio.POJO.Utils.Validator.cleanNumber;

public class RegisterController {

    @FXML
    private Button Atras;

    @FXML
    private Button Confirmar;

    @FXML
    private PasswordField Contrasenia;

    @FXML
    private TextField apellido;

    @FXML
    private TextField cedula;

    @FXML
    private Text errorContrasenia;

    @FXML
    private TextField nombre;

    @FXML
    private TextField telefono;

    @FXML
    void switchToLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) nombre.getScene().getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void register(ActionEvent event) throws IOException {
        try {
            boolean result = UserService.getInstance().register(cleanNumber(cedula.getText()),
                    nombre.getText(),
                    apellido.getText(),
                    telefono.getText(),
                    Contrasenia.getText());
            if (!result) {
                showError();
            }
            switchToLogin(event);
        } catch (NoSuchAlgorithmException | SQLException e) {
            showError();
        }
    }

    private void showError() {
        this.errorContrasenia.setVisible(true);
    }

}
