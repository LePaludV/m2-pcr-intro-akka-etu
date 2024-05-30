package m2dl.pcr.akka.eratosthene;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class EratostheneActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef nextActor = null;
    private int prime;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Integer) {
            int number = (Integer) message;
            if (nextActor == null) {
                prime = number;
                log.info("Prime number: " + number);
                nextActor = getContext().actorOf(Props.create(EratostheneActor.class));
            } else {
                if (number % prime != 0) {
                    nextActor.tell(number, getSelf());
                }
            }
        }
    }
}