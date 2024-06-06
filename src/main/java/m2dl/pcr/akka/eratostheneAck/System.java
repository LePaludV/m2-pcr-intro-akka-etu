package m2dl.pcr.akka.eratostheneAck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System {

    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.helloworld3.System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        ActorRef filterActor = actorSystem.actorOf(Props.create(EratostheneActor.class, 2), "filter-actor-2");
        int N = 100;

        Thread.sleep(5000);

        for (int i = 3; i <= N; i++) {
            filterActor.tell(i, null);

        }

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}