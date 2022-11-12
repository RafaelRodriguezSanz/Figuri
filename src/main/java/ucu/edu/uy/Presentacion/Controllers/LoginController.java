package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ucu.edu.uy.Servicio.Servicios.UserService;
import static ucu.edu.uy.Servicio.POJO.Utils.Validator.cleanNumber;

@Getter
@Setter
public class LoginController {
    @FXML
    private AnchorPane ap;

    @FXML
    private Text errorMessage;

    @FXML
    private Text forgottenPasswordLink;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField userField;

    @FXML
    void forgottenPasswordLinkClicked(MouseEvent event) {

    }

    @FXML
    void loginBtnClicked(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        boolean logged = UserService.getInstance().login(this.getUserField().getText(),
                this.getPasswordField().getText());
        System.out.println("login " + logged);
        if (logged) {
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/Views/BasicFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            this.getErrorMessage().setVisible(true);
        }
    }

    @FXML
    void registerBtnClicked(ActionEvent event) {

    }

}
