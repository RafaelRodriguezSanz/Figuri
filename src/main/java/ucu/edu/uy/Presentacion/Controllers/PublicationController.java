package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Presentacion.DO.OfertaDO;
import ucu.edu.uy.Servicio.Servicios.FiguritaExistenteService;
import ucu.edu.uy.Servicio.Servicios.OfertaService;
import ucu.edu.uy.Servicio.Servicios.PublicacionService;

public class PublicationController implements Initializable {

    @FXML
    private Label FiguritasDeseadas;

    @FXML
    private Button atrasBtn;

    @FXML
    private Label figuritaPublicada;

    @FXML
    private Button ofertarBtn;

    @FXML
    private ListView<String> ofertasList;

    @FXML
    private Scene scene;

    @FXML
    void goToPublications(ActionEvent event) throws IOException {
        Session.getInstance().setPublicationID(null);
        Stage stage = (Stage) FiguritasDeseadas.getScene().getWindow();
        Scene newScene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(newScene);
        stage.show();
        stage.setTitle("Tus Publicaciones");
        Session.getInstance().setPublicationID(null);
    }

    @FXML
    void newOfert(ActionEvent event) throws IOException {
        Stage stage = (Stage) FiguritasDeseadas.getScene().getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Ofert.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Oferta");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String idPublicacion = Session.getInstance().getPublicationID();
        try {
            this.figuritaPublicada.setText(PublicacionService.getInstance().read(idPublicacion).toString());
            String f1 = (FiguritaExistenteService.getInstance()
                    .getFigurita(PublicacionService.getInstance().read(idPublicacion).getId_figurita_existente_1()))
                    .toString();
            String f2 = (FiguritaExistenteService.getInstance()
                    .getFigurita(PublicacionService.getInstance().read(idPublicacion).getId_figurita_existente_2()))
                    .toString();
            String f3 = (FiguritaExistenteService.getInstance()
                    .getFigurita(PublicacionService.getInstance().read(idPublicacion).getId_figurita_existente_3()))
                    .toString();
            FiguritasDeseadas.setText(f1 + "\n" + f2 + "\n" + f3 + "\n");
            Collection<OfertaDO> ofertas = OfertaService.getInstance()
                    .readAll(Session.getInstance().getPublicationID());
            Collection<String> oferasConvertido = new ArrayList<>();
            if (ofertas != null) {
                ofertas.forEach(oferta -> {
                    oferasConvertido.add(oferta.getId_oferta() + " - " + oferta.getId_publicacion1() + '+'
                            + oferta.getId_publicacion2() + '+' + oferta.getId_publicacion3() + '+');
                });
            }
            ofertasList.getItems().addAll(oferasConvertido);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
