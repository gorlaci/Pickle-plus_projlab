package model;

import testing.Logger;

import java.util.List;

public class Mask extends IntervalItem{
    @Override
    public void meet(Person person) {
        
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
        Logger.exit( this, "saveFromGas", "true" );
        return true;
    }

    @Override
    public void timeElapsed(int time) {

    }
}
