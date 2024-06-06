package m2dl.pcr.akka.cryptage;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.japi.Procedure;
import m2dl.pcr.akka.stringservices.StringUtils;

public class ErreurControleProvider extends UntypedActor {
    StringUtils stringUtils;

    public ErreurControleProvider() {
        stringUtils = new StringUtils();
    }

    Procedure<Object> crypte = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Letter) {
                String res = stringUtils.ajouteCtrl(((Letter) msg).message);
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
