package model;

import testing.Logger;

import java.util.List;

public class BeerGlass extends IntervalItem{
    @Override
    public void meet(Person person) {

    }

    @Override
    public boolean saveFromDeath(Person killer) {
        Logger.enter( this, "saveFromDeath", List.of(killer) );
        boolean success = Logger.askQuestion( "Is # activated?", this );

    }

    @Override
    public boolean saveFromGas() {
        return false;
    }

    @Override
    public void timeElapsed(int time) {

    }
}
