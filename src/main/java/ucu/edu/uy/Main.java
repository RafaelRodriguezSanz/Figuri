package ucu.edu.uy;

import jade.domain.FIPAException;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class Main {

    public static void main(String[] args)
            throws StaleProxyException, InterruptedException, FIPAException, ControllerException {
        App.main(args);
    }
}
