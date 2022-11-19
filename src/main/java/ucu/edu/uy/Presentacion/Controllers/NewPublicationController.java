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
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class NewPublicationController {

    @FXML
    private Button atrasBtn;

    @FXML
    private ListView<?> figuritasDeUsuario;

    @FXML
    private ListView<?> figuritasExistentes;

    @FXML
    private Button publicarBtn;

    @FXML
    private Scene scene;

    @FXML
    void goToPublicaciones(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publicaciones.fxml"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void publicar(ActionEvent event) {

    }

}
