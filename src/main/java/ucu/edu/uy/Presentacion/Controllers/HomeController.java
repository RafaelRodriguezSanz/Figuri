package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button figuritasBtn;

    @FXML
    private Button intercambiarBtn;

    @FXML
    private Button publicacionesBtn;

    @FXML
    private Scene scene;

    @FXML
    private Label title;

    @FXML
    void goToFiguritas(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Figuritas.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tus Figuritas");
    }

    @FXML
    void goToPublicaciones(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("TUs publicaciones");
    }

    @FXML
    void goToIntercambiar(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Exchange.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Intercambio");
    }

}