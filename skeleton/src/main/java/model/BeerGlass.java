package model;

import testing.Logger;

import java.util.List;

public class BeerGlass extends IntervalItem{

    public BeerGlass(){
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
        boolean success = Logger.askQuestion( "Is # activated?", this );
        Logger.exit( this, "saveFromDeath", success ? "true" : "false" );
        return success;
    }

    @Override
    public boolean saveFromGas() {
        Logger.enter(this, "saveFromGas");
        Logger.exit(this, "saveFromGas", "false");
        return false;
    }

    @Override
    public void timeElapsed(int time) {
        Logger.enter(this, "timeElapsed", List.of(time));
        boolean isActivated = Logger.askQuestion( "Is # activated?", this );
        if(isActivated) {
            boolean isTimeRemaining = Logger.askQuestion("Is there any time remaining for #?", this);
            if(!isTimeRemaining) {
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
