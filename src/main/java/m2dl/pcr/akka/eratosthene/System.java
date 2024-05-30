package m2dl.pcr.akka.eratosthene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System {
    public static final Logger log = LoggerFactory.getLogger(System.class);

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");
        final ActorRef eratostheneActor = system.actorOf(Props.create(EratostheneActor.class), "eratostheneActor");

        for (int i = 2; i <= 100; i++) {
            eratostheneActor.tell(i, ActorRef.noSender());
        }

        system.terminate();
    }

}
