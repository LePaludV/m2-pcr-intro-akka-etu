package m2dl.pcr.akka.eratostheneAck;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class EratostheneActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    int primeNumber;
    ActorRef nextFilterActor;

    public EratostheneActor(int primeNumber) {
        log.info("FilterActor constructor");
        this.primeNumber = primeNumber;
    }

    Procedure<Object> last = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                // log.info("[Filter " + primeNumber + "] receive " + msg);
                if ((Integer) msg % primeNumber != 0) {
                    log.info("[!] Prime number: " + msg);
                    nextFilterActor = getContext().actorOf(Props.create(EratostheneActor.class, msg));
                    getContext().become(intermediate, true);
                }
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> intermediate = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                // log.info("[Filter " + primeNumber + "] receive " + msg);
                if ((Integer) msg % primeNumber != 0) {
                    nextFilterActor.tell(msg, getSelf());
                }
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        last.apply(msg);
    }
}