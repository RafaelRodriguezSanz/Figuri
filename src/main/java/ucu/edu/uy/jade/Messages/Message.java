package ucu.edu.uy.jade.Messages;

import java.io.IOException;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Serializable;

/**
 * MessageTest
 */
public class Message {
    private ACLMessage message;

    public Message(int performative, AID receiver, String message) {
        this.message = new ACLMessage(performative);
        this.message.addReceiver(receiver);
        this.message.setContent(message);
    }

    public Message(int performative, AID receiver, Serializable object) throws IOException {
        this.message = new ACLMessage(performative);
        this.message.addReceiver(receiver);
        this.message.setContentObject(message);
    }

    public ACLMessage getMessage() {
        return message;
    }
}