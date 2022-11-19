package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OfertsController {

    @FXML
    private Button atrasBtn;

    @FXML
    private ListView<?> ofertasList;

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
    void openOfert(MouseEvent event) {

    }

}
