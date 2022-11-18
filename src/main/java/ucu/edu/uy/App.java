package ucu.edu.uy;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import jade.domain.FIPAException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    @FXML
    private AnchorPane ap;

    public static void main(String[] args)
            throws InterruptedException, FIPAException, StaleProxyException, ControllerException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        stage.setScene(scene);
        stage.show();
    }
}
