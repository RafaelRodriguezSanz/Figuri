package ucu.edu.uy.Presentacion.Controllers;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ucu.edu.uy.Jade.Utils.Session;
import ucu.edu.uy.Persistencia.ORM.PostgresORM;
import ucu.edu.uy.Presentacion.DO.PublicacionDO;
import ucu.edu.uy.Presentacion.DO.UserDO;
import ucu.edu.uy.Servicio.DTO.FiguritaDeUsuarioDTO;
import ucu.edu.uy.Servicio.DTO.PublicacionDTO;
import ucu.edu.uy.Servicio.Servicios.FiguritaDeUsuarioService;
import ucu.edu.uy.Servicio.Servicios.PublicacionService;
import ucu.edu.uy.Servicio.Servicios.UserService;

public class UserController implements Initializable {

    @FXML
    private Button atrasBtn;

    @FXML
    private Text datos;

    @FXML
    private Scene scene;

    @FXML
    void goToPublications(ActionEvent event) throws IOException {
        Stage stage = (Stage) scene.getWindow();
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Publications.fxml"));
        stage.setScene(scene);
        stage.show();
        Session.getInstance().setPublicationID(null);
        Session.getInstance().setOfferId(null);
        Session.getInstance().setOfferId(null);
        stage.setTitle("Tus Publicaciones");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        PublicacionDTO publication = new PublicacionDTO();
        try {
            publication = PostgresORM.getInstance()
                    .toDTO(PublicacionService.getInstance().read(Session.getInstance().getPublicationID()));
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        FiguritaDeUsuarioDTO figurita = FiguritaDeUsuarioService.getInstance()
                .readFiguritaDTO(publication.getId_figurita_usuario());
        Collection<PublicacionDO> publicaciones = new ArrayList();
        UserDO user = UserService.getInstance().readUser(figurita.getId_usuario());
        StringJoiner data = new StringJoiner("\n");
        data.add("Nombre: " + user.getNombre() + user.getApellido());
        data.add("Teléfono: " + user.getTelefono());
        datos.setText(data.toString());
    }

}
