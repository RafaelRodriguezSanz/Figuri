package ucu.edu.uy.Jade.Agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import ucu.edu.uy.Jade.Messages.Message;

import java.io.IOException;
import java.util.StringJoiner;

public class AgentTest extends Agent {

    public void setup() {
        StringJoiner b = new StringJoiner("\n");
        b.add("AID: " + this.getAID().toString());
        b.add("NAME: " + this.getName());
        b.add("AMS: " + this.getAMS());
        // for (DFAgentDescription services : getServices()) {
        // System.out.println(services.getName());
        // }
        // try {
        // while (true) {
        // sendMessage();
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    public void registerService() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Exchanging");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

    }

    public void unregisterService(String service) {
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    public DFAgentDescription[] getServices() {
        try {
            return DFService.search(this, new DFAgentDescription());
        } catch (FIPAException fe) {
            fe.printStackTrace();
            return null;
        }
    }

    public void sendMessage() throws IOException {
        Message m = new Message(ACLMessage.PROPOSE, getServices()[0].getName(), "Will you marry me?");
        this.send(m.getMessage());
    }

    public void recieveMessage() {
        doWait();
        ACLMessage msg = receive();
        if (msg != null) {
            System.out.println("I received " + msg.getContent());
        } else {
            System.out.println("I didn't receive msg ");
        }
    }
}
