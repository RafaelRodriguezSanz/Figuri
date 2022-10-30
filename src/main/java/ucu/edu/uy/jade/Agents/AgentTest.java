package ucu.edu.uy.jade.Agents;

import jade.core.Agent;
import java.util.StringJoiner;

public class AgentTest extends Agent {

    public void setup() {
        StringJoiner b = new StringJoiner("\n");
        b.add("AID: " + this.getAID().toString());
        b.add("NAME: " + this.getName());
        b.add("AMS: " + this.getAMS());
    }
}
