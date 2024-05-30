package m2dl.pcr.akka.helloworld4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System {
    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String[] args) throws InterruptedException {
        // Création de l'acteur parent

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        Thread.sleep(5000);

        final ActorRef parentActor = actorSystem.actorOf(Props.create(HelloGoodbyeActor.class), "parentActor");

        parentActor.tell("John", null);
        parentActor.tell("Pauline", null);
        parentActor.tell("Eva", null);
        parentActor.tell("Bill", null);
        parentActor.tell("Marc", null);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }

}
