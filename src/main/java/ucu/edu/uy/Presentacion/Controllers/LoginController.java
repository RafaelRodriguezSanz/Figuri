package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import ucu.edu.uy.Jade.Utils.Session;
import java.awt.Desktop;

@Getter
@Setter
public class LoginController {

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
    void forgottenPasswordLinkClicked(MouseEvent event) throws IOException, URISyntaxException {
        Desktop desktop = Desktop.getDesktop();
        URI mailto = new URI(
                "mailto:support@figuri.com?subject=Pass%20Recovery&body=Hi%2C%20I%20need%20a%20recovery%20Code%21");
        desktop.browse(mailto);
        desktop.mail(mailto);
    }

    @FXML
    void loginBtnClicked(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        System.out.println("Setting Session...");
        boolean logged = Session.getInstance().login(this.getUserField().getText(), this.getPasswordField().getText());
        System.out.println("login> " + logged);
        if (logged) {
            System.out.println("Changing UI...");
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            Scene scene = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Home");

            System.out.println("Starting Agent...");
            Session.getInstance().startSession();
            System.out.println("Agent Started...");
        } else {
            this.getErrorMessage().setVisible(true);
        }
    }

    @FXML
    void registerBtnClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Register.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("New User Registration");
    }

}