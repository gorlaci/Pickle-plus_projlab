package model;

import testing.Logger;

import java.util.List;

public class TVSZ extends Item{
    @Override
    public void activate() {

    }

    @Override
    public void meet(Person person) {
        Logger.enter( this, "meet", List.of(person) );
        Logger.exit( this, "meet");
    }

    @Override
    public boolean saveFromDeath(Person killer) {
        return false;
    }

    @Override
    public boolean saveFromGas() {
        return false;
    }

    @Override
    public void timeElapsed(int time) {

    }
}
