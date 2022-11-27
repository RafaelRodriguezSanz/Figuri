package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import jade.util.leap.LinkedList;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Presentacion.DO.PublicacionDO;
import ucu.edu.uy.Servicio.Servicios.FiguritaDeUsuarioService;
import ucu.edu.uy.Servicio.Servicios.FiguritaExistenteService;
import ucu.edu.uy.Servicio.Servicios.PublicacionService;

public class ExchangeController implements Initializable {

    @FXML
    private Button atrasBtn;

    @FXML
    private Button eliminarBtn;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem deleteBtn;

    @FXML
    private Button nuevaBtn;

    @FXML
    private Scene scene;

    @FXML
    private ListView<String> publications;

    @FXML
    void createPublication(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/NewPublication.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Nueva Publicacion");
    }

    @FXML
    void deletePublication(ActionEvent event) throws NoSuchAlgorithmException {
        String itemId = publications.getSelectionModel().getSelectedItem().split(" - ")[1].trim();
        int index = publications.getSelectionModel().getSelectedIndex();
        System.out.println('\"' + itemId + '\"');
        if (PublicacionService.getInstance().delete(itemId)) {
            publications.getItems().remove(index);
            System.out.println(index);
        }
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Home.fxml"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Home");
    }

    @FXML
    void openPublication(MouseEvent event) throws IOException {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                String itemId = publications.getSelectionModel().getSelectedItem().split(" - ")[1].trim();
                Session.getInstance().setPublicationID(itemId);
                Stage stage = (Stage) scene.getWindow();
                Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publication.fxml"));
                stage.setScene(scene);
                stage.show();
                stage.setTitle("Tus Publicaciones");
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            Collection<PublicacionDO> values = PublicacionService.getInstance().readAll();
            Collection<String> valuesFormated = new ArrayList<>();
            values.forEach(value -> {
                valuesFormated
                        .add(FiguritaDeUsuarioService.getInstance().readFigurita(value.getId_figurita_usuario())
                                .getDescripcion() + " - " + value.getId_publicacion());
            });
            System.out.println(valuesFormated.toString());
            publications.getItems().addAll(valuesFormated);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(publications.getItems().toString());
        publications.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
