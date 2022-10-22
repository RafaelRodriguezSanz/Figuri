package ucu.edu.uy.jade.Messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.tools.sniffer.Message;

/**
 * MessageTest
 */
public class MessageTest extends Message {

    public MessageTest(ACLMessage arg0, AID arg1) {
        super(arg0, arg1);
        System.out.println("Message!");
    }

}