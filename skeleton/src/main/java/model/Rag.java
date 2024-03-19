package model;

import testing.Logger;

public class Rag extends IntervalItem{

    public Rag(){
        Logger.create(this);
        Logger.exitCreate(this);
    }

    @Override
    public void meet(Person person) {

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
