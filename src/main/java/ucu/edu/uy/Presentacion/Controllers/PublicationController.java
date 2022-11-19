package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class PublicationController {

    @FXML
    private Label FiguritasDeseadas;

    @FXML
    private Button atrasBtn;

    @FXML
    private Label figuritaPublicada;

    @FXML
    private Button ofertarBtn;

    @FXML
    private ListView<?> ofertasList;

    @FXML
    private Scene scene;

    @FXML
    void goToPublications(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publicaciones.fxml"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void newOfert(ActionEvent event) {

    }

}
