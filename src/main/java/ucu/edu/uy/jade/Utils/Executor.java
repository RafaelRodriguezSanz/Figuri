package ucu.edu.uy.jade.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.StringJoiner;

public class Executor {
    public Properties prop;

    public Executor() {
        prop = new Properties();
    }

    public void loadProperties() throws IOException {
        prop = new Properties();
        String basePath = new File("").getAbsolutePath();
        String path = (new File(basePath + "/network.properties")).getAbsolutePath();
        File propertiesFile = new File(path);
        InputStream stream = new FileInputStream(propertiesFile.getAbsolutePath().replace("%20", " "));
        prop.load(stream);
    }

    public String executeCommand() {
        StringJoiner command = new StringJoiner(" ");
        command.add("java -jar jade.Boot");

        String gui = this.prop.getProperty("jade.gui");
        Optional<String> guiOptional = guiParser(gui);
        if (guiOptional.isPresent()) {
            command.add(guiOptional.get());
        }

        String port = this.prop.getProperty("jade.port");
        Optional<String> portOptional = portParser(port);
        if (portOptional.isPresent()) {
            command.add(portOptional.get());
        }

        String containerName = this.prop.getProperty("jade.containerName");
        Optional<String> containerOptional = containerParser(containerName);
        if (containerOptional.isPresent()) {
            command.add(containerOptional.get());
        }

        String hostName = this.prop.getProperty("jade.host");
        Optional<String> hostOptional = hostParser(hostName);
        if (hostOptional.isPresent()) {
            command.add(hostOptional.get());
        }

        String agentsNames = this.prop.getProperty("jade.jade.agentsNames");
        String classesPackages = this.prop.getProperty("jade.jade.agents");
        Optional<String> agentsOptional = agentsParser(agentsNames, classesPackages);
        if (agentsOptional.isPresent()) {
            command.add(agentsOptional.get());
        }

        return command.toString();
    }

    private Optional<String> guiParser(String gui) {
        if (Boolean.parseBoolean(gui)) {
            return Optional.ofNullable("-gui");
        }
        return Optional.empty();
    }

    private Optional<String> portParser(String port) {
        try {
            int portValue = Integer.parseInt(port);
            return Optional.ofNullable("-port " + portValue);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<String> containerParser(String containerName) {
        try {
            return Optional.ofNullable("-container -container-name " + containerName);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<String> hostParser(String host) {
        try {
            InetAddress ip = InetAddress.getByName(host);
            return Optional.ofNullable("-host " + ip);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Optional<String> agentsParser(String agentsNames, String classesPackages) {
        try {
            StringJoiner command = new StringJoiner(" ");
            command.add("-agents");
            String[] names = agentsNames.split(",");
            String[] classes = classesPackages.split(",");
            if (names.length != classes.length) {
                throw new Exception("Names and packages must be the same amount.");
            }
            for (int i = 0; i < classes.length; i++) {
                command.add(names[i] + ':' + classes[i]);
            }
            return Optional.ofNullable(command.toString());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
