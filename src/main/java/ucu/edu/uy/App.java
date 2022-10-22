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

public class App {
    public static void main(String[] args)
            throws InterruptedException, FIPAException, StaleProxyException, ControllerException {
        Runtime rt = Runtime.instance();

        // 1) create a platform (main container+DF+AMS)
        Profile profileMain = new ProfileImpl("localhost", 8080, "ID", true);
        System.out.println("Launching a main-container..." + profileMain);
        ContainerController mainContainer = rt.createMainContainer(profileMain);

        // Create Container
        System.out.println("Launching containers ...");
        ProfileImpl pContainer = new ProfileImpl("localhost", 8080, "ID", true);
        pContainer.setParameter(Profile.CONTAINER_NAME, "my-Container");
        System.out.println("Launching container " + pContainer);
        ContainerController myContainer = (AgentContainer) rt.createAgentContainer(pContainer);

        // First Agent
        AgentController firstAgent = mainContainer.createNewAgent("john", "ucu.edu.uy.jade.Agents.AgentTest", null);

        // AMS & DF Agents
        AgentController AMS = mainContainer.getAgent("AMS");
        AgentController DF = mainContainer.getAgent("DF");

        // Get My Agent
        getAgent(mainContainer, "john");
        firstAgent.start();
        firstAgent.activate();

    }

    private static void getAgent(ContainerController mainContainer, String name) throws ControllerException {
        AgentController myAgent = mainContainer.getAgent(name);
    }
}
