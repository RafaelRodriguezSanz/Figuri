package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Presentacion.DO.FiguritaDeUsuarioDO;
import ucu.edu.uy.Presentacion.DO.OfertaDO;
import ucu.edu.uy.Presentacion.DO.PublicacionDO;
import ucu.edu.uy.Presentacion.Mappers.OfertaMapper;
import ucu.edu.uy.Presentacion.Mappers.PublicacionMapper;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.PublicacionDTO;
import ucu.edu.uy.Servicio.Servicios.FiguritaDeUsuarioService;
import ucu.edu.uy.Servicio.Servicios.OfertaService;
import ucu.edu.uy.Servicio.Servicios.PublicacionService;
import ucu.edu.uy.Servicio.Servicios.UserService;

public class OfertController implements Initializable {

    @FXML
    private Button acceptBtn;

    @FXML
    private Button atrasBtn;

    @FXML
    private Label figuritaData;

    @FXML
    private ListView<String> figuritasOfertadas;

    @FXML
    private Button rejectbtn;

    @FXML
    private Scene scene;

    @FXML
    void accept(ActionEvent event) throws IOException {
        OfertaService.getInstance().deleteOferta(Session.getInstance().getOfferId());
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tus Publicaciones");
    }

    @FXML
    void goToPublicaciones(ActionEvent event) throws IOException, SQLException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tus Publicaciones");
        OfertaService.getInstance().changeOferta(Session.getInstance().getPublicationID(),
                figuritasOfertadas.getSelectionModel().getSelectedItems()
                        .toArray(new String[figuritasOfertadas.getSelectionModel().getSelectedItems().size()]));
    }

    @FXML
    void reject(ActionEvent event) throws IOException {
        OfertaService.getInstance().deleteOferta(Session.getInstance().getOfferId());
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tus Publicaciones");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        figuritasOfertadas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        PublicacionDTO publication = new PublicacionDTO();
        try {
            publication = PostgresORM.getInstance()
                    .toDTO(PublicacionService.getInstance().read(Session.getInstance().getPublicationID()));
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        FiguritaDeUsuarioDTO figurita = FiguritaDeUsuarioService.getInstance()
                .readFiguritaDTO(publication.getId_figurita_usuario());
        Collection<PublicacionDO> publicaciones = new ArrayList();
        try {
            publicaciones = PublicacionService.getInstance()
                    .readAll(figurita.getId_usuario().toString());
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Collection<String> valuesFormated = new ArrayList<>();
        if (publicaciones != null) {
            publicaciones.forEach(value -> {
                valuesFormated
                        .add(value.getId_publicacion() + " - " + value.getId_figurita_usuario());
            });
        }
        figuritasOfertadas.getItems().addAll(valuesFormated);
        OfertaDO oferta = OfertaMapper.toDO(PostgresORM.getInstance()
                .toDTO(OfertaService.getInstance().readOferta(Session.getInstance().getOfferId())));

        PublicacionDO publication1 = new PublicacionDO();
        PublicacionDO publication2 = new PublicacionDO();
        PublicacionDO publication3 = new PublicacionDO();
        try {
            publication1 = PublicacionMapper.toDO(
                    PostgresORM.getInstance()
                            .toDTO(PublicacionService.getInstance().read(oferta.getId_publicacion1())));
            publication2 = PublicacionMapper.toDO(
                    PostgresORM.getInstance()
                            .toDTO(PublicacionService.getInstance().read(oferta.getId_publicacion2())));
            publication3 = PublicacionMapper.toDO(
                    PostgresORM.getInstance()
                            .toDTO(PublicacionService.getInstance().read(oferta.getId_publicacion3())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        figuritasOfertadas.getSelectionModel()
                .select(publication1.getId_publicacion() + " - " + publication1.getId_figurita_usuario());
        figuritasOfertadas.getSelectionModel()
                .select(publication1.getId_publicacion() + " - " + publication2.getId_figurita_usuario());
        figuritasOfertadas.getSelectionModel()
                .select(publication1.getId_publicacion() + " - " + publication3.getId_figurita_usuario());
    }

}
