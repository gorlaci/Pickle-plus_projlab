package model;

public class Rag extends IntervalItem{
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
