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
        return false;
    }

    @Override
    public void timeElapsed(int time) {

    }
}
