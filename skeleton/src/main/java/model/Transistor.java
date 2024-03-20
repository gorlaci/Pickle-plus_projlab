package model;

import testing.Logger;

import java.util.HashMap;
import java.util.List;

public class Transistor extends Item{

    private Transistor pair=null;

    private static final HashMap<Person,Transistor> pairingRequests = new HashMap<>();

    public Transistor(){
        Logger.create(this);
        Logger.exitCreate(this);
    }

    public void setPair(Transistor transistor) {
        Logger.enter(this, "setPair", List.of(transistor));
        pair=transistor;
        Logger.exit(this, "setPair");
    }


    @Override
    public void activate() {
        Logger.enter( this, "activate" );

        boolean isPaired = Logger.askQuestion( "Is # paired?", this );
        if (isPaired) {
            Person personToMove = holder;
            personToMove.dropItem( this );
            personToMove.enterRoom( pair.location );
        } else {
            Transistor candidate = pairingRequests.get( holder );
            if( candidate == null || candidate.holder != holder ){
                pairingRequests.put( holder, this );
            } else {
                pair = candidate;
                candidate.pair = this;
                pairingRequests.remove(holder);
            }
        }

        Logger.exit( this, "activate" );
    }

    @Override
    public void meet(Person person) {
        Logger.enter( this, "meet", List.of(person) );
        Logger.exit( this, "meet");
    }

    @Override
    public boolean saveFromDeath(Person killer) {
        Logger.enter( this, "saveFromDeath", List.of(killer) );
        Logger.exit( this, "saveFromDeath", "false" );
        return false;
    }

    @Override
    public boolean saveFromGas() {
        Logger.enter( this, "saveFromGas");
        Logger.exit( this, "saveFromGas", "false" );
        return false;
    }

    @Override
    public void timeElapsed(int time) {
        Logger.enter(this, "timeElapsed", List.of(time));
        Logger.exit(this, "timeElapsed");
    }
}
