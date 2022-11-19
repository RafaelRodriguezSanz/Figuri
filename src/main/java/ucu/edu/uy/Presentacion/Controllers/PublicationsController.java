package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import jade.util.leap.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ucu.edu.uy.Presentacion.DO.PublicacionDO;
import ucu.edu.uy.Servicio.Servicios.PublicacionService;

public class PublicationsController {

    private static ObservableList<String> publicaciones;

    @FXML
    private Button atrasBtn;

    @FXML
    private Button eliminarBtn;

    @FXML
    private Button nuevaBtn;

    @FXML
    private Scene scene;

    @FXML
    private ListView<String> publications;

    @FXML
    void createPublication(ActionEvent event) {

    }

    @FXML
    void deletePublication(ActionEvent event) {
        // ((PublicacionDO) event.getTarget()).getId_publicacion();
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openPublication(MouseEvent event) throws IOException {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                Stage stage = (Stage) scene.getWindow();
                Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publication.fxml"));

                stage.setScene(scene);
                stage.show();
                // Publication.init(((PublicacionDO) event.getTarget()).getId_publicacion());
            }
        }
    }

    public void init() throws NoSuchAlgorithmException {
        publicaciones = FXCollections.observableArrayList();
        PublicacionService.getInstance().readAll().forEach(publication -> {
            publicaciones.add(publication.toString());
        });
        this.publications.setItems(publicaciones);
        for (String publicacionDO : publicaciones) {
            System.out.println(publicacionDO);
        }
    }

}
