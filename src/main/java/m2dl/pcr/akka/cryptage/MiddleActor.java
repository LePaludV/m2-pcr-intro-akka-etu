package m2dl.pcr.akka.cryptage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class MiddleActor extends UntypedActor {
    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.cryptage.System.class);
    private ActorRef nextActor;
    private ActorRef finalActor;

    public MiddleActor(ActorRef nextActor, ActorRef finalActor) {
        this.nextActor = nextActor;
        this.finalActor = finalActor;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;
            log.info("MiddleActor: " + msg);
            Letter letter = new Letter(msg, finalActor);
            nextActor.tell(letter, getSelf());
            log.info("MiddleActor: On a bien envoyé le msgffffffyqtzdfsgcZES,YDGIYQEksfdç ");

        }
    }

}
