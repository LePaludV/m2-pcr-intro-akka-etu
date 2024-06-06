package m2dl.pcr.akka.cryptage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System {
        public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.cryptage.System.class);

        public static void main(String... args) throws Exception {
                final ActorSystem actorSystem = ActorSystem.create("actor-system");

                ActorRef crypActor = actorSystem.actorOf(Props.create(CryptageProvider.class), "crypActor");
                log.info("CryptageProvider -> Recepteur");
                ActorRef dest = actorSystem.actorOf(Props.create(Dest.class), "dest");
                Letter letter = new Letter("Hello", dest);
                crypActor.tell(letter, null);

                log.info("ErreurControleProvider -> Recepteur");
                ActorRef errActor = actorSystem.actorOf(Props.create(ErreurControleProvider.class), "errActor");
                errActor.tell(letter, null);

                log.info("-> CryptageProvider -> ErreurControleProvider -> Recepteur");
                ActorRef actorIntermediaire = actorSystem.actorOf(Props.create(MiddleActor.class, errActor, dest),
                                "filter-actor-3");
                Letter letter2 = new Letter("Mega Test", actorIntermediaire);
                crypActor.tell(letter2, null);

                actorSystem.terminate();

        }
}
