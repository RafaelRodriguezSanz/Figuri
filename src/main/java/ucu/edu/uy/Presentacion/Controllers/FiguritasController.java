package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import ucu.edu.uy.Servicio.Servicios.FiguritaDeUsuarioService;
import javafx.scene.control.TextField;
import javafx.scene.control.SelectionMode;
import ucu.edu.uy.Presentacion.DO.FiguritaDeUsuarioDO;
import ucu.edu.uy.Presentacion.Controllers.LoginController;


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
    private ListView<FiguritaDeUsuarioDO> figuritasDeUsuario;

    @FXML
    private ListView<?> figuritasDisponibles;

    @FXML
   private TextField idFiguritaExistente;
   
   private String estado;

   @FXML
   void SeleccionoPesimo(ActionEvent event) {
    estadoBtn.setText("Pesimo");
    this.estado="3";
   }

   @FXML
   void SeleccionoMalo(ActionEvent event) {
    estadoBtn.setText("Malo");
    this.estado="2";
   }
   @FXML
   void SeleccionoBueno(ActionEvent event) {
    estadoBtn.setText("Bueno");
    this.estado="1";
   }

   @FXML
   void SeleccionoExcelente(ActionEvent event) {
    estadoBtn.setText("Excelente");
    this.estado="0";
   }

   //String id_Usuario = LoginController.getUserField().getText();


    @FXML
    void addFigurita(ActionEvent event) {
       /*  String figurita=FiguritaDeUsuarioService.getInstance().createFigurita(idFiguritaExistente.getText(),estado,id_Usuario);
        FiguritaDeUsuarioDO figuri= FiguritaDeUsuarioService.getInstance().readFigurita(figurita);
        figuritasDeUsuario = new ListView<>();
        ObservableList<FiguritaDeUsuarioDO> lista = FXCollections.observableArrayList(figuri);
        figuritasDeUsuario.setItems(lista);
        figur itasDeUsuario.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
       // figuritasDeUsuario.getSelectionModel().selectedItemProperty().addListener();*/
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
