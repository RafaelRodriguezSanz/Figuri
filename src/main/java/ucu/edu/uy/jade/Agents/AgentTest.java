package ucu.edu.uy.jade.Agents;

import jade.core.Agent;
import jade.domain.AMSService;
import ucu.edu.uy.jade.Behaviours.BehaviourTest;

import java.util.StringJoiner;

import jade.core.AID;

public class AgentTest extends Agent {

    public void setup() {
        StringJoiner b = new StringJoiner("\n");
        b.add("AID: " + this.getAID().toString());
        b.add("NAME: " + this.getName());
        b.add("AMS: " + this.getAMS());

        System.out.println(b.toString());
        addBehaviour(new BehaviourTest());
    }
}
