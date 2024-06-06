package m2dl.pcr.akka.cryptage;

import akka.actor.ActorRef;

public class Letter {
    ActorRef recepter;
    public String message;

    public Letter(String message, ActorRef recepter) {
        this.recepter = recepter;
        this.message = message;
    }
}