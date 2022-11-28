package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Presentacion.DO.OfertaDO;
import ucu.edu.uy.Servicio.Servicios.OfertaService;

public class OfertsController implements Initializable {

    @FXML
    private Button atrasBtn;

    @FXML
    private ListView<String> ofertasList;

    @FXML
    private Scene scene;

    @FXML
    void goToPublicaciones(ActionEvent event) throws IOException {
        Stage stage = (Stage) atrasBtn.getScene().getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tus Publicaciones");
        Session.getInstance().setPublicationID(null);
    }

    @FXML
    void openOfert(MouseEvent event) throws IOException {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                String itemId = ofertasList.getSelectionModel().getSelectedItem().split(" - ")[1].trim();
                Session.getInstance().setOfferId(itemId);
                Stage stage = (Stage) scene.getWindow();
                Scene scene = FXMLLoader.load(getClass().getResource("/Views/Ofert.fxml"));
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Oferta");
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Collection<OfertaDO> values = OfertaService.getInstance().readAll(Session.getInstance().getPublicationID());
        Collection<String> valuesFormated = new ArrayList<>();
        if (values != null) {
            values.forEach(value -> {
                valuesFormated
                        .add(value.getId_oferta() + " - " + value.getId_publicacion1() + '+'
                                + value.getId_publicacion2() + '+' + value.getId_publicacion3() + '+');
            });
        }
        ofertasList.getItems().addAll(valuesFormated);
    }

}
