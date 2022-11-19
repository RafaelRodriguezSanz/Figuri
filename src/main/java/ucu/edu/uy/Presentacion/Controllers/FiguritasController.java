package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class FiguritasController {
    @FXML
    private Scene scene;

    @FXML
    private Button agregarBtn;

    @FXML
    private Button atrasBtn;

    @FXML
    private Button borrarBtn;

    @FXML
    private MenuButton estadoBtn;

    @FXML
    private ListView<?> figuritasDeUsuario;

    @FXML
    private ListView<?> figuritasDisponibles;

    @FXML
    void addFigurita(ActionEvent event) {

    }

    @FXML
    void deleteFigurita(ActionEvent event) {

    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setFiguritas() {

    }

}
