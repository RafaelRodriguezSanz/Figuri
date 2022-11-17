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
        Scene scene =  FXMLLoader.load(getClass().getResource("/Views/FiguriLogin.fxml"));
        stage.setScene(scene);
        stage.show();
    }

   /*  public static void startPlatform() throws ControllerException, FIPAException {
        Runtime rt = Runtime.instance();

        // 1) create a platform (main container+DF+AMS)
        Profile profileMain = new ProfileImpl("192.168.9.1", 1111, "ID", false);
        System.out.println("Launching a main-container..." + profileMain);
        ContainerController mainContainer = rt.createMainContainer(profileMain);

        // Create Container
        System.out.println("Launching containers ...");
        ProfileImpl pContainer = new ProfileImpl("192.168.9.1", 1111, "ID", false);
        pContainer.setParameter(Profile.CONTAINER_NAME, "my-Container");
        System.out.println("Launching container " + pContainer);
        ContainerController myContainer = (AgentContainer) rt.createAgentContainer(pContainer);

        // First Agent
        AgentController firstAgent = myContainer.createNewAgent("john", "ucu.edu.uy.Jade.Agents.AgentTest", null);

        // Get My Agent
        getAgent(myContainer, "john");
        firstAgent.start();
        firstAgent.activate();
    }
    
    private static void getAgent(ContainerController myContainer, String name) throws ControllerException {
        AgentController myAgent = myContainer.getAgent(name);
    }
}
