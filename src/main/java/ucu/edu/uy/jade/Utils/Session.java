package ucu.edu.uy.jade.Utils;

import lombok.Getter;
import lombok.Setter;
import ucu.edu.uy.Servicio.POJO.Utils.Validator;
import ucu.edu.uy.Servicio.Servicios.UserService;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ucu.edu.uy.jade.Utils.Session;

public class Session {
    private static final Session SINGLE_INSTANCE = new Session();

    @Getter
    @Setter
    String id;

    private Session() {
        this.id = null;
    }

    public static Session getInstance() {
        return SINGLE_INSTANCE;
    }

    public boolean login(String user, String password) {
        try {
            if (UserService.getInstance().login(user, password)) {
                this.id = Validator.cleanNumber(user);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void startSession() {
        System.out.println("Adding Agent...");
        Runtime rt = Runtime.instance();

        // 1) create a platform (main container+DF+AMS)
        Profile profileMain = new ProfileImpl("192.168.9.1", 1111, "ID", false);
        System.out.println("Launching a main-container..." + profileMain);
        ContainerController mainContainer = rt.createMainContainer(profileMain);

        // Create Container
        System.out.println("Launching containers ...");
        ProfileImpl pContainer = new ProfileImpl("192.168.9.1", 1111, "ID", false);
        pContainer.setParameter(Profile.CONTAINER_NAME, "Traders");
        System.out.println("Launching container " + pContainer);
        ContainerController myContainer = (AgentContainer) rt.createAgentContainer(pContainer);

        // First Agent
        AgentController firstAgent;
        try {
            firstAgent = myContainer.createNewAgent("Agent-" + getId(), "ucu.edu.uy.Jade.Agents.AgentTest", null);
            // Get My Agent
            firstAgent.start();
            firstAgent.activate();
        } catch (StaleProxyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
