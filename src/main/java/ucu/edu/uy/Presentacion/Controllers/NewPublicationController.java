package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Presentacion.DO.FiguritaDeUsuarioDO;
import ucu.edu.uy.Presentacion.DO.FiguritaExistenteDO;
import ucu.edu.uy.Servicio.Servicios.FiguritaDeUsuarioService;
import ucu.edu.uy.Servicio.Servicios.FiguritaExistenteService;
import ucu.edu.uy.Servicio.Servicios.PublicacionService;

import java.awt.Desktop;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class NewPublicationController implements Initializable {

    @FXML
    private Button atrasBtn;

    @FXML
    private ListView<String> figuritasDeUsuario;

    @FXML
    private ListView<String> figuritasExistentes;

    @FXML
    private Button publicarBtn;

    @FXML
    private Scene scene;

    @FXML
    void goToPublicaciones(ActionEvent event) throws IOException {
        Stage stage = (Stage) figuritasDeUsuario.getScene().getWindow();
        Scene newScene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(newScene);
        stage.show();
        stage.setTitle("Tus Publicaciones");
    }

    @FXML
    void publicar(ActionEvent event) throws IOException {
        String itemId = figuritasDeUsuario.getSelectionModel().getSelectedItem().split(" - ")[0].trim();
        String itemId1 = figuritasExistentes.getSelectionModel().getSelectedItems().get(0).split(" - ")[0].trim();
        String itemId2 = null;
        String itemId3 = null;
        try {
            itemId2 = figuritasExistentes.getSelectionModel().getSelectedItems().get(1).split(" - ")[0].trim();
            itemId3 = figuritasExistentes.getSelectionModel().getSelectedItems().get(2).split(" - ")[0].trim();
        } catch (Exception e) {
            // Errror
        }
        String id = PublicacionService.getInstance().post(itemId, itemId1, itemId2, itemId3);
        if (Objects.nonNull(id)) {
            Stage stage = (Stage) figuritasDeUsuario.getScene().getWindow();
            Scene newScene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
            stage.setScene(newScene);
            stage.show();
            stage.setTitle("Tus Publicaciones");
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        figuritasExistentes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Collection<FiguritaDeUsuarioDO> figuritasUsuario = FiguritaDeUsuarioService.getInstance().readAll();
        Collection<String> figuritasUsuarioConverted = new ArrayList<>();
        figuritasUsuario.forEach(figurita -> {
            figuritasUsuarioConverted
                    .add(figurita.getId_figurita_usuario().toString() + " - " + figurita.getDescripcion());
        });
        figuritasDeUsuario.getItems().addAll(figuritasUsuarioConverted);
        Collection<FiguritaExistenteDO> figuritas = FiguritaExistenteService.getInstance().readAll();
        Collection<String> figuritasConverted = new ArrayList<>();
        figuritas.forEach(figurita -> {
            figuritasConverted.add(figurita.getId_figurita_existente() + " - " + figurita.getDescripcion());
        });
        figuritasExistentes.getItems().addAll(figuritasConverted);

    }

}
