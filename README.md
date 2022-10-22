# Figuri
Data Base Project - 2022

----

# FIPA
The Foundation of Intelligent Physical Agents
http://www.fipa.org/subgroups/ROFS-SG-docs/2007-TAAS-specifying-MAS.pdf
# Examples
https://www-labs.iro.umontreal.ca/~dift6802/jade/src/examples/

# Agent
Hay multiples agentes con nombres unicos.
## Setup
`protected void setup()` es el metodo que inicializa al agente
## Behaviours
Acciones de los agentes para cumplir sus goals.
`import jade.core.behaviours.Behaviour`
Los behaviours no se ejecutan si no se agregan a la lista de comportamientos de un agente. Para ello se utiliza el metodo: `addBehaviour()`
Pueden ser agregadoe en cualquier momento.
Los Behaviours son clases que extienden de Behaviour y tienen que implementar el `metodo action()`. En `action()` se especifica el comportamiento deseado. 
Por otro lado esta el metodo `done()` que devuelve un boolean determinando si la accion termino o no.
Tener en cuenta que los hilos de los comportamientos no son preemptivos sino que son cooperativos. Si no se tienen Behaviours activos en la cola de behaviours, el thread queda en sleep y se despierta cuando haya un nuevo behaviour.
#### Behaviours Types
##### One-Shot Behaviour
Se ejecuta una unica vez
##### Cyclic Behaviour
Se ejecuta en forma ciclica
##### Generic Behaviour
Se podria cambiar el valor de una variable y no salir hasta que el done sea true, o cambiar el comportamiento segun una variable, etc.
## Communication
`jade.lang.acl.ACLMessage`
Los mensajes que intercambian los agentes se desarrollan en el leugnaje ACL definido en FIPA. Incluye los siguientes campos:
- Sender
- List of receivers
- Performative (Communicative intention) 
	- REQUEST
	- INFORM
	- QUERY_IF
	- CFP (call for proposal)
	- PROPOSE
	- ACCEPT_PROPOSAL
	- REJECT_PROPOSAL
- Content
- Language (encode/parse schemma)
- Ontology (vocabulary of symbols)
- Control fields
	-conversation-id
	-reply-with
	-in-reply-to
	-reply-by

Ejemlo:
```
// Message carrying a request for offer
ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
for (int i = 0; i < sellerAgents.lenght; ++i) {
 cfp.addReceiver(sellerAgents[i]);
}
cfp.setContent(targetBookTitle);
myAgent.send(cfp);
```

Recepcion:
```
ACLMessage msg = receive();
if (msg != null) {
 // Process the message
}
```

Se recomienda que sea bloqueante:
```
public void action() {
 ACLMessage msg = myAgent.receive();
 if (msg != null) {
 // Message received. Process it
 ...
 }
 else {
 block();
 }
}
```
### Message Template
Los mensajes se pueden clasificar en siertos template. Esto es util para la gestion de mensajes y su procesamiento.
`jade.lang.acl.MessageTemplate`

Se puede bloquear hasta conseguir un mensaje con el template necesario.

```
public void action() {
 MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
 ACLMessage msg = myAgent.receive(mt);
 if (msg != null) {
 // CFP Message received. Process it
 ...
 }
 else {
 block();
 }
}
```


##### Waker Behaviour
##### Ticker Behaviour
## AID
Cada agente tiene un ID.
```
import jade.core.AID
getAID()
```

## Name
```
import jade.core.AID
getAID().getName()
```
El nombre es de la forma `<nickname>@<platform-name>`.
Estos nombers son los que se usan para poder interactuar con agentes de otras plataformas.
```
String nickname = “Peter”;
AID id = new AID(nickname, AID.ISLOCALNAME);
```
## Execute
Para ejecutar un agente hay que usar:
`java -jar jade.boot nickname:AgentJarName`

`java -cp lib\jade.jar jade.Boot -gui -local-port 1111`

Similarly it is possible to make JADE use a different host name/address with respect to that read from the underlying network stack by means of the -local-host <host-name-or-address> option. This is typically useful when dealing with network environments where hosts can be reached only by specifying hostnames including the network domain (e.g. jade.tilab.com instead of just avalon) as exemplified below

`java -cp lib\jade.jar jade.Boot -gui -local-host jade.tilab.com`
More info: (Starting Agent)[https://jade.tilab.com/documentation/tutorials-guides/jade-administration-tutorial/starting-jade/]	
Distributed Systems: (Distributed Plataform)[https://jade.tilab.com/documentation/tutorials-guides/jade-administration-tutorial/creating-a-distributed-platform/]
Remote Plataforms: (Platforms)[https://jade.tilab.com/documentation/tutorials-guides/jade-administration-tutorial/running-multiple-jade-platforms/]
Administration: (Admin)[https://jade.tilab.com/doc/administratorsguide.pdf]	
Tecnical Data: (Technical)[https://jade.tilab.com/doc/programmersguide.pdf]
(Java Doc)[https://jade.tilab.com/doc/api/index.html]
## Delete
Se ejecuta el metodo `doDelete()` para matar a un agente. Se ejecuta tambien el metodo `takeDown()` para hacer un clean-up de los cosas del agente previo al `doDelete()`
## Arguments
Se le puede pasar argumentos a los agentes como parametros al iniciar. Se utiliza el metodo `getArguments()` para obtener un array de objetos que son los argumentos.
Los argumentos se pasan de la siguiente forma:
`java jade.Boot buyer:BookBuyerAgent(The-Lord-of-the-rings)` donde `The-Lord-of-the-rings` es el argumento.

# Container
Es una de las instancias de JADE JRE(Java Running Environment) y contiene varios agentes
# Platform
El conjunto de Containers activos es un platform.
# Main Container
Se ejecuta al comenzar la plataforma y el resto de los contenedores se registran en cuanto inician. Se les indica a los contenedores cual es el host y puerto al cual conectarse.
# AMS y DF
Son dos agentes que se ejecutan dentro del main container.
AMS (Agent Management System)
DF (Directory Facilitator)
El AMS se encarga de asignar nombres unicos a los agentes.
El DF es el que probee Yellow Pages.
# Yellow Pages
Es un sistema donde los agentes pueden ofrecer servicios o consumirlos para satisfacer sus metas.
Se utiliza el SL0 language y FIPA-agent-management ontology para poder hacer las peticiones a las Yellow Pages.
`jade.domain.DFService`

## Publicar servicios
```
jade.domain.FIPAAgentManagement
 protected void setup() {
// Register the book-selling service in the yellow pages
 DFAgentDescription dfd = new DFAgentDescription();
 dfd.setName(getAID());
 ServiceDescription sd = new ServiceDescription();
 sd.setType(“book-selling”);
 sd.setName(“JADE-book-trading”);
 dfd.addServices(sd);
 try {
 DFService.register(this, dfd);
 }
 catch (FIPAException fe) {
 fe.printStackTrace();
 }
}
```

Es importante despublicar el servicio cuando el agente DF muere:
```
protected void takeDown() {
 // Deregister from the yellow pages
 try {
 DFService.deregister(this);
 }
 catch (FIPAException fe) {
 fe.printStackTrace();
 }
 // Close the GUI
 myGui.dispose();
 // Printout a dismissal message
 System.out.println(“Seller-agent “+getAID().getName()+” terminating.”);
 }
```
Para buscar servicios:
```
public class BookBuyerAgent extends Agent {
 // The title of the book to buy
 private String targetBookTitle;
 // The list of known seller agents
 private AID[] sellerAgents;
 // Put agent initializations here
 protected void setup() {
 // Printout a welcome message
 System.out.println(“Hello! Buyer-agent “+getAID().getName()+” is ready.”);

 // Get the title of the book to buy as a start-up argument
 Object[] args = getArguments();
 if (args != null && args.length > 0) {
 targetBookTitle = (String) args[0];
 System.out.println(“Trying to buy “+targetBookTitle);
 // Add a TickerBehaviour that schedules a request to seller agents every minute
 addBehaviour(new TickerBehaviour(this, 60000) {
 protected void onTick() {
 // Update the list of seller agents
 DFAgentDescription template = new DFAgentDescription();
 ServiceDescription sd = new ServiceDescription();
 sd.setType(“book-selling”);
 template.addServices(sd);
 try {
 DFAgentDescription[] result = DFService.search(myAgent, template);
 sellerAgents = new AID[result.length];
 for (int i = 0; i < result.length; ++i) {
 sellerAgents[i] = result[i].getName();
 }
 }
 catch (FIPAException fe) {
 fe.printStackTrace();
 }
 // Perform the request
 myAgent.addBehaviour(new RequestPerformer());
 }
 } );
```

Tambien existen los metodos `searchUntilFound()` y `createSubscriptionMessage()`

				   
Belief -> Los Datos del estado actual del universo en la BD
	
Desires -> Lo que se quiere obtener.
	
Goal -> Son los Desires que se van a buscar.
	
Intentions-> Es la busqueda de los Goals.
	
Plans -> Acciones para poder cumplir los Goals.
	
Events -> Son los gatillos que hacen ejecutar los planes.



Belief => Datos de figuritas (No exactamente la realidad)
	
Desires => Completar el album
	
Goal => Figuritas que nos faltan
	
Intentions => Figuritas que queremos
	
Plans => Publicar, intercambiar, ofertar, query, etc.
	
Events => Publicaciones, etc.

---

(Create Platform)[https://startjade.gitlab.io/CreatePlatform/]
