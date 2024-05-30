// Acteur enfant pour afficher "Hello _msg_ !"
package m2dl.pcr.akka.helloworld4;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class HelloChildActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    Procedure<Object> hello = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                log.info("Hello " + msg + "!");

            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object message) throws Exception {
        hello.apply(message);
    }
}