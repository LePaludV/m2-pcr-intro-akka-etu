package m2dl.pcr.akka.cryptage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Procedure;
import m2dl.pcr.akka.stringservices.StringUtils;

public class CryptageProvider extends UntypedActor {
    ActorRef recepter;
    StringUtils stringUtils;
    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.cryptage.System.class);

    public CryptageProvider() {
        stringUtils = new StringUtils();
    }

    Procedure<Object> crypte = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Letter) {
                log.info("le msg à crypté: " + ((Letter) msg).message);
                String res = stringUtils.crypte(((Letter) msg).message);
                ActorRef dest = ((Letter) msg).recepter;
                dest.tell(res, getSelf());
            }
        }
    };

    @Override
    public void onReceive(Object message) throws Exception {
        crypte.apply(message);
    }

}
