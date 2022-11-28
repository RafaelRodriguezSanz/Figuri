package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Presentacion.DO.FiguritaDeUsuarioDO;
import ucu.edu.uy.Presentacion.DO.FiguritaExistenteDO;
import ucu.edu.uy.Servicio.Servicios.FiguritaDeUsuarioService;
import ucu.edu.uy.Servicio.Servicios.FiguritaExistenteService;

public class FiguritasController implements Initializable {
    @FXML
    private ToggleGroup StateGroup;

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
    private ListView<String> figuritasDeUsuario;

    @FXML
    private ListView<String> figuritasDisponibles;

    @FXML
    void addFigurita(ActionEvent event) {
        String disponibleId = figuritasDisponibles.getSelectionModel().getSelectedItem().split(" - ")[0].trim();
        String estado = ((MenuItem) StateGroup.getSelectedToggle()).getText();
        System.out.println(estado);
        String figuritaId = FiguritaDeUsuarioService.getInstance().createFigurita(disponibleId, estado,
                Session.getInstance().getId());
        FiguritaDeUsuarioDO figurita = FiguritaDeUsuarioService.readFigurita(figuritaId);
        figuritasDeUsuario.getItems().add(figuritaId + " - " + figurita.getDescripcion());
    }

    @FXML
    void deleteFigurita(ActionEvent event) throws SQLException {
        String itemId = figuritasDeUsuario.getSelectionModel().getSelectedItem().split(" - ")[0].trim();
        int index = figuritasDeUsuario.getSelectionModel().getSelectedIndex();
        System.out.println(itemId);
        System.out.println(index);
        if (FiguritaDeUsuarioService.getInstance().deleteFigurita(itemId)) {
            figuritasDeUsuario.getItems().remove(index);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Collection<FiguritaExistenteDO> figuritas = FiguritaExistenteService.getInstance().readAll();
        Collection<String> figuritasConverted = new ArrayList<>();
        if (figuritas != null) {
            figuritas.forEach(figurita -> {
                figuritasConverted.add(figurita.getId_figurita_existente() + " - " + figurita.getDescripcion());
            });
        }
        figuritasDisponibles.getItems().addAll(figuritasConverted);

        Collection<FiguritaDeUsuarioDO> figuritasUsuario = FiguritaDeUsuarioService.getInstance().readAll();
        Collection<String> figuritasUsuarioConverted = new ArrayList<>();
        if (figuritasUsuario != null) {
            figuritasUsuario.forEach(figurita -> {
                figuritasUsuarioConverted
                        .add(figurita.getId_figurita_usuario().toString() + " - " + figurita.getDescripcion());
            });
        }
        figuritasDeUsuario.getItems().addAll(figuritasUsuarioConverted);
    }

}
