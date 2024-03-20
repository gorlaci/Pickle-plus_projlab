package model;

import testing.Logger;

import java.util.List;

public class Mask extends IntervalItem{

    public Mask(){
        Logger.create(this);
        Logger.exitCreate(this);
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
        Logger.enter( this, "saveFromGas" );
        boolean isActivated = Logger.askQuestion( "Is # activated?", this );
        if(!isActivated)
            activate();
        Logger.exit( this, "saveFromGas", "true" );
        return true;
    }

    @Override
    public void timeElapsed(int time) {
        Logger.enter(this, "timeElapsed", List.of(time));
        boolean isActivated = Logger.askQuestion( "Is # activated?", this );
        if(isActivated) {
            boolean isOver = Logger.askQuestion("Are both duration and timeRemaining of # zero?", this);
            if(isOver) {
                if (holder != null) {
                    holder.removeItem(this);
                }
                else {
                    location.removeItem(this);
                }
            }
        }
        Logger.exit(this, "timeElapsed");
    }
}
